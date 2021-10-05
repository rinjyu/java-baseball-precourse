package baseball;

/**
 * @author Choi InJoo <rinjyu@naver.com>
 * @version 1.0
 * @since 1.0
 */
public enum GameResultType {

    STRIKE("strike", "스트라이크"),
    BALL("ball", "볼"),
    NOTHING("nothing", "낫싱");

    final private String key;

    final private String value;

    GameResultType(String key, String value) {
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
