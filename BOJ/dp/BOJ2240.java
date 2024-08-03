import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2240 {

    static int T, W;
    static int[][][] dp;

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        T = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);

        // T초, W값, 위치 상태 표기
        dp = new int[T + 1][W + 2][3];
        arr = new int[T + 1];

        for (int i = 1; i <= T; i++)
            arr[i] = Integer.parseInt(reader.readLine());

        for (int t = 1; t <= T; t++) {
            // w 값 - 1부터 시작
            for (int w = 1; w <= W + 1; w++) {

                if (arr[t] == 1) {
                    // 그대로 있는 것 또는 움직여서 자두를 받은 것
                    dp[t][w][1] = Math.max(dp[t - 1][w][1], dp[t - 1][w - 1][2]) + 1;
                    // 그대로 있는 것 또는 움직인 것
                    dp[t][w][2] = Math.max(dp[t - 1][w][2], dp[t - 1][w - 1][1]);
                } else {
                    if (t == 1 && w == 1)
                        continue;
                    dp[t][w][1] = Math.max(dp[t - 1][w][1], dp[t - 1][w - 1][2]);
                    dp[t][w][2] = Math.max(dp[t - 1][w][2], dp[t - 1][w - 1][1]) + 1;
                }
            }
        }

        int result = 0;
        for (int w = 1; w <= W + 1; w++) {
            result = Math.max(result, Math.max(dp[T][w][1], dp[T][w][2]));
        }

        System.out.println(result);
    }

}