import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10942_1 {

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

        for (int i = 1; i <= N; i++)
            num[i] = Integer.parseInt(tokenizer.nextToken());

        for (int end = 1; end <= N; end++) {
            for (int start = 1; start <= end; start++) {
                // 한자리 수는 무조건 팰린드롬
                if (start == end)
                    dp[start][end] = 1;
                // 두자리 수는 앞 뒤 수가 동일하면 팰린드롬
                else if (end - start == 1)
                    dp[start][end] = (num[start] == num[end]) ? 1 : 0;
                // 세자리 이상 수부터는 앞 뒤 수 체크 및 사이 수들이 팰린드롬인지 확인
                else {
                    dp[start][end] = (num[end] == num[start] && dp[start + 1][end - 1] == 1) ? 1 : 0;
                }
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
