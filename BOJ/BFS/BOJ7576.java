import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ7576 {
    static BufferedReader reader;

    // 박스 크기
    static int R, C;
    static int[][] box, dist;
    static Queue<Pair> queue;

    public static void main(String[] args) throws IOException {
        init();

        bfs();

        int max = 0;
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (dist[row][col] == -1 && box[row][col] != -1) {
                    System.out.println(-1);
                    System.exit(0);
                }
                max = Math.max(max, dist[row][col]);
            }
        }

        System.out.println(max);
    }

    static void init() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        queue = new LinkedList<>();

        String[] input = reader.readLine().split(" ");
        C = Integer.parseInt(input[0]);
        R = Integer.parseInt(input[1]);

        box = new int[R][C];
        dist = new int[R][C];

        for (int r = 0; r < R; r++) {
            input = reader.readLine().split(" ");
            for (int c = 0; c < C; c++) {
                box[r][c] = Integer.parseInt(input[c]);

                if (box[r][c] == 1) {
                    queue.add(new Pair(r, c));
                    dist[r][c] = 0;
                } else
                    dist[r][c] = -1;

            }
        }
    }

    static void bfs() {

        int[] dr = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;

                if (dist[nr][nc] > -1 || box[nr][nc] == -1)
                    continue;

                queue.add(new Pair(nr, nc));
                dist[nr][nc] = dist[cur.row][cur.col] + 1;
            }
        }
    }

    static class Pair {
        int row, col;

        public Pair(int r, int c) {
            row = r;
            col = c;
        }
    }
}
