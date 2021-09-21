package science;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Хранит в себе список всех существующих в данные момент тэгов и ключевых слов, умеет читать их из словарей
 */
public class KeyWordsCollector {

    private Map<Tag, String> paths;
    private Map<String, TokenTag> keyWords;
    private Map<Tag, Integer> keyWordsCount;
    private static Map<String, TokenTag> keyWordsFavorites = new HashMap<>();

    public KeyWordsCollector(Map<Tag, Integer> tags, Map<Tag, String> paths) {
        keyWords = new HashMap<>();
        keyWordsCount = new HashMap<>();
        this.paths = paths;
        readAllDictionaries();
        readAllFavoritesKeyWords();
        for (Map.Entry<Tag, Integer> tag : tags.entrySet()) {
            if (!keyWords.containsKey(tag.getKey().getLabel())) {
                this.keyWords.put(tag.getKey().getLabel().toLowerCase().replaceAll("\\pP", " "), new TokenTag(tag.getKey(), 1));
                this.keyWordsCount.put(tag.getKey(), tag.getValue());
            }
        }
    }

    /**
     * Достаёт тэги из всех словарей
     */
    private void readAllDictionaries() {
        paths.forEach(this::readDictionary);
    }

    private void readDictionary(Tag tag, String path) {
        InputStream inputStream = getClass().getResourceAsStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            while (bufferedReader.ready()) {
                String[] tokenLine = bufferedReader.readLine().split(":");
                keyWords.put(
                        tokenLine[0],
                        new TokenTag(tag, Double.parseDouble(tokenLine[1])));
                keyWordsCount.put(
                        Tag.spamTag(),
                        Integer.MAX_VALUE
                );
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void readAllFavoritesKeyWords() {
        keyWords.putAll(keyWordsFavorites);
    }

    public void putFavoriteKeyWords(Map<String, TokenTag> favoritesKeyWords) {
        Map<String, TokenTag> newKeyWordsFavorites = new HashMap<>();
        for (Map.Entry<String, TokenTag> keyWord : favoritesKeyWords.entrySet()) {
            boolean inKeyWords = false;
            for (Map.Entry<String, TokenTag> tag : keyWordsFavorites.entrySet()) {
                if (keyWord.getKey().equals(tag.getValue().getTag().getLabel())) {
                    newKeyWordsFavorites.put(tag.getKey(), keyWord.getValue());
                    inKeyWords = true;
                }
            }
            if (!inKeyWords) {
                newKeyWordsFavorites.put(keyWord.getKey(), keyWord.getValue());
            }
        }
        Map<String, TokenTag> modifiedKeyWordsFavorites = new HashMap<>(keyWordsFavorites);
        for (Map.Entry<String, TokenTag> tag : keyWordsFavorites.entrySet()) {
            if (newKeyWordsFavorites.containsKey(tag.getValue().getTag().getLabel())) {
                modifiedKeyWordsFavorites.remove(tag.getKey());
            }
        }
        keyWordsFavorites = modifiedKeyWordsFavorites;
        keyWordsFavorites.putAll(newKeyWordsFavorites);
    }

    public TokenTag getToken(String word) {
        word = word.toLowerCase().replaceAll("\\pP", " ");
        if (keyWords.containsKey(word)) {
            return keyWords.get(word);
        } else {
            return new TokenTag(Tag.emptyTag(), 0);
        }
    }

    public List<Tag> getTags() {
        return keyWords.values()
                .stream()
                .map(TokenTag::getTag)
                .collect(Collectors.toList());
    }

    public List<Tag> getTopTags(int minCount) {
        return keyWordsCount.entrySet()
                .stream()
                .filter(tagIntegerEntry -> tagIntegerEntry.getValue() >= minCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
