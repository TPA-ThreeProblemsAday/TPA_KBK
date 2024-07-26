import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9084 {
    static int T, N, M;
    static int[] coin;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(br.readLine());
            dp = new int[10001];

            dp[0] = 1;
            for (int i = 0; i < N; i++) {
                int coin = Integer.parseInt(st.nextToken());
                for (int j = coin; j <= M; j++) {
                    dp[j] += dp[j - coin];
                }
            }

            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb);

    }

}
