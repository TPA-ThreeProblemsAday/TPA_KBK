import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2583 {

    // 모눈종이 세로, 가로
    static int row, col;

    // 직사각형 개수
    static int K;
    static int rx, ry, lx, ly;

    static boolean[][] map;
    static boolean[][] visited;

    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        row = Integer.parseInt(tokenizer.nextToken());
        col = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        map = new boolean[row][col];
        while (K-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            // 왼쪽 아래
            lx = Integer.parseInt(tokenizer.nextToken());
            ly = Integer.parseInt(tokenizer.nextToken());

            // 오른쪽 위
            rx = Integer.parseInt(tokenizer.nextToken());
            ry = Integer.parseInt(tokenizer.nextToken());

            for (int r = ly; r < ry; r++) {
                for (int c = lx; c < rx; c++) {
                    map[r][c] = true;
                }
            }
        }

        visited = new boolean[row][col];
        list = new ArrayList<>();

        int zone = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (!map[r][c] && !visited[r][c]) {
                    // 탐색 시작
                    bfs(r, c);
                    zone++;
                }
            }
        }

        Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        builder.append(zone + "\n");
        for (int area : list)
            builder.append(area + " ");

        System.out.println(builder);
    }

    static Queue<int[]> queue;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static void bfs(int r, int c) {
        queue = new ArrayDeque<>();
        queue.add(new int[] { r, c });
        visited[r][c] = true;
        int area = 1;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node[0] + dr[d];
                int nc = node[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= row || nc >= col)
                    continue;
                if (visited[nr][nc] || map[nr][nc])
                    continue;

                area++;
                visited[nr][nc] = true;
                queue.add(new int[] { nr, nc });
            }
        }

        list.add(area);
    }
}
