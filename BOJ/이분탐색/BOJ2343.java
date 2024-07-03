import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2343 {
    static int N, M;
    static int[] arr;

    static int lo, mid, hi, min = 100_001, ret = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(reader.readLine());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            hi += arr[i];
            lo = Math.max(lo, arr[i]);
        }

        while (lo <= hi) {
            mid = (lo + hi) / 2;

            int cnt = getCnt(mid);

            if (cnt <= M) {
                hi = mid - 1;
            } else
                lo = mid + 1;
        }

        System.out.println(lo);
    }

    static int getCnt(int mid) {
        int cnt = 0, temp = 0;

        for (int i = 0; i < N; i++) {
            if (temp + arr[i] > mid) {
                cnt++;
                temp = 0;
            }
            temp += arr[i];
        }

        if (temp != 0)
            cnt++;

        return cnt;
    }

}
