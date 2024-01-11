import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int calculate(String target) {
        if(hasCustomDelimiter(target)){
            // 커스텀 구분자 패턴이 있는 경우
        }
        else {
            // 기본 구분자가 있는 경우
        }
        return 0;
    }

    // 커스텀 구분자 패턴 존재 여부 판별 메서드
    public boolean hasCustomDelimiter(String target) {
        String customDelimiterRegex = "//[^\n]*\n";
        Pattern pattern = Pattern.compile(customDelimiterRegex);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }

    // 기본 구분자 존재 여부 판별 메서드
    public boolean hasDefaultDelimiter(String target) {
        String defaultDelimiterRegex = "[:,]";
//        Pattern pattern = Pattern.compile();
//        Matcher matcher = pattern.matcher(target);
//        return matcher.find();
        return true;
    }
}
