import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ14391 {

    static int[][] paper;
    static int N, M;
    static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        paper = new int[N][M];

        String temp = null;
        for (int i = 0; i < N; i++) {
            temp = reader.readLine();
            for (int j = 0; j < M; j++) {
                paper[i][j] = temp.charAt(j) - '0';
            }
        }

        // 0은 가로, 1은 세로
        for (int s = 0; s < (1 << (N * M)); s++) {
            int sum = 0;

            for (int i = 0; i < N; i++) {
                int cur = 0;

                for (int j = 0; j < M; j++) {
                    // 현재 요소
                    int ret = i * M + j;

                    // 가로 요소
                    if ((s & (1 << ret)) == 0) {
                        cur = cur * 10 + paper[i][j];
                    } else { // 가로 요소가 아닐 때,
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }

            for (int j = 0; j < M; j++) {
                int cur = 0;

                for (int i = 0; i < N; i++) {
                    int ret = i * M + j;

                    // 세로 요소
                    if ((s & (1 << ret)) > 0) {
                        cur = cur * 10 + paper[i][j];
                    } else { // 세로 요소가 아닐 때
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }

            max = Math.max(max, sum);

        }

        System.out.println(max);
    }

}
