import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ2870 {

    static int N;
    static List<BigInteger> list;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        list = new ArrayList<>();

        while (N-- > 0) {
            String input = reader.readLine();

            String num = "";
            for (int i = 0; i < input.length(); i++) {
                char cur = input.charAt(i);

                // 숫자일 때,
                if (cur >= '0' && cur <= '9') {
                    num += cur;
                } else {
                    if (num.length() > 0)
                        list.add(new BigInteger(num));
                    num = "";
                }
            }

            if (num.length() > 0)
                list.add(new BigInteger(num));

        }

        Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        for (BigInteger num : list) {
            builder.append(num + "\n");
        }

        System.out.println(builder);

    }
}