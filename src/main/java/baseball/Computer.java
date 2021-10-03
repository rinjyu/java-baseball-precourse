package baseball;

import nextstep.utils.GameRule;
import nextstep.utils.Randoms;

/**
 * @author Choi InJoo <rinjyu@naver.com>
 * @version 1.0
 * @since 1.0
 */
public class Computer {

    private String[] numbers;

    public Computer() {
        setNumber();
    }

    public String[] getNumbers() {
        return numbers;
    }

    private void setNumber() {
        String[] numberArray = {"0", "0", "0"};
        for (int i = 0; i < numberArray.length; i++) {
            String number = rotateRandom(numberArray);
            numberArray[i] = number;
        }
        this.numbers = numberArray;
    }

    private static String rotateRandom(String[] numberArray) {
        String number;
        do {
            number = String.valueOf(Randoms.pickNumberInRange(1, 9));
        } while (GameRule.computerInputNumberValid(numberArray, number));

        return number;
    }
}
