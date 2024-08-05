import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2240_TD {

    static int T, W, result;
    static int[][][] dp;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        T = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);

        dp = new int[T][W + 1][2];
        arr = new int[T];

        for (int i = 0; i < T; i++)
            arr[i] = Integer.parseInt(br.readLine());

        // 0초일 때 1에서 시작, 0초에서 2에서 시작
        System.out.println(Math.max(go(0, 0, 0), go(0, 1, 1)));
    }

    static int go(int t, int w, int cur) {
        if (w > W)
            return -(int) 1e9;

        if (t == T)
            return 0;

        if (dp[t][w][cur] > 0)
            return dp[t][w][cur];

        dp[t][w][cur] = Math.max(go(t + 1, w, cur), go(t + 1, w + 1, cur ^ 1));

        int loc = arr[t] - 1;

        if (loc == cur)
            dp[t][w][cur] += 1;

        return dp[t][w][cur];
    }

}
