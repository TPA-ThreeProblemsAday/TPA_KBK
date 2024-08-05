import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4811 {

    static int N;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true) {

            N = Integer.parseInt(br.readLine());

            if (N == 0)
                break;

            dp = new long[N + 1][N + 1];

            sb.append(go(N, 0)).append("\n");
        }

        System.out.println(sb);
    }

    static long go(int w, int h) {

        if (w == 0)
            return 1;

        if (dp[w][h] > 0)
            return dp[w][h];

        dp[w][h] += go(w - 1, h + 1);

        if (h > 0)
            dp[w][h] += go(w, h - 1);

        return dp[w][h];
    }
}
