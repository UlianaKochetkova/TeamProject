package science;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NLPManager {
    public KeyWordsCollector keyWordsCollector;
    private String messages;
    private TagAnalyzer tagAnalyzer;
    private Map<Tag, String> PATHS = new HashMap<>();

    public NLPManager() {
        PATHS.put(new Tag(1, "SPAM"), "/dictionaries/spam.dict");
        messages = "";
        tagAnalyzer = new TagAnalyzer();
        List<Tag> tags = tagAnalyzer.analyze(messages);
        this.keyWordsCollector = new KeyWordsCollector(tags, PATHS);
    }

    public void recomputeTags() {
        List<Tag> tags = tagAnalyzer.analyze(messages);
        this.keyWordsCollector = new KeyWordsCollector(tags, PATHS);
    }

    public void addMessage(String message) {
        this.messages = messages.concat(" ... " + message);
        recomputeTags();
    }
}
