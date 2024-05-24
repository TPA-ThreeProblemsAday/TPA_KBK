import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14620 {
    static int N, nr, nc;
    static int[][] map;

    static boolean[][] visited;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int INF = Integer.MAX_VALUE, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        StringTokenizer tokenizer = null;
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        go(0, 1, 0);

        System.out.println(min);

    }

    static void go(int idx, int here, int price) {

        // 가지치기
        if (price >= min)
            return;

        if (idx == 3) {
            min = Math.min(min, price);
            return;
        }

        for (int i = here; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (!good(i, j))
                    continue;

                int temp = plant(i, j, true);
                go(idx + 1, i, price + temp);
                plant(i, j, false);
            }
        }

    }

    static boolean good(int row, int col) {

        if (visited[row][col])
            return false;

        for (int d = 0; d < 4; d++) {
            nr = row + dr[d];
            nc = col + dc[d];

            if (visited[nr][nc])
                return false;
        }

        return true;
    }

    static int plant(int row, int col, boolean flag) {

        int ret = map[row][col];

        visited[row][col] = flag;

        for (int d = 0; d < 4; d++) {
            nr = row + dr[d];
            nc = col + dc[d];

            ret += map[nr][nc];
            visited[nr][nc] = flag;
        }

        return ret;
    }

}
