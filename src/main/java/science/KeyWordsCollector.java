package science;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyWordsCollector {
    private Map<Tag, String> paths;
    private Map<String, TokenTag> keyWords;

    public KeyWordsCollector(List<Tag> tags, Map<Tag, String> paths) {
        keyWords = tags.stream()
                .collect(
                        Collectors.toMap(
                                Tag::getValue,
                                tag -> new TokenTag(tag, 1)));
        this.paths = paths;
        readAllDictionaries();
    }

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
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public TokenTag getToken(String word) {
        word = word.toLowerCase().replaceAll("\\pP", "");
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
}
