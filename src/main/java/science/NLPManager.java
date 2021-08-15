package science;

import java.util.HashMap;
import java.util.Map;

public class NLPManager {
    KeyWordsCollector keyWordsCollector;
    private Map<Tag, String> PATHS = new HashMap<>();

    public NLPManager() {
        PATHS.put(new Tag("SPAM", -1), "/dictionaries/spam.dict");
        PATHS.put(new Tag("VKONTAKTE", -1), "/dictionaries/vkontakte.dict");
        PATHS.put(new Tag("TIKTOK", -1), "/dictionaries/tiktok.dict");
        PATHS.put(new Tag("INSTAGRAM", -1), "/dictionaries/instagram.dict");
        this.keyWordsCollector = new KeyWordsCollector(PATHS);
    }
}
