package baseball;

import nextstep.utils.Console;
import nextstep.utils.GameRule;

/**
 * @author Choi InJoo <rinjyu@naver.com>
 * @version 1.0
 * @since 1.0
 */
public class User {

    private String[] numbers;

    public User() {
        setNumber();
    }

    public String[] getNumbers() {
        return numbers;
    }

    private void setNumber() {
        String number = Console.readLine();
        while (!GameRule.userInputNumberValid(number)) {
            System.err.print("[ERROR] 잘못된 입력값입니다.\n숫자를 입력해주세요 : ");
            number = Console.readLine();
        }
        this.numbers = number.split("");
    }
}
