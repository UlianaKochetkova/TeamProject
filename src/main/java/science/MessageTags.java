package science;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс представляющий собой Map все тэгов сообщения
 */
public class MessageTags extends HashMap<Tag, Double> {

    public void addRate(TokenTag tokenTag) {
        Tag tag = tokenTag.getTag();
        if (this.containsKey(tag)) {
            Double oldValue = this.get(tag);
            this.replace(tag, oldValue + tokenTag.getTagRate());
        } else {
            this.put(tag, tokenTag.getTagRate());
        }
    }

    public Map<Tag, Double> getTopTagsMap(int count) {
        return this.entrySet().stream()
                .filter(tagDoubleEntry -> tagDoubleEntry.getValue() > 0)
                .sorted(Comparator.comparingDouble(Map.Entry<Tag, Double>::getValue).reversed())
                .limit(count)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    public List<String> getTopTags(int count) {
        return this.entrySet().stream()
                .filter(tagDoubleEntry -> tagDoubleEntry.getValue() > 0)
                .sorted(Comparator.comparingDouble(Map.Entry<Tag, Double>::getValue).reversed())
                .map(tagDoubleEntry -> tagDoubleEntry.getKey().getLabel())
                .distinct()
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return getTopTagsMap(7).toString();
    }
}
