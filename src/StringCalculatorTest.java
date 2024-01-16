import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    private StringCalculator calculator;

    @Before
    public void setup() {
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("구분자가 없는 문자열에서, 숫자의 합을 반환한다.")
    public void nonDelimiter() {
        assertEquals(0, calculator.add(""));
        assertEquals(0, calculator.add(" "));
        assertEquals(100, calculator.add("100"));
    }

    @Test
    @DisplayName("기본 구분자 쉼표 (,) 로 구분된 숫자의 합을 반환한다.")
    public void separatedByDefaultDelimiter() {
        assertEquals(6, calculator.add("1,2,3"));
        assertEquals(5, calculator.add(",2,3"));
        assertEquals(5, calculator.add("2,,3"));
    }

    @Test
    @DisplayName("기본 구분자 쉼표 (,) 와 콜론 (:) 으로 구분된 숫자의 합을 반환한다.")
    public void separatedByDefaultDelimiters() {
        assertEquals(6, calculator.add("1,2:3"));
    }

    @Test
    @DisplayName("커스텀 구분자로 구분된 숫자의 합을 반환한다.")
    public void separatedByCustomDelimiter() {
        assertEquals(6, calculator.add("//;\n1;2;3"));
    }

    @Test
    @DisplayName("기본 구분자도 아니고, 커스텀 구분자도 아닌 구분자가 나온 경우 예외 처리한다.")
    public void separatedByExceptionDelimiter() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                calculator.add("//#\n1;2#3")
        );
        assertEquals("기본 구분자도, 커스텀 구분자도 아닌 구분자가 존재합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("문자열에 음수가 있는 경우, 예외 처리한다.")
    public void checkNegativeValue() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->
                calculator.add("//;\n1;-1;3")
        );
        assertEquals("음수가 존재합니다.", exception.getMessage());
    }

    // TODO : 커스텀 구분자와 기본 구분자가 섞여있는 경우
}
