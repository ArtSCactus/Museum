package view.enums;

public enum ColumnTypes {
    CARD("CARD"), FUND("CARD");
    private String value;

    ColumnTypes(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ColumnTypes{" +
                "value='" + value + '\'' +
                '}';
    }
}
