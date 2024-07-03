import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17124 {
    static int T, N, M;
    static int[] A, B;

    static int lo, hi, mid;

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

            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            // 오름차순 정렬
            Arrays.sort(B);

            long ans = 0;
            for (int i = 0; i < N; i++) {
                // 절댓값 최소 차이
                int min = 987654321;
                int num = 0;

                lo = 0;
                hi = M - 1;

                while (lo <= hi) {
                    mid = (lo + hi) / 2;

                    int temp = Math.abs(A[i] - B[mid]);

                    // 갭이 작을 떄
                    if (min > temp) {
                        num = B[mid];
                        min = temp;
                    }
                    // 같은 갭일 땐 더 작은 수
                    else if (min == temp) {
                        num = Math.min(num, B[mid]);
                    }

                    if (temp == 0)
                        break;

                    if (A[i] - B[mid] > 0) {
                        lo = mid + 1;
                    } else if (A[i] - B[mid] < 0) {
                        hi = mid - 1;
                    }

                }

                ans += num;
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
