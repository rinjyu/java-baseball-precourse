package baseball;

/**
 * @author Choi InJoo <rinjyu@naver.com>
 * @version 1.0
 * @since 1.0
 */
public enum GameProcessType {

    GAME_RETRY(1, "게임 재시작"),
    GAME_OVER(2, "게임 종료");

    final private int key;

    final private String value;

    private GameProcessType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
