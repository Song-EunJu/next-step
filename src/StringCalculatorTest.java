import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    private StringCalculator calculator;

    @Before
    public void setup() {
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("기본 구분자 1개로 구분된 숫자의 합을 반환한다.")
    public void sumSplitBySingleDefaultDelimiter() {
        assertEquals(6, calculator.add("1,2,3"));
        assertEquals(6, calculator.add("1:2:3"));
    }

    @Test
    @DisplayName("기본 구분자 2개로 구분된 숫자의 합을 반환한다.")
    public void sumSplitByDefaultDelimiters() {
        assertEquals(6, calculator.add("1,2:3"));
    }

    @Test
    @DisplayName("커스텀 구분자 1개로 구분된 숫자의 합을 반환한다.")
    public void sumSplitBySingleCustomDelimiter() {
        assertEquals(6, calculator.add("//;\n1;2;3"));
    }

    @Test
    @DisplayName("커스텀 구분자 2개 이상으로 구분된 숫자의 합을 반환한다.")
    public void sumSplitByMultipleCustomDelimiters() {
        assertEquals(6, calculator.add("//#\n//@\n1#2@3"));
        assertEquals(10, calculator.add("//#\n//@\n//;\n1;2@3#4"));
    }

    @Test
    @DisplayName("구분자가 없는 문자열에서 숫자의 합을 반환한다.")
    public void sumWithoutDelimiter() {
        assertEquals(100, calculator.add("100"));
    }

    @Test
    @DisplayName("문자열 내에 공백이 존재하는 경우 숫자 0으로 간주하여 숫자의 합을 반환한다.")
    public void sumWithZeroForWhitespace() {
        assertEquals(0, calculator.add(""));
        assertEquals(0, calculator.add(" "));
        assertEquals(0, calculator.add(null));
        assertEquals(5, calculator.add(",2,3"));
        assertEquals(5, calculator.add(" ,2,3"));
        assertEquals(5, calculator.add("2,,3"));
        assertEquals(6, calculator.add("//-\n-1-2-3"));
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("기본 구분자도 아니고, 커스텀 구분자도 아닌 구분자가 나온 경우 예외 처리한다.")
    public void handleUnexpectedDelimiters() {
        calculator.add("//#\n1;2#3");
        calculator.add("//;\n1-1;3");
        calculator.add("#3,1");
    }

    @Test
    @DisplayName("문자열 내에 숫자가 존재하지 않는 경우 공백을 0으로 간주하여 합을 계산한다.")
    public void sumAssumingZeroForNoDigits() {
        assertEquals(0, calculator.add("//#\n#"));
    }

    @Test(expected = RuntimeException.class)
    @DisplayName("문자열 내에 음수가 존재하는 경우 RuntimeException 으로 예외처리한다.")
    public void handleNegativeNumbers() {
        calculator.add("//;\n1;-1;3");
        calculator.add("-1");
        calculator.add("1,2,-1");
        calculator.add("//+\n-1+2-3");
    }
}
