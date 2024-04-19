import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9996 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        String pattern = reader.readLine();
        int index = pattern.indexOf("*");

        // 패턴 * 전후 구하기
        String a = pattern.substring(0, index);
        String b = pattern.substring(index + 1);

        StringBuilder builder = new StringBuilder();
        String input = null;
        outer: while (N-- > 0) {
            input = reader.readLine();

            try {
                if (input.length() < pattern.length() - 1)
                    throw new RuntimeException();

                for (int i = 0; i < a.length(); i++) {
                    if (a.charAt(i) != input.charAt(i)) {
                        throw new RuntimeException();
                    }
                }

                for (int i = 0; i < b.length(); i++) {
                    if (b.charAt(b.length() - i - 1) != input.charAt(input.length() - i - 1)) {
                        throw new RuntimeException();
                    }
                }
            } catch (RuntimeException e) {
                builder.append("NE\n");
                continue outer;
            }

            builder.append("DA\n");

        }

        System.out.println(builder);

    }

}
