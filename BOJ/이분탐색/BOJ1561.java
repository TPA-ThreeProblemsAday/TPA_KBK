import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ1561 {

    static int N, M;
    static int[] arr;
    static long lo, mid, hi, temp, ret;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];

        st = new StringTokenizer(reader.readLine());

        for (int i = 0; i < M; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        if (N <= M) {
            System.out.println(N);
            return;
        }

        lo = 1;
        hi = 60000000004L;

        while (lo <= hi) {
            // 특정 시간이 mid일 때, N명 이상 태울 수 있는지
            mid = (lo + hi) / 2;

            if (check(mid)) {
                ret = mid;
                hi = mid - 1;
            } else
                lo = mid + 1;
        }

        // 태울 수 있는 인원 수
        temp = M;

        // 넉넉하게 구한 범위를 줄임
        for (int i = 0; i < M; i++)
            temp += (ret - 1) / arr[i];

        for (int i = 0; i < M; i++) {
            // 놀이기구가 끝남 -> 1명 더 태울 수 있음
            if (ret % arr[i] == 0)
                temp++;

            if (temp == N) {
                System.out.println(i + 1);
                return;
            }
        }
    }

    static boolean check(long mid) {
        temp = M;

        for (int i = 0; i < M; i++)
            temp += mid / arr[i];

        return temp >= N;
    }
}
