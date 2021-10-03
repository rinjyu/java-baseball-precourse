package baseball;

import nextstep.utils.Console;

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
        this.numbers = number.split("");
    }
}
