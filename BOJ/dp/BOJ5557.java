import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5557 {
    static BufferedReader reader;

    // 숫자 개수
    static int N;
    // 숫자 배열
    static int[] num;
    // 숫자 합 담아놓을 배열
    static long[][] dp;
    // 답
    static long ans;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        num = new int[N];

        // 경우의 수 저장 배열
        dp = new long[N][21];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        for (int idx = 0; idx < N; idx++) {
            num[idx] = Integer.parseInt(tokenizer.nextToken());
        }

        // 현재 수 계산 결과
        int temp = 0;

        // 첫번째 숫자 경우의 수 1가지
        dp[0][num[0]] = 1;
        for (int idx = 1; idx < N - 1; idx++) {
            for (int sum = 0; sum < 21; sum++) {
                // 이전 계산 결과가 있을 때
                if (dp[idx - 1][sum] > 0) {
                    // 이전 수에 덧셈
                    temp = sum + num[idx];
                    if (range(temp))
                        // 값 누적
                        dp[idx][temp] += dp[idx - 1][sum];

                    // 이전 수에 뺄셈
                    temp = sum - num[idx];
                    if (range(temp))
                        dp[idx][temp] += dp[idx - 1][sum];
                }
            }
        }

        System.out.println(dp[N - 2][num[N - 1]]);
    }

    static boolean range(int sum) {
        return 0 <= sum && sum <= 20;
    }

    // static long solution(int idx, int sum) {

    // if (sum < 0 || sum > 20)
    // return 0;

    // // 끝에서 2번째 수
    // if (idx == N - 2) {
    // // 계산 결과가 맞을 때
    // if (sum == num[N - 1])
    // return 1;
    // else
    // return 0;
    // }

    // // 현재까지 경우의 수
    // int cur = dp[idx][sum];

    // cur += solution(idx + 1, sum - num[idx]);
    // cur += solution(idx + 1, sum + num[idx]);

    // return cur;
    // }

}
