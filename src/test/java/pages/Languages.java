package pages;

public enum Languages {
    EN("EN"), ES("ES"), FR("FR"), CN("CN"), UA("UA");
    private String language;

    private Languages(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return language;
    }
}
