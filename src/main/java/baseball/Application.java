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
    }
}
