package nextstep.utils;

import baseball.GameProcessType;
import baseball.GameResult;
import baseball.GameResultType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Choi InJoo <rinjyu@naver.com>
 * @version 1.0
 * @since 1.0
 */
public class GameRule {

    public static int MAX_LENGTH = 3;

    /**
     * 입력한 숫자 길이 확인
     * @param data 유효성을 검사할 데이터
     * @return 유효한 숫자길이인지의 여부(true / false)
     */
    public static boolean numberLength(String data) {
        if (Objects.isNull(data) || "".equals(data)) {
            return false;
        }

        return data.length() == MAX_LENGTH;
    }

    /**
     * 입력한 문자가 숫자(1~9)인지 여부
     * @param data 유효성을 검사할 데이터
     * @return 유효한 숫자 범위인지의 여부(true / false)
     */
    public static boolean numberPattern (String data) {
        return Pattern.matches("^[1-9]*$", data);
    }

    /**
     * 숫자 중복 여부
     * @param data 중복 검사할 데이터
     * @return 중복된 숫자가 존재하는 지의 여부(true / false)
     */
    public static boolean duplicateNumber(String[] data) {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(data));
        data = hashSet.toArray(new String[0]);

        return numberLength(String.join("", data));
    }

    /**
     * 숫자 중복 여부
     * @param data 중복 검사할 데이터
     * @return 중복된 숫자가 존재하는 지의 여부(true / false)
     */
    public static boolean duplicateNumber(String[] data, String number) {
        Arrays.sort(data);
        return Arrays.binarySearch(data, number) > -1;
    }

    /**
     * 사용자가 입력한 숫자 유효성 검사
     * @param number 유효성을 검사할 데이터
     * @return 유효한 숫자인지의 여부(true / false)
     */
    public static boolean userInputNumberValid(String number) {
        return numberLength(number) && numberPattern(number) && duplicateNumber(number.split(""));
    }

    /**
     * 컴퓨터가 입력한 숫자 유효성 검사
     * @param data 현재 배열 정보
     * @param number 유효성을 검사할 데이터
     * @return 유효한 숫자인지의 여부(true / false)
     */
    public static boolean computerInputNumberValid(String[] data, String number) {
        String[] tempArray = data.clone();
        return duplicateNumber(tempArray, number);
    }

    /**
     * 힌트 생성하기 : 스트라이크, 볼 건수
     * @param computerNumbers 컴퓨터가 입력한 숫자들
     * @param userNumbers 사용자가 입력한 숫자들
     * @return 스트라이크와 볼의 건수
     */
    public static GameResult generateHint(String[] computerNumbers, String[] userNumbers) {
        int strikeCount = 0;
        int ballCount = 0;

        for (int i = 0; i < userNumbers.length; i++) {
            strikeCount += strikeCount(computerNumbers, userNumbers, i);
            ballCount += ballCount(computerNumbers, userNumbers, i);
        }

        return new GameResult(strikeCount, ballCount);
    }

    /**
     * 스트라이크 건수
     * @param computerNumbers 컴퓨터가 입력한 숫자들
     * @param userNumbers 사용자가 입력한 숫자들
     * @param currentIndex 현재 인덱스
     * @return 스트라이크 건수
     */
    public static int strikeCount(String[] computerNumbers, String[] userNumbers, int currentIndex) {
        if (Arrays.asList(computerNumbers).equals(Arrays.asList(userNumbers))) {
            return MAX_LENGTH;
        }

        String computerNumber = String.join("", computerNumbers);
        if (computerNumber.indexOf(userNumbers[currentIndex]) == currentIndex) {
            return 1;
        }

        return 0;
    }

    /**
     * 볼 건수
     * @param computerNumbers 컴퓨터가 입력한 숫자들
     * @param userNumbers 사용자가 입력한 숫자들
     * @param currentIndex 현재 인덱스
     * @return 볼 건수
     */
    public static int ballCount(String[] computerNumbers, String[] userNumbers, int currentIndex) {
        String computerNumber = String.join("", computerNumbers);
        if (computerNumber.contains(userNumbers[currentIndex]) && computerNumber.indexOf(userNumbers[currentIndex]) != currentIndex) {
            return 1;
        }

        return 0;
    }

    /**
     * 게임 결과 메시지
     * @param strikeCount 스트라이크 건수
     * @param ballCount 볼 건수
     * @return 게임 결과 메시지
     */
    public static String gameResultMessage(int strikeCount, int ballCount) {
        if (strikeCount == 0 && ballCount == 0) {
            return GameResultType.NOTHING.getValue();
        }

        return ((strikeCount > 0) ? String.format("%s" + GameResultType.STRIKE.getValue(), strikeCount) : "")
                + ((ballCount > 0) ? (strikeCount > 0 ? " " : "") + String.format("%s" + GameResultType.BALL.getValue(), ballCount) : "");
    }

    /**
     * 게임 종료 여부
     * @param strikeCount 스트라이크 건수
     * @return 게임 종료 여부(true / false)
     */
    public static boolean gameOver(int strikeCount) {
        return strikeCount == MAX_LENGTH;
    }

    /**
     * 게임 종료, 재시작 입력 숫자 검증
     * @param retry 재시작
     * @return 유효한 게임 종료, 재시작 입력값인지의 여부(true / false)
     */
    public static boolean gameProcessType(int retry) {
        return retry == GameProcessType.GAME_RETRY.getKey() || retry == GameProcessType.GAME_OVER.getKey();
    }
}
