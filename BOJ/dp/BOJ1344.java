import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1344 {
    static int N = 18;
    static double A, B;
    static double[][][] dp;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = (double) Integer.parseInt(br.readLine()) / 100;
        B = (double) Integer.parseInt(br.readLine()) / 100;

        isPrime = new boolean[N + 1];

        init();

        dp = new double[N + 1][N + 1][N + 1];
        for (int i = 0; i <= 18; i++) {
            for (int j = 0; j <= 18; j++)
                Arrays.fill(dp[i][j], -1);
        }

        System.out.println(dfs(0, 0, 0));
    }

    static void init() {

        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= (int) Math.sqrt(N); i++) {

            if (!isPrime[i])
                continue;

            for (int j = i + i; j <= N; j += i)
                isPrime[j] = false;
        }
    }

    static double dfs(int turn, int a, int b) {

        if (turn == N)
            return isPrime[a] || isPrime[b] ? 1.0 : 0.0;

        if (dp[turn][a][b] > -1)
            return dp[turn][a][b];

        dp[turn][a][b] = 0.0;
        
        dp[turn][a][b] += dfs(turn + 1, a + 1, b + 1) * A * B;
        dp[turn][a][b] += dfs(turn + 1, a + 1, b) * A * (1 - B);
        dp[turn][a][b] += dfs(turn + 1, a, b + 1) * (1 - A) * B;
        dp[turn][a][b] += dfs(turn + 1, a, b) * (1 - A) * (1 - B);

        return dp[turn][a][b];
    }

}