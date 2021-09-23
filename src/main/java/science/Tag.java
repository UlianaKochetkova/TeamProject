package science;

/**
 * Простейший класс тэга
 */
public class Tag {

    private static int maxId = 2;
    private int id;
    private String label;
    private String value;

    public Tag(int id, String label) {
        this.id = id;
        maxId = Math.max(id + 1, maxId);
        this.label = label;
        this.value = label.toLowerCase().replaceAll("\\pP", " ");
    }

    public Tag(String label) {
        this.id = getNewId();
        this.label = label;
        this.value = label.toLowerCase().replaceAll("\\pP", " ");
    }

    public static Tag emptyTag() {
        return new Tag(0, "NO_TAG");
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
