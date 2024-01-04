import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1629 {
    static BufferedReader reader;
    static long A, B, mod;

    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        A = Long.parseLong(input[0]);
        B = Long.parseLong(input[1]);
        mod = Long.parseLong(input[2]);

        System.out.println(solution(A, B));

    }

    static long solution(long a, long b) {

        if (b == 1)
            return a % mod;

        long val = solution(a, b / 2);
        val = val * val % mod;

        if (b % 2 == 0)
            return val;

        return val * a % mod;
    }
}
