import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1915 {
    static int N, M, ans;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];

        if (N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        for (int i = 1; i <= N; i++) {
            String temp = br.readLine();
            for (int j = 1; j <= M; j++) {
                int now = temp.charAt(j - 1) - '0';

                if (i == 1 && j == 1)
                    dp[i][j] = now;
                else if (now == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        System.out.println(ans * ans);

    }
}
