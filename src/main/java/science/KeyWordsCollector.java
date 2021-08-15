package science;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class KeyWordsCollector {
    private Map<Tag, String> paths;
    private Map<String, TokenTag> keyWords;

    public KeyWordsCollector(Map<Tag, String> paths) {
        this.paths = paths;

        keyWords = new HashMap<>();

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
        if (keyWords.containsKey(word)) {
            return keyWords.get(word);
        } else {
            return new TokenTag(Tag.emptyTag(), 0);
        }
    }
}
