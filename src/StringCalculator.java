import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DEFAULT_DELIMITER_REGEX = "[:,]";
    private static final Pattern DEFAULT_DELIMITER_PATTERN = Pattern.compile(DEFAULT_DELIMITER_REGEX);
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.*?)\n");

    public int add(String target) {
        // 커스텀 구분자가 존재하는 경우
        if(hasCustomDelimiter(target)) {
            return sumSplitByCustomDelimiter(target);
        }

        // 기본 구분자가 존재하는 경우
        if(hasDefaultDelimiter(target)) {
            return sumSplitByDelimiter(DEFAULT_DELIMITER, target);
        }

        // 커스텀 구분자, 기본 구분자 모두 존재하지 않는 경우
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

    // 구분자로 구분한 각 숫자 반환
    public int getNumber(String target) {
        if(target.isBlank())
            return 0;
        try {
            int num = Integer.valueOf(target);
            handleNegativeValue(num);
            return num;
        } catch (NumberFormatException e) {
            throw new RuntimeException("기본 구분자도, 커스텀 구분자도 아닌 구분자가 존재합니다.");
        }
    }

    public void handleNegativeValue(int num){
        if (num < 0)
            throw new RuntimeException("음수가 존재합니다.");
    }

    // 특정 구분자로 계산하는 경우
    public int sumSplitByDelimiter(String delimiter, String target) {
        int sum = 0;
        String[] targetNumbers = target.split(delimiter);
        for (String number : targetNumbers) {
            int num = getNumber(number);
            checkNegativeNumber(delimiter, num);
            sum += num;
        }
        return sum;
    }

    // 커스텀 구분자 정규식 생성
    public String getCustomDelimiterRegex(List<String> delimiters) {
        String customDelimiters = String.join("|", delimiters);
        return "[" + customDelimiters + "]";
    }

    // 커스텀 구분자 기준으로 나누어 합 반환
    public int sumSplitByCustomDelimiter(String target) {
        List<String> customDelimiters = getCustomDelimiters(target);
        String customDelimiterRegex = getCustomDelimiterRegex(customDelimiters);
        String targetStr = target.substring(target.lastIndexOf("\n")+1);
        return sumSplitByDelimiter(customDelimiterRegex, targetStr);
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
}
