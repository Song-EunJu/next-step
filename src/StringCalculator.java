import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DEFAULT_DELIMITER_REGEX = "[:,]";
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.*?)\n");

    public int add(String target) {
        if(isBlank(target))
            return 0;

        return sum(split(target));
    }

    private String[] split(String target){
        if(hasCustomDelimiter(target)) {
            return splitByCustomDelimiter(target);
        }

        return target.split(DEFAULT_DELIMITER_REGEX);
    }

    private boolean isBlank(String target) {
        return target == null || target.isBlank();
    }

    // 커스텀 구분자 패턴 존재 여부 판별
    public boolean hasCustomDelimiter(String target) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(target);
        return matcher.find();
    }

    // 구분자로 구분한 각 숫자 반환
    public int getPositiveNumber(String target) {
        if(isBlank(target))
            return 0;

        try {
            int num = Integer.parseInt(target);
            if (num < 0)
                throw new RuntimeException("음수가 존재합니다.");
            return num;
        } catch (NumberFormatException e) {
            throw new RuntimeException("기본 구분자도, 커스텀 구분자도 아닌 구분자가 존재합니다.");
        }
    }

    // 합을 계산하여 반환
    public int sum(String[] targetNumbers) {
        int sum = 0;
        for (String number : targetNumbers) {
            int num = getPositiveNumber(number);
            sum += num;
        }
        return sum;
    }

    // 커스텀 구분자 기준으로 문자열 분할
    public String[] splitByCustomDelimiter(String target) {
        String customDelimiterRegex = getCustomDelimiterRegex(getCustomDelimiters(target));
        String targetStr = target.substring(target.lastIndexOf("\n")+1);
        return targetStr.split(customDelimiterRegex);
    }

    // 커스텀 구분자 목록 반환
    public List<String> getCustomDelimiters(String target) {
        Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(target);
        List<String> customDelimiters = new ArrayList<>();
        while (matcher.find()) {
            String matchDelimiter = handlePlusEscape(matcher.group(1));
            customDelimiters.add(matchDelimiter);
        }
        return customDelimiters;
    }

    // 커스텀 구분자 정규식 생성
    public String getCustomDelimiterRegex(List<String> delimiters) {
        String customDelimiters = String.join("|", delimiters);
        return "[" + customDelimiters + "]";
    }

    public String handlePlusEscape(String matchDelimiter) {
        return matchDelimiter.equals("+") ? "\\+" : matchDelimiter;
    }
}
