import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10942_2 {

    // 수열의 크기, 질문의 수
    static int N, M;

    // 숫자
    static int[] num;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        num = new int[N + 1];
        dp = new int[N + 1][N + 1];

        StringTokenizer tokenizer = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }

        // 1자리 수
        for (int i = 1; i <= N; i++)
            dp[i][i] = 1;

        // 2자리 수
        for (int i = 1; i <= N - 1; i++)
            dp[i][i + 1] = num[i] == num[i + 1] ? 1 : 0;

        // 3자리 이상 수
        for (int start = N - 1; start >= 1; start--) {
            for (int end = start + 2; end <= N; end++) {
                dp[start][end] = num[start] == num[end] && dp[start + 1][end - 1] == 1 ? 1 : 0;
            }
        }

        M = Integer.parseInt(br.readLine());
        int S = 0, E = 0;
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine(), " ");
            S = Integer.parseInt(tokenizer.nextToken());
            E = Integer.parseInt(tokenizer.nextToken());

            sb.append(dp[S][E] + "\n");
        }

        System.out.println(sb);
    }

}