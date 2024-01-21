import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2294 {

    static int[] dp;

    static int N, K;

    static int max_value = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        dp = new int[K + 1];
        Arrays.fill(dp, max_value);
        dp[0] = 0;

        int cost = 0;
        for (int i = 0; i < N; i++) {
            cost = Integer.parseInt(reader.readLine());

            for (int j = cost; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j - cost] + 1);
            }
        }

        if(dp[K] == max_value) System.out.println(-1);
        else System.out.println(dp[K]);
    }
}
