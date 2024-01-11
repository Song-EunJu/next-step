import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    private StringCalculator calculator;

    @Before
    public void setup(){
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("기본 구분자 쉼표 (,) 와 콜론 (:) 으로 구분된 숫자의 합을 반환한다.")
    public void separatedByDefaultDelimiter() {
        assertEquals(6, calculator.calculate("1,2,3"));
    }

    @Test
    @DisplayName("커스텀 구분자로 구분된 숫자의 합을 반환한다.")
    public void separatedByCustomDelimiter(){
        assertEquals(6, calculator.calculate("//;\n1;2;3"));
    }
}
