import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ1012 {

    static BufferedReader reader;

    static int R, C;
    // 배추밭
    static int[][] map;

    // 방문 체크
    static boolean[][] visited;

    // 지렁이
    static int worm;

    static Queue<Pair> queue = new ArrayDeque<>();

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());

        String[] input = null;
        int cab = 0, cabR = 0, cabC = 0;
        while (T > 0) {
            input = reader.readLine().split(" ");
            C = Integer.parseInt(input[0]);
            R = Integer.parseInt(input[1]);
            cab = Integer.parseInt(input[2]);

            map = new int[R][C];
            visited = new boolean[R][C];

            for (int i = 0; i < cab; i++) {
                input = reader.readLine().split(" ");
                cabC = Integer.parseInt(input[0]);
                cabR = Integer.parseInt(input[1]);

                map[cabR][cabC] = 1;
            }

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (!visited[r][c] && map[r][c] == 1) {
                        worm++;
                        visited[r][c] = true;
                        queue.add(new Pair(r, c));

                        bfs();
                    }
                }
            }

            sb.append(worm + "\n");
            // 초기화
            worm = 0;

            T--;
        }

        System.out.println(sb);
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;
                if (visited[nr][nc] || map[nr][nc] != 1)
                    continue;

                visited[nr][nc] = true;
                queue.add(new Pair(nr, nc));
            }
        }
    }

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
