import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9996_1 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        String pattern = reader.readLine();

        int index = pattern.indexOf("*");

        String pre = pattern.substring(0, index);
        String suffix = pattern.substring(index + 1);

        StringBuilder builder = new StringBuilder();

        while (N-- > 0) {
            String input = reader.readLine();

            // 반례 체크 : 사이즈, 최대, 최소
            if (input.length() < pre.length() + suffix.length()) {
                builder.append("NE\n");
                continue;
            }

            if (pre.equals(input.substring(0, pre.length()))
                    && suffix.equals(input.substring(input.length() - suffix.length())))
                builder.append("DA\n");
            else
                builder.append("NE\n");

        }

        System.out.println(builder);

    }

}
