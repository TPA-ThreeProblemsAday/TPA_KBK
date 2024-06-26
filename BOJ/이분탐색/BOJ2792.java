import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2792 {

    static int N, M;
    static int[] arr;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        arr = new int[M];

        int lo = 1, hi = 0, mid = 0;

        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
            hi = Math.max(hi, arr[i]);
        }

        while (lo <= hi) {
            mid = (lo + hi) / 2;

            if (check(mid)) {
                min = Math.min(min, mid);
                hi = mid - 1;
            } else
                lo = mid + 1;
        }

        System.out.println(min);
    }

    static boolean check(int mid) {
        // 나눠줄 수 있는 학생 수
        int num = 0;

        for (int i = 0; i < M; i++) {
            num += arr[i] / mid;
            if (arr[i] % mid > 0)
                num++;
        }

        // 보석을 다 나눠줄 수 있는지 여부
        return N >= num;
    }

}
