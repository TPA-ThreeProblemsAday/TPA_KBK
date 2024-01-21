import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1106 {

    static int[] dp;
    static int C, N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        C = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);

        dp = new int[C + 101];
        Arrays.fill(dp, 987654321);
        dp[0] = 0;

        int people = 0, pay = 0;
        for (int t = 0; t < N; t++) {
            input = reader.readLine().split(" ");
            pay = Integer.parseInt(input[0]);
            people = Integer.parseInt(input[1]);

            for (int i = people; i <= C + 100; i++) {
                dp[i] = Math.min(dp[i], dp[i - people] + pay);
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = C; i <= C + 100; i++)
            result = Math.min(result, dp[i]);

        System.out.println(result);
    }

}
