import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629 {
    static int A, B, mod;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        A = Integer.parseInt(tokenizer.nextToken());
        B = Integer.parseInt(tokenizer.nextToken());
        mod = Integer.parseInt(tokenizer.nextToken());

        System.out.println(solution(B));
    }

    // 시간 복잡도 = log 2의 20억
    static long solution(long num) {
        // 기저 사례
        if (num == 1)
            return A % mod;

        // 곱한 것을 변수에 담아둠
        long val = solution(num / 2);

        // 값이 오버 플로우 되는 것을 방지하기 위해 모듈러 연산
        val = val * val % mod;

        // 홀수면 A를 한번더 곱해줘야 함
        if ((num & 1) > 0)
            return val * A % mod;

        return val;
    }
}
