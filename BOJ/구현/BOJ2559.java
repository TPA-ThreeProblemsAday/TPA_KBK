import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ2559 {

    // 전체 날짜
    static int N;

    // 연속적인 날짜의 수
    static int K;

    // 온도 누적합 담기
    static int[] pSum;

    // 최대 온도 합
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        pSum = new int[N + 1];

        tokenizer = new StringTokenizer(reader.readLine(), " ");
        // 누적합 계산
        for (int i = 1; i <= N; i++) {
            pSum[i] = pSum[i - 1] + Integer.parseInt(tokenizer.nextToken());
        }

        int temp = 0;
        for (int i = K; i <= N; i++) {
            temp = pSum[i] - pSum[i - K];
            max = Math.max(temp, max);
        }

        System.out.println(max);

    }

}
