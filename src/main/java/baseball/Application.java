package baseball;

import nextstep.utils.GameRule;

public class Application {
    public static void main(String[] args) {
        gameView();
    }

    public static void gameView() {
        System.out.print("숫자를 입력해주세요 : ");
        String[] userNumbers = new User().getNumbers();
        String[] computerNumbers = new Computer().getNumbers();
        GameResult gameResult = GameRule.generateHint(computerNumbers, userNumbers);
        System.out.println(GameRule.gameResultMessage(gameResult.getStrikeCount(), gameResult.getBallCount()));
        if (GameRule.gameOver(gameResult.getStrikeCount())) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
            System.out.println("게임을 새로 시작하려면 1, 게임을 종료하려면 2를 입력하세요.");
        }
    }
}
