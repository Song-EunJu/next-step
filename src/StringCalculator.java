import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int calculate(String target) {
        // 커스텀 구분자도 있고, 기본 구분자도 있는 경우
        if(hasCustomDelimiter(target) && hasDefaultDelimiter(target)) {

        }

        // 커스텀 구분자만 있는 경우
        if(hasCustomDelimiter(target)) {
            // 1개만 있을 경우

            // 2개 이상일 경우
        }

        // 기본 구분자만 있는 경우
        if(hasDefaultDelimiter(target)) {

        }

        // 구분자가 없는 경우 (커스텀 구분자, 기본 구분자 둘다 X)
        if(!(hasCustomDelimiter(target) || hasDefaultDelimiter(target))) {
            return target.isBlank() ? 0 : Integer.valueOf(target);
        }
        return 0;
    }

    // 커스텀 구분자가 여러 개인 경우를 판별
    public boolean hasCustomDelimiters(String target) {
        String customDelimiterRegex = "//[^\n]*\n";

        Pattern pattern = Pattern.compile(customDelimiterRegex);
        Matcher matcher = pattern.matcher(target);

        while(matcher.find()) {
            String found = matcher.group();
            System.out.println("Found: " + found);
        }
        return matcher.find();
    }

    // 커스텀 구분자 패턴 존재 여부 판별 메서드
    public boolean hasCustomDelimiter(String target) {
        String customDelimiterRegex = "//[^\n]*\n";
        Pattern pattern = Pattern.compile(customDelimiterRegex);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    // 기본 구분자 존재 여부 판별 메서드
    public boolean hasDefaultDelimiter(String target) {
        String defaultDelimiterRegex = "[:,]";
        Pattern pattern = Pattern.compile(defaultDelimiterRegex);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }
}
