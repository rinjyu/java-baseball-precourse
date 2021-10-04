package nextstep.utils;

import baseball.GameProcessType;
import baseball.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class GameRuleTest {

    private static final int MAX_LENGTH = 3;

    @ParameterizedTest
    @DisplayName("입력한 숫자 길이 확인")
    @ValueSource(strings = {"", " ", "1", "12", "123", "1234"})
    void 입력한_숫자_길이_확인(String data) {
        assertAll(
                () -> assertNotNull(data),
                () -> assertEquals(MAX_LENGTH, data.length(), "입력한 숫자 길이 오류")
        );
    }

    @ParameterizedTest
    @DisplayName("입력한 문자가 숫자(1~9)인지 여부")
    @ValueSource(strings = { "0", "1", "12#", "*", "123", "1230" })
    void 입력한_문자가_숫자인지_여부(String data) {
        assertTrue(Pattern.matches("^[1-9]*$", data), "1~9의 숫자로 이루어지지 않음");
    }

    @ParameterizedTest
    @DisplayName("숫자 중복 여부1")
    @CsvSource({"1:2:3", "1:2:2", "1:2:1", "2:2:2"})
    void 숫자_중복_여부1(String data) {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(data.split(":")));
        String[] dataArray = hashSet.toArray(new String[0]);

        assertEquals(MAX_LENGTH, String.join("", dataArray).length(), "중복된 숫자 존재");
    }

    @ParameterizedTest
    @DisplayName("숫자 중복 여부2")
    @CsvSource({"1:2:3,1", "1:3:5,2"})
    void 숫자_중복_여부2(String data, String number) {
        String[] dataArray = data.split(":");
        Arrays.sort(dataArray);

        assertFalse(Arrays.binarySearch(dataArray, number) > -1, "중복된 숫자 존재");
    }

    @ParameterizedTest
    @DisplayName("사용자가 입력한 숫자 유효성 검사 : 숫자 길이, 범위")
    @ValueSource(strings = {"", " ", "1", "12", "120", "123", "1234"})
    void 사용자가_입력한_숫자_유효성_검사_숫자_길이와_범위(String number) {
        assertAll(
                () -> assertNotNull(number),
                () -> assertEquals(MAX_LENGTH, number.length(), "입력한 숫자 길이 오류"),
                () -> assertTrue(Pattern.matches("^[1-9]*$", number), "1~9의 숫자로 이루어지지 않음")
        );
    }

    @ParameterizedTest
    @DisplayName("사용자가 입력한 숫자 유효성 검사 : 중복된 숫자 검사")
    @ValueSource(strings = {"111", "123", "121", "133"})
    void 사용자가_입력한_숫자_유효성_검사_중복된_숫자_검사(String number) {
        assertEquals(MAX_LENGTH, String.join("", new HashSet<>(Arrays.asList(number.split(""))).toArray(new String[0])).length(), "중복된 숫자 존재");
    }

    @ParameterizedTest
    @DisplayName("컴퓨터가 입력한 숫자 유효성 검사")
    @CsvSource({"1:2:3,1", "1:3:5,2"})
    void 컴퓨터가_입력한_숫자_유효성_검사(String data, String number) {
        String[] dataArray = data.split(":");
        String[] tempArray = dataArray.clone();
        Arrays.sort(tempArray);
        assertFalse(Arrays.binarySearch(tempArray, number) > -1, "중복된 숫자 존재");
    }

    @ParameterizedTest
    @DisplayName("힌트 생성하기 : 스트라이크, 볼 건수")
    @CsvSource({"1:2:3,1:4:5", "1:2:3,5:4:1", "1:2:3,1:4:5", "1:2:3,5:2:1", "1:2:3,1:2:3"})
    void 힌트_생성하기(String computerNumber, String userNumber) {
        int strikeCount = 0;
        int ballCount = 0;
        String[] computerNumbers = computerNumber.split(":");
        String[] userNumbers = userNumber.split(":");

        for (int i = 0; i < userNumbers.length; i++) {
            strikeCount += GameRule.strikeCount(computerNumbers, userNumbers, i);
            ballCount += GameRule.ballCount(computerNumbers, userNumbers, i);
        }

        GameResult gameResult = new GameResult(strikeCount, ballCount);
        System.out.println(gameResult);
    }

    @ParameterizedTest
    @DisplayName("스트라이크 건수")
    @CsvSource({"1:2:3,1:4:5,0", "1:2:3,5:4:1,2"})
    void 스트라이크_건수(String computerNumber, String userNumber, int currentIndex) {
        String[] computerNumbers = computerNumber.split(":");
        String[] userNumbers = userNumber.split(":");
        computerNumber = String.join("", computerNumbers);
        assertEquals(computerNumber.indexOf(userNumbers[currentIndex]), currentIndex, "스트라이크 건수 0");
    }

    @ParameterizedTest
    @DisplayName("볼 건수")
    @CsvSource({"1:2:3,1:4:5,1", "1:2:3,5:2:1,2"})
    void 볼_건수(String computerNumber, String userNumber, int currentIndex) {
        String[] computerNumbers = computerNumber.split(":");
        String[] userNumbers = userNumber.split(":");
        computerNumber = String.join("", computerNumbers);
        assertTrue(computerNumber.contains(userNumbers[currentIndex])
                && computerNumber.indexOf(userNumbers[currentIndex]) != currentIndex, "볼 건수 0");
    }

    @ParameterizedTest
    @DisplayName("게임 결과 메시지")
    @CsvSource({"0,0", "3,0", "0,3", "2,1"})
    void 게임_결과_메시지(int strikeCount, int ballCount) {
        assertAll(
                () -> assertTrue(strikeCount == 0 && ballCount == 0, "낫싱 : strikeCount와 ballCount는 0이어야 함"),
                () -> assertTrue(strikeCount > 0 && ballCount == 0, "N스트라이크 : strikeCount는 0보다 커야하고, ballCount는 0이어야 함"),
                () -> assertTrue(strikeCount == 0 && ballCount > 0, "N볼 : strikeCount는 0, ballCount는 0보다 커야 함"),
                () -> assertTrue(strikeCount > 0 && ballCount > 0, "N스트라이크 N볼 : strikeCount와 ballCount는 0보다 커야함")
        );
    }

    @ParameterizedTest
    @DisplayName("게임 종료 여부")
    @ValueSource(ints = { 0, 1, 2, 3 })
    void 게임_종료_여부(int strikeCount) {
        assertEquals(MAX_LENGTH, strikeCount, "게임 계속 진행");
    }

    @ParameterizedTest
    @DisplayName("게임 종료, 재시작 입력 숫자 검증")
    @ValueSource(strings = { "0", "1", "2", "3" })
    void 게임_종료_여부(String retry) {
        assertTrue(GameProcessType.GAME_RETRY.getKey().equals(retry) || GameProcessType.GAME_OVER.getKey().equals(retry), "1, 2만 입력 가능");
    }
}