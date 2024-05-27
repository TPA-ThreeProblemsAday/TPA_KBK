import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1987 {

    static int R, C;

    static char[][] map;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new char[R][C];

        String temp = null;
        for (int i = 0; i < R; i++) {
            temp = reader.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        dfs(1, 0, 0, 1 << (map[0][0] - 'A'));

        System.out.println(max);

    }

    static void dfs(int depth, int row, int col, int value) {

        max = Math.max(max, depth);

        for (int d = 0; d < 4; d++) {
            int nr = row + dr[d];
            int nc = col + dc[d];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                continue;

            int temp = (1 << (map[nr][nc] - 'A'));

            if ((value & temp) > 0)
                continue;

            dfs(depth + 1, nr, nc, value | temp);
        }

    }
}
