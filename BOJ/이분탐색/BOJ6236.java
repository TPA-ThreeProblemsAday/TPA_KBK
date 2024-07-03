import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ6236 {

    static int N, M;
    static int[] arr;

    static int left, mid, right, ret;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
            right += arr[i];
            left = Math.max(left, arr[i]);
        }

        while (left <= right) {
            mid = (left + right) / 2;

            int cnt = getCount(mid);

            if (cnt <= M) {
                ret = mid;
                right = mid - 1;
            } else if (cnt > M) {
                left = mid + 1;
            }
        }

        System.out.println(ret);
    }

    static int getCount(int mid) {
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
