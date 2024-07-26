import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ12852 {
    static int N;
    static int INF = (int) 1e9;
    static int[] dp, trace;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        trace = new int[N + 1];

        Arrays.fill(dp, INF);

        dp[0] = dp[1] = 0;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            trace[i] = i - 1;

            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                trace[i] = i / 3;
            }
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                trace[i] = i / 2;
            }
        }
        StringBuilder sb = new StringBuilder();

        sb.append(dp[N]).append("\n");

        int now = N;
        while (now != 0) {
            sb.append(now).append(" ");
            now = trace[now];
        }

        System.out.println(sb);
    }
}
