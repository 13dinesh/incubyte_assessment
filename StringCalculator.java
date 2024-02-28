import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";

        if (numbers.startsWith("//")) {
            Matcher m = Pattern.compile("//(\\[.+\\])+\n(.+)").matcher(numbers);
            if (m.matches()) {
                List<String> delimiters = extractDelimiters(m.group(1));
                delimiter = String.join("|", delimiters);
                numbers = m.group(2);
            }
        }

        String[] nums = numbers.split(delimiter);

        int sum = 0;
        List<Integer> negatives = new ArrayList<>();
        for (String num : nums) {
            int n = Integer.parseInt(num);
            if (n < 0) {
                negatives.add(n);
            }
            if (n <= 1000) {
                sum += n;
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return sum;
    }

    private static List<String> extractDelimiters(String input) {
        List<String> delimiters = new ArrayList<>();
        Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(input);
        while (m.find()) {
            delimiters.add(Pattern.quote(m.group(1)));
        }
        return delimiters;
    }
}
