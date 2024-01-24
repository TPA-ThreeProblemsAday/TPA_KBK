import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3474 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());

        for (int t = 0; t < T; t++) {
            int ans = 0;
            int num = Integer.parseInt(reader.readLine());

            int ret2 = 0, ret5 = 0;
            for (int i = 2; i <= num; i *= 2) {
                ret2 = num / i;
            }

            for (int j = 5; j <= num; j *= 5) {
                ret5 += num / j;
            }

            ans = Math.min(ret2, ret5);
            sb.append(ret5 + "\n");
        }

        System.out.println(sb);

    }
}
