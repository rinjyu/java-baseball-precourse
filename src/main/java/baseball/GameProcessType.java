package baseball;

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
