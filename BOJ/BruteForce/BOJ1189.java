import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1189 {

    static int R, C, K;

    static char[][] map;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        String input = null;
        for (int i = 0; i < R; i++) {
            input = reader.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        visited[R - 1][0] = true;
        System.out.println(go(1, R - 1, 0));
    }

    static int go(int idx, int r, int c) {

        if (idx == K) {
            if (r == 0 && c == C - 1)
                return 1;
            return 0;
        }

        int ret = 0;

        int nr = 0, nc = 0;
        for (int d = 0; d < 4; d++) {
            nr = r + dr[d];
            nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                continue;
            if (visited[nr][nc] || map[nr][nc] == 'T')
                continue;

            visited[nr][nc] = true;

            ret += go(idx + 1, nr, nc);

            visited[nr][nc] = false;
        }

        return ret;
    }

}
