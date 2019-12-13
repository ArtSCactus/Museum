package view.enums;

public enum UIPlacement {
    TOP("Top"), RIGHT("Right"), LEFT("Left"), CENTER("Center"), BOTTOM("Bottom");
    private String name;

    UIPlacement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
