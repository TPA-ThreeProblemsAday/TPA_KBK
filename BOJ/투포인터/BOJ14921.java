import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14921 {
    static int N;
    static int[] arr;
    static int min = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(reader.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = N - 1;

        while (start < end) {
            int value = arr[start] + arr[end];

            // 기록
            if (Math.abs(min) > Math.abs(value))
                min = value;

            if (value < 0) {
                start++;
            } else if (value > 0) {
                end--;
            } else {
                min = 0;
                break;
            }
        }

        System.out.println(min);
    }
}
