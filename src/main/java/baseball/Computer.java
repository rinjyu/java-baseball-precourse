package baseball;

import nextstep.utils.Randoms;

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
            String number = String.valueOf(Randoms.pickNumberInRange(1, 9));
            numberArray[i] = number;
        }
        this.numbers = numberArray;
    }
}
