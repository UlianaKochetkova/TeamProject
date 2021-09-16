package science;

import java.util.HashMap;
import java.util.Map;

/**
 * Основной класс, который занимается управлением NLP
 */
public class NLPManager {
    public KeyWordsCollector keyWordsCollector;
    private String messages;
    private TagAnalyzer tagAnalyzer;
    private Map<Tag, String> PATHS = new HashMap<>();

    public NLPManager() {
        PATHS.put(new Tag(1, "SPAM"), "/dictionaries/spam.dict");
        messages = "";
        tagAnalyzer = new TagAnalyzer();
        Map<Tag, Integer> tags = tagAnalyzer.analyze(messages);
        this.keyWordsCollector = new KeyWordsCollector(tags, PATHS);
    }

    /**
     * Пересчитывает теги в чате
     */
    public void recomputeTags() {
        Map<Tag, Integer> tags = tagAnalyzer.analyze(messages);
        this.keyWordsCollector = new KeyWordsCollector(tags, PATHS);
    }

    /**
     * Добавляет новое сообщение в текст сообщений
     *
     * @param message
     */
    public void addMessage(String message, boolean isRecompute) {
        this.messages = messages.concat(" ... " + message);
        if (isRecompute) {
            recomputeTags();
            System.out.println(messages.length());
        }
    }

    public void putFavoriteKeyWords(Map<String, TokenTag> favoritesKeyWords) {
        keyWordsCollector.putFavoriteKeyWords(favoritesKeyWords);
        recomputeTags();
    }
}
