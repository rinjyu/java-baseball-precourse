package baseball;

public class Application {
    public static void main(String[] args) {
        gameView();
    }

    public static void gameView() {
        System.out.print("숫자를 입력해주세요 : ");
        String[] userNumbers = new User().getNumbers();
        String[] computerNumbers = new Computer().getNumbers();
    }
}
