package baseball;

import nextstep.utils.Console;
import nextstep.utils.GameRule;

import java.util.Arrays;
import java.util.Objects;

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
        while (!GameRule.isUserInputNumberValid(number)) {
            System.out.println("[ERROR] 잘못된 입력값입니다.");
            System.out.print("숫자를 입력해주세요 : ");
            number = Console.readLine();
        }
        this.numbers = number.split("");
    }

    @Override
    public String toString() {
        return "User [numbers=" + (!Objects.isNull(numbers)? Arrays.toString(numbers): "") + "]";
    }
}
