import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12100 {
    static int N;
    static int max = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        int[][] map = new int[N][N];

        StringTokenizer tokenizer = null;
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        dfs(0, map);

        System.out.println(max);

    }

    static void dfs(int depth, int[][] map) {

        if (depth == 5) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] temp = move(map);

            dfs(depth + 1, temp);

            map = rotate(map);
        }
    }

    static int[][] rotate(int[][] map) {
        int[][] copy = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = map[N - j - 1][i];
            }
        }
        return copy;
    }

    static int[][] move(int[][] map) {
        int[][] copy = new int[N][N];

        for (int i = 0; i < N; i++) {
            // column 값
            int c = -1;
            // 합칠 수 있는지 여부
            boolean flag = false;

            for (int j = 0; j < N; j++) {

                // 현재 들어온 값이 0이면 패스
                if (map[i][j] == 0)
                    continue;

                // 합칠 수 있고, 이전 값과 현재 들어온 값이 일치하면 합치기
                if (flag && map[i][j] == copy[i][c]) {
                    copy[i][c] *= 2;
                    flag = false;
                } else { // 아니면 현재 값 넣기
                    copy[i][++c] = map[i][j];
                    // 합칠 수 있음
                    flag = true;
                }
                // 최댓값 비교
                max = Math.max(max, copy[i][c]);
            }
        }

        return copy;
    }

}
