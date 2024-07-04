import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7795 {
    static int T, N, M;
    static int[] A, B;

    static int lo, mid, hi, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(reader.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(reader.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            B = new int[M];
            ans = 0;

            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B);

            for (int i = 0; i < N; i++) {
                lo = 0;
                hi = M - 1;

                int ret = -1;

                while (lo <= hi) {
                    mid = (lo + hi) / 2;

                    if (B[mid] >= A[i]) {
                        hi = mid - 1;
                    } else {
                        ret = mid;
                        lo = mid + 1;
                    }
                }

                ans += ret + 1;

            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);

    }
}
