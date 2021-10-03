package baseball;

import nextstep.utils.Console;
import nextstep.utils.GameRule;

/**
 * @author Choi InJoo <rinjyu@naver.com>
 * @version 1.0
 * @since 1.0
 */
public class Application {
    public static void main(String[] args) {
        gameView();
    }

    /**
     * 게임 화면
     */
    public static void gameView() {
        int gameProcessType;
        do {
            System.out.print("숫자를 입력해주세요 : ");
            String[] userNumbers = new User().getNumbers();
            String[] computerNumbers = new Computer().getNumbers();
            gameProcessType = getGameProcessType(computerNumbers, userNumbers);
        } while (gameProcessType != GameProcessType.GAME_OVER.getKey());
    }

    /**
     * 게임 진행 상태
     * @param computerNumbers 컴퓨터가 입력한 숫자들
     * @param userNumbers 사용자가 입력한 숫자들
     * @return 게임 프로세스 유형값
     */
    public static int getGameProcessType(String[] computerNumbers, String[] userNumbers) {
        int gameProcessType = GameProcessType.GAME_RETRY.getKey();
        GameResult gameResult = GameRule.generateHint(computerNumbers, userNumbers);
        System.out.println(GameRule.gameResultMessage(gameResult.getStrikeCount(), gameResult.getBallCount()));
        if (GameRule.gameOver(gameResult.getStrikeCount())) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
            System.out.println("게임을 새로 시작하려면 1, 게임을 종료하려면 2를 입력하세요.");
            gameProcessType = setGameProcessType();
        }

        return gameProcessType;
    }

    /**
     * 사용자가 입력한 게임 재시작, 종료값 설정
     * @return 사용자가 입력한 값
     */
    public static int setGameProcessType() {
        String gameProcessType;
        do {
            System.err.println("[ERROR] 잘못입력하셨습니다.\n게임을 새로 시작하려면 1, 게임을 종료하려면 2를 입력하세요.");
            gameProcessType = Console.readLine();
        } while (!GameRule.numberPattern(gameProcessType) || GameRule.gameProcessType(Integer.parseInt(gameProcessType)));

        return Integer.parseInt(gameProcessType);
    }
}
