import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int sumSplitByNoneDelimiter(String target) {
        return target.isBlank() ? 0 : Integer.valueOf(target);
    }

    public int sumSplitByDefaultDelimiter(String delimiter, String target) {
        int sum = 0;
        String[] targetNumbers = target.split(delimiter);
        for (String number : targetNumbers) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }

    public int sumSplitByCustomDelimiter(String target) {
        String pattern = "//(.*?)\n";

        // 패턴과 입력 문자열을 사용하여 매칭 수행
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(target);

        // 매칭된 결과 출력
        String customDelimiter = "";
        while (matcher.find()) {
            customDelimiter = matcher.group(1);
        }

        String targetExceptDelimiter = target.substring(target.indexOf("\n")+1);
        return sumSplitByDefaultDelimiter(customDelimiter, targetExceptDelimiter);
    }
}
