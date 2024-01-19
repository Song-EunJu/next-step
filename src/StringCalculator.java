import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DEFAULT_DELIMITER = "[:,]";
    private static final Pattern DEFAULT_DELIMITER_REGEX = Pattern.compile("[:,]");
    private static final Pattern CUSTOM_DELIMITER_REGEX = Pattern.compile("//(.*?)\n");

    public int add(String target) {
        // 커스텀 구분자가 있는 경우
        if(hasCustomDelimiter(target)) {
            return sumSplitByCustomDelimiter(target);
        }

        // 기본 구분자가 있는 경우
        if(hasDefaultDelimiter(target)) {
            return sumSplitByDelimiter(DEFAULT_DELIMITER, target);
        }

        int num = getNumber(target);
        if(num < 0)
            throw new RuntimeException("음수가 존재합니다.");
        return num;
    }

    // 커스텀 구분자 패턴 존재 여부 판별
    public boolean hasCustomDelimiter(String target) {
        Matcher matcher = CUSTOM_DELIMITER_REGEX.matcher(target);
        return matcher.find();
    }

    // 기본 구분자 존재 여부 판별
    public boolean hasDefaultDelimiter(String target) {
        Matcher matcher = DEFAULT_DELIMITER_REGEX.matcher(target);
        return matcher.find();
    }

    public int getNumber(String target) {
        return target.isBlank() ? 0 : Integer.valueOf(target);
    }

    // 특정 구분자로 계산하는 경우
    public int sumSplitByDelimiter(String delimiter, String target) {
        int sum = 0;
        String[] targetNumbers = target.split(delimiter);
        for (String number : targetNumbers) {
            try {
                int num = getNumber(number);
                if((!delimiter.equals("-") && num < 0) || num < 0)
                    throw new RuntimeException("음수가 존재합니다.");
                sum += num;
            } catch (NumberFormatException e) {
                throw new RuntimeException("기본 구분자도, 커스텀 구분자도 아닌 구분자가 존재합니다.");
            }
        }
        return sum;
    }

    public int sumSplitByCustomDelimiter(String target) {
        String customDelimiter = getCustomDelimiter(target);
        String targetStr = target.substring(target.indexOf("\n")+1);
        return sumSplitByDelimiter(customDelimiter, targetStr);
    }

    // 커스텀 구분자 뽑아내기
    public String getCustomDelimiter(String target) {
        Matcher matcher = CUSTOM_DELIMITER_REGEX.matcher(target);
        String customDelimiter = "";
        while (matcher.find()) {
            customDelimiter = matcher.group(1);
        }
        return customDelimiter.equals("+") ? "\\+" : customDelimiter;
    }
}
