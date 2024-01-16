import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DEFAULT_DELIMITER = "[:,]";
    private static final String CUSTOM_DELIMITER = "//(.*?)\n";
    private static final Pattern DEFAULT_DELIMITER_REGEX = Pattern.compile("[:,]");
    private static final Pattern CUSTOM_DELIMITER_REGEX = Pattern.compile("//(.*?)\n");

    public int add(String target) {
        // 커스텀 구분자가 있는 경우
        if(hasCustomDelimiter(target)) {
            return sumSplitByCustomDelimiter(target);
        }

        // 기본 구분자가 있는 경우
        if(hasDefaultDelimiter(target)) {
            return sumSplitByDefaultDelimiter(DEFAULT_DELIMITER, target);
        }

        // 구분자가 없는 경우
        return target.isBlank() ? 0 : Integer.valueOf(target);
    }

    // 커스텀 구분자 패턴 존재 여부 판별 메서드
    public boolean hasCustomDelimiter(String target) {
        Matcher matcher = CUSTOM_DELIMITER_REGEX.matcher(target);
        return matcher.find();
    }

    // 기본 구분자 존재 여부 판별 메서드
    public boolean hasDefaultDelimiter(String target) {
        Matcher matcher = DEFAULT_DELIMITER_REGEX.matcher(target);
        return matcher.find();
    }

    // 구분자가 없는 경우
    public int getNumber(String target) {
        return target.isBlank() ? 0 : Integer.valueOf(target);
    }

    // 특정 구분자로 계산하는 경우
    public int sumSplitByDefaultDelimiter(String delimiter, String target) {
        int sum = 0;
        String[] targetNumbers = target.split(delimiter);
        for (String number : targetNumbers) {
            try {
                int num = getNumber(number);
                if(!delimiter.equals("-") && num < 0)
                    throw new RuntimeException("음수가 존재합니다.");
                sum += getNumber(number);
            } catch (NumberFormatException e) {
                throw new RuntimeException("기본 구분자도, 커스텀 구분자도 아닌 구분자가 존재합니다.");
            }
        }
        return sum;
    }

    // 커스텀 구분자로 계산하는 경우
    public int sumSplitByCustomDelimiter(String target) {
        String customDelimiter = getCustomDelimiter(target);
        String targetStr = target.substring(target.indexOf("\n")+1);
        return sumSplitByDefaultDelimiter(customDelimiter, targetStr);
    }

    // 커스텀 구분자 뽑아내기
    public String getCustomDelimiter(String target) {
        Matcher matcher = CUSTOM_DELIMITER_REGEX.matcher(target);

        String customDelimiter = "";
        while (matcher.find()) {
            customDelimiter = matcher.group(1);
        }
        return customDelimiter;
    }

    // 커스텀 구분자 이후 문자열 반환
    public String getTargetStrExceptCustomDelimiter(String target){
        return target.substring(target.indexOf("\n")+1);
    }

    // 음수 판별
//    public boolean checkNegativeValue(String target) {
//        // 커스텀 구분자에 - 가 없는데, 이후에 - 가 등장하는 경우
//        if(!getCustomDelimiter(target).equals("-") && getTargetStrExceptCustomDelimiter(target).contains("-")) {
//
//        }
//                return true;
//            return false;
//        }
//        // 커스텀 구분자에 - 가 있는 경우 -> 무조건 성립
//    }
//
}
