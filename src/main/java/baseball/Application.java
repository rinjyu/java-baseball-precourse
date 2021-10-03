package baseball;

import nextstep.utils.Console;
import nextstep.utils.GameRule;

public class Application {
    public static void main(String[] args) {
        gameView();
    }

    /**
     * 게임 화면
     */
    public static void gameView() {
        int retry;
        do {
            System.out.print("숫자를 입력해주세요 : ");
            String[] personNumbers = new User().getNumbers();
            String[] computerNumbers = new Computer().getNumbers();
            retry = getGameProcessType(computerNumbers, personNumbers);
        } while (retry != GameProcessType.GAME_OVER.getKey());
    }

    /**
     * 게임 진행 상태
     * @param computerNumbers
     * @param personNumbers
     * @return
     */
    public static int getGameProcessType(String[] computerNumbers, String[] personNumbers) {
        int retry = GameProcessType.GAME_RETRY.getKey();
        GameResult gameResult = GameRule.generateHint(computerNumbers, personNumbers);
        System.out.println(GameRule.gameResultMessage(gameResult.getStrikeCount(), gameResult.getBallCount()));
        if (GameRule.gameOver(gameResult.getStrikeCount())) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
            System.out.println("게임을 새로 시작하려면 1, 게임을 종료하려면 2를 입력하세요.");
            retry = setGameProcessType();
        }

        return retry;
    }

    /**
     * 사용자가 입력한 게임 재시작, 종료값 설정
     * @return
     */
    public static int setGameProcessType() {
        String inputRetry;
        do {
            System.err.println("[ERROR] 잘못입력하셨습니다.\n게임을 새로 시작하려면 1, 게임을 종료하려면 2를 입력하세요.");
            inputRetry = Console.readLine();
        } while (!GameRule.numberPattern(inputRetry) || GameRule.gameProcessType(Integer.parseInt(inputRetry)));

        return Integer.parseInt(inputRetry);
    }
}
