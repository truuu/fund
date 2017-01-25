package fund.dto;

public class Church {
    int value;
    String label;

    public Church() {
    }

    public Church(Code code) {
        this.value = code.id;
        this.label = code.codeName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
