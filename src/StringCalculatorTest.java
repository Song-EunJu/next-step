import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    private StringCalculator calculator;
    private static final String DEFAULT_DELIMITER = "[:,]";

    @Before
    public void setup(){
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("구분자가 없는 문자열에서, 숫자의 합을 반환한다.")
    public void nonDelimiter() {
        assertEquals(0, calculator.getNumber(""));
        assertEquals(0, calculator.getNumber(" "));
        assertEquals(100, calculator.getNumber("100"));
    }

    @Test
    @DisplayName("기본 구분자 쉼표 (,) 로 구분된 숫자의 합을 반환한다.")
    public void separatedByDefaultDelimiter() {
        assertEquals(6, calculator.sumSplitByDefaultDelimiter(",", "1,2,3"));
        assertEquals(5, calculator.sumSplitByDefaultDelimiter(",", ",2,3"));
        assertEquals(5, calculator.sumSplitByDefaultDelimiter(",", "2,,3"));
    }

    @Test
    @DisplayName("기본 구분자 쉼표 (,) 와 콜론 (:) 으로 구분된 숫자의 합을 반환한다.")
    public void separatedByDefaultDelimiters() {
        assertEquals(6, calculator.sumSplitByDefaultDelimiter(DEFAULT_DELIMITER, "1,2:3"));
    }

    @Test
    @DisplayName("커스텀 구분자로 구분된 숫자의 합을 반환한다.")
    public void separatedByCustomDelimiter(){
        assertEquals(6, calculator.sumSplitByCustomDelimiter("//;\n1;2;3"));
    }

    // TODO : 커스텀 구분자와 기본 구분자가 섞여있는 경우

    @Test
    @DisplayName("문자열에 음수가 있는 경우, 예외 처리한다.")
    public void checkNegativeValue(){
        assertEquals("음수는 존재할 수 없습니다", calculator.sumSplitByCustomDelimiter("//;\n1;2;3"));
    }
}
