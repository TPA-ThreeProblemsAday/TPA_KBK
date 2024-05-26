import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시간복잡도 : 2의 20승으로 해결
public class BOJ1285 {

    static int N;
    static int[] coin;

    static int ret = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        coin = new int[N + 1];

        String input = null;
        for (int i = 1; i <= N; i++) {
            input = reader.readLine();

            for (int j = 0; j < N; j++) {
                if (input.charAt(j) == 'T')
                    coin[i] |= (1 << j);
            }
        }

        go(1);

        System.out.println(ret);
    }

    static void go(int here) {

        // 뒤집기 끝
        if (here == N + 1) {

            int sum = 0;

            // 0 ~ N - 1자리까지
            for (int i = 1; i <= (1 << (N - 1)); i <<= 1) {

                // T의 갯수
                int cnt = 0;

                // 현재 열의 T 갯수 확인
                for (int j = 1; j <= N; j++) {
                    if ((coin[j] & i) > 0)
                        cnt++;
                }

                // 정해진 행에 대한 열의 최적해 (뒤집거나 아니거나)
                sum += Math.min(cnt, N - cnt);
            }

            ret = Math.min(ret, sum);
            return;
        }

        // 뒤집기 X
        go(here + 1);

        // 뒤집기
        coin[here] = ~coin[here];
        go(here + 1);

    }

}
