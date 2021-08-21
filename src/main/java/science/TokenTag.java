package science;

/**
 * Тэг слова сообщения
 */
public class TokenTag {
    private Tag tag;
    private double tagRate;

    public TokenTag(Tag tag, double tagRate) {
        this.tag = tag;
        this.tagRate = tagRate;
    }

    public Tag getTag() {
        return tag;
    }

    public double getTagRate() {
        return tagRate;
    }
}
