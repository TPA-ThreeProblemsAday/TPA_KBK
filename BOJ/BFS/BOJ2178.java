import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2178 {
    static BufferedReader reader;
    // 거리 저장 배열
    static int[][] dist;
    // 미로
    static int[][] map;

    // 미로 row, col
    static int row, col;

    public static void main(String[] args) throws IOException{
        init();

        bfs();

        System.out.println(dist[row - 1][col - 1]);
    }

    static void init() throws IOException{
        reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        row = Integer.parseInt(input[0]);
        col = Integer.parseInt(input[1]);

        dist = new int[row][col];
        map = new int[row][col];

        String temp = null;
        for (int r = 0; r < row; r++) {
            temp = reader.readLine();
            for (int c = 0; c < col; c++) {
                map[r][c] = temp.charAt(c) - '0';
                dist[r][c] = -1;
            }
        }
    }

    static void bfs() throws IOException{
        Queue<Pair> queue = new LinkedList<>();

        // 방문 표시
        dist[0][0] = 1;
        queue.add(new Pair(0, 0));

        int[] dr = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];

                if (nr < 0 || nc < 0 || nr >= row || nc >= col)
                    continue;

                if (dist[nr][nc] > -1 || map[nr][nc] == 0)
                    continue;

                dist[nr][nc] = dist[cur.row][cur.col] + 1;
                queue.add(new Pair(nr, nc));
            }
        }

    }

    static class Pair {
        int row;
        int col;

        public Pair(int r, int c) {
            row = r;
            col = c;
        }
    }
}
