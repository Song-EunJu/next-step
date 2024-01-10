import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static void main(String[] args) {
        String testStr = "//;\n1;2;3";

        String regex = "//[^\n]*\n";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(testStr);
        if(matcher.find()){
            // 커스텀 구분자 패턴이 있는 경우
        }
        else {
            // 기본 구분자가 있는 경우
            if(testStr.contains(":") || testStr.contains(",")){

            }
        }
    }
}
