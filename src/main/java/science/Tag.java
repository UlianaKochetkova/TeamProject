package science;

/**
 * Простейший класс тэга
 */
public class Tag {
    private static int maxId = 4;
    private int id;
    private String label;
    private String value;

    public Tag(int id, String label) {
        this.id = id;
        this.label = label;
        this.value = label.toLowerCase().replaceAll("\\pP", " ");
    }

    public static Tag emptyTag() {
        return new Tag(0, "NO_TAG");
    }

    public static Tag spamTag() {
        return new Tag(1, "SPAM");
    }

    public static int getNewId() {
        return ++maxId;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
