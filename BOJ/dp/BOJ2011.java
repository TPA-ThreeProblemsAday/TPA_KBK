import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2011 {
    static BufferedReader reader;
    static int N;
    static int[] num;
    static int[] dp;

    static int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        N = input.length();

        num = new int[N];
        dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            num[i] = input.charAt(i) - '0';
        }

        // 암호 해석 불가
        if (num[0] == 0) {
            System.out.println(0);
            return;
        }

        // 기저 조건
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            if (num[i - 1] != 0) {
                dp[i] += dp[i - 1] % MOD;
            }

            int temp = num[i - 2] * 10 + num[i - 1];

            if (10 <= temp && temp <= 26) {
                dp[i] += dp[i - 2] % MOD;
            }
        }

        System.out.println(dp[N] % MOD);
    }
}
