package baseball;

import nextstep.utils.Console;
import nextstep.utils.GameRule;

/**
 * @author Choi InJoo <rinjyu@naver.com>
 * @version 1.0
 * @since 1.0
 */
public class Application {

    private static String[] computerNumbers;

    public static void main(String[] args) {
        gameView();
    }

    /**
     * 컴퓨터가 입력한 숫자 생성
     * @return 컴퓨터가 입력한 숫자들
     */
    private static String[] generateComputerNumbers() {
        computerNumbers = new Computer().getNumbers();
        return computerNumbers;
    }

    /**
     * 게임 화면
     */
    public static void gameView() {
        String gameProcessType;
        generateComputerNumbers();
        do {
            System.out.print("숫자를 입력해주세요 : ");
            String[] userNumbers = new User().getNumbers();
            gameProcessType = getGameProcessType(userNumbers);
        } while (!GameProcessType.GAME_OVER.getKey().equals(gameProcessType));
    }

    /**
     * 게임 진행 상태
     * @param userNumbers 사용자가 입력한 숫자들
     * @return 게임 프로세스 유형값
     */
    public static String getGameProcessType(String[] userNumbers) {
        String gameProcessType = GameProcessType.GAME_RETRY.getKey();
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
    public static String setGameProcessType() {
        String gameProcessType = Console.readLine();
        while (!GameRule.isInputGameProcessTypeValid(gameProcessType)) {
            System.out.println("[ERROR] 잘못된 입력값입니다.");
            System.out.println("게임을 새로 시작하려면 1, 게임을 종료하려면 2를 입력하세요.");
            gameProcessType = Console.readLine();
        }
        if (GameProcessType.GAME_RETRY.getKey().equals(gameProcessType)) {
            generateComputerNumbers();
        }
        return gameProcessType;
    }
}
