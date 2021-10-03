package nextstep.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.regex.Pattern;

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
     * 사람이 입력한 숫자 유효성 검사
     * @param number 유효성을 검사할 데이터
     * @return 유효한 숫자인지의 여부(true / false)
     */
    public static boolean personInputNumberValid(String number) {
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
}