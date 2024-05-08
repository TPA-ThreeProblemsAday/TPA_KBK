import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ2636_1 {
    static int R, C;
    static int[][] map;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static boolean[][] visited;

    // 모두 녹는데 걸리는 시간
    static int time;
    // 모두 녹기 한 시간 전 칸의 개수
    static int count;

    public static void main(String[] args) throws IOException {
        init();
        solution();

        System.out.println(time);
        System.out.println(count);
    }

    static void solution() {

        while (true) {
            visited = new boolean[R][C];

            bfs();

            time++;

            int cnt = checkMap();

            if (cnt == 0)
                break;
            else
                count = cnt;
        }
    }

    static Deque<int[]> queue;

    static void bfs() {
        queue = new ArrayDeque<>();
        visited[0][0] = true;
        queue.add(new int[] { 0, 0 });

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node[0] + dr[d];
                int nc = node[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;
                if (visited[nr][nc])
                    continue;

                visited[nr][nc] = true;

                // 1이면 모서리 치즈 녹이기
                if (map[nr][nc] == 1)
                    map[nr][nc] = 2;
                else // 0이면 queue에 추가
                    queue.add(new int[] { nr, nc });
            }
        }

    }

    static int checkMap() {
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1)
                    cnt++;
            }
        }

        return cnt;
    }

    static void init() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());

                // 처음 치즈 개수
                if (map[i][j] == 1)
                    count++;
            }
        }
    }
}
