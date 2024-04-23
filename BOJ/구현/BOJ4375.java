import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4375 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = null;

        int N = 0;
        StringBuilder builder = new StringBuilder();

        while ((input = reader.readLine()) != null) {
            N = Integer.parseInt(input);
            builder.append(solution(N) + "\n");
        }

        System.out.println(builder);

    }

    static int solution(int N) {
        int base = 1;
        // 자릿수
        int count = 1;

        while (base % N != 0) {
            // 모듈러 연산을 하지 않으면 시간 초과가 남
            // (a + b) % n = (a % n + b % n) % n
            // (a * b) % n = (a % n * b % n) % n
            // 마지막에 가서 모듈러 연산을 하는 것이 아니라 중간중간 모듈러 연산을 해도 동일한 값을 가짐
            base = (base * 10 + 1) % N;
            count++;
        }

        return count;
    }
}
