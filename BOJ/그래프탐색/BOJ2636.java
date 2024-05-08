import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2636 {

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

            dfs(0, 0);

            time++;

            int cnt = checkMap();

            if (cnt == 0)
                break;
            else
                count = cnt;
        }
    }

    static void dfs(int i, int j) {
        visited[i][j] = true;

        // 모서리에 있는 치즈, 탐색 stop
        if (map[i][j] == 1) {
            map[i][j] = 2;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C || visited[nr][nc])
                continue;
            dfs(nr, nc);
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
