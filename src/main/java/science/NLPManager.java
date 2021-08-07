package science;

import java.util.HashMap;
import java.util.Map;

public class NLPManager {
    KeyWordsCollector keyWordsCollector;
    Map<Tag, String> PATHS = new HashMap<>();

    public NLPManager() {
        PATHS.put(Tag.SPAM, "/dictionaries/spam.dict");
        PATHS.put(Tag.VKONTAKTE, "/dictionaries/vkontakte.dict");
        PATHS.put(Tag.TIKTOK, "/dictionaries/tiktok.dict");
        PATHS.put(Tag.INSTAGRAM, "/dictionaries/instagram.dict");
        this.keyWordsCollector = new KeyWordsCollector(PATHS);
    }
}
