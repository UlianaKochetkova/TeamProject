package science;

public class Tag {
    private String label;
    private int importance;

    public Tag(String label, int importance) {
        this.label = label;
        this.importance = importance;
    }

    public static Tag emptyTag() {
        return new Tag("NO_TAG", 0);
    }

    public String getLabel() {
        return label;
    }

    public int getImportance() {
        return importance;
    }
}
