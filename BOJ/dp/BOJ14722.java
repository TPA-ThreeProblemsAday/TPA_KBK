import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ14722 {

    static int N;
    static int[][] map;
    static int[][][] dp;

    static final int STRAWBERRY = 0, CHOCOLATE = 1, BANANA = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());

        map = new int[N][N];
        dp = new int[N][N][3];

        StringTokenizer tokenizer = null;
        for (int r = 0; r < N; r++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            for (int c = 0; c < N; c++)
                map[r][c] = Integer.parseInt(tokenizer.nextToken());
        }

        if (map[0][0] == STRAWBERRY)
            dp[0][0][STRAWBERRY] = 1;

        int milk = 0;
        for (int i = 1; i < N; i++) {
            milk = map[0][i];
            dp[0][i][STRAWBERRY] = milk == STRAWBERRY ? dp[0][i - 1][BANANA] + 1 : dp[0][i - 1][STRAWBERRY];

            dp[0][i][CHOCOLATE] = milk == CHOCOLATE && dp[0][i][BANANA] < dp[0][i][STRAWBERRY]
                    ? dp[0][i - 1][STRAWBERRY] + 1
                    : dp[0][i - 1][CHOCOLATE];

            dp[0][i][BANANA] = milk == BANANA && dp[0][i][STRAWBERRY] < dp[0][i][CHOCOLATE]
                    ? dp[0][i - 1][CHOCOLATE] + 1
                    : dp[0][i - 1][BANANA];
        }

        for (int i = 1; i < N; i++) {
            milk = map[i][0];
            dp[i][0][STRAWBERRY] = milk == STRAWBERRY ? dp[i - 1][0][BANANA] + 1 : dp[i - 1][0][STRAWBERRY];
            dp[i][0][CHOCOLATE] = milk == CHOCOLATE && dp[i][0][BANANA] < dp[i][0][STRAWBERRY]
                    ? dp[i - 1][0][STRAWBERRY] + 1
                    : dp[i - 1][0][CHOCOLATE];
            dp[i][0][BANANA] = milk == BANANA && dp[i][0][STRAWBERRY] < dp[i][0][CHOCOLATE]
                    ? dp[i - 1][0][CHOCOLATE] + 1
                    : dp[i - 1][0][BANANA];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                milk = map[i][j];
                dp[i][j][STRAWBERRY] = milk == STRAWBERRY
                        ? Math.max(dp[i][j - 1][BANANA] + 1, dp[i - 1][j][BANANA] + 1)
                        : Math.max(dp[i - 1][j][STRAWBERRY], dp[i][j - 1][STRAWBERRY]);

                dp[i][j][CHOCOLATE] = milk == CHOCOLATE && dp[i][j][BANANA] < dp[i][j][STRAWBERRY]
                        ? Math.max(dp[i][j - 1][STRAWBERRY] + 1, dp[i - 1][j][STRAWBERRY] + 1)
                        : Math.max(dp[i - 1][j][CHOCOLATE], dp[i][j - 1][CHOCOLATE]);

                dp[i][j][BANANA] = milk == BANANA && dp[i][j][STRAWBERRY] < dp[i][j][CHOCOLATE]
                        ? Math.max(dp[i][j - 1][CHOCOLATE] + 1, dp[i - 1][j][CHOCOLATE] + 1)
                        : Math.max(dp[i - 1][j][BANANA], dp[i][j - 1][BANANA]);
            }
        }

        System.out.println(Math.max(dp[N - 1][N - 1][STRAWBERRY],
                Math.max(dp[N - 1][N - 1][CHOCOLATE], dp[N - 1][N - 1][BANANA])));

    }

}
