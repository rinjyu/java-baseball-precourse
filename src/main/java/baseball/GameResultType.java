package baseball;

public enum GameResultType {

    STRIKE("strike", "스트라이크"),
    BALL("ball", "볼"),
    NOTHING("nothing", "낫싱");

    final private String key;

    final private String value;

    private GameResultType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
