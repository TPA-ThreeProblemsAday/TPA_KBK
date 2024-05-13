import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16234 {
    static int N, L, R;

    static int[][] map;
    static boolean[][] visited;
    static List<int[]> union;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        L = Integer.parseInt(tokenizer.nextToken());
        R = Integer.parseInt(tokenizer.nextToken());

        map = new int[N][N];
        union = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        while (true) {
            boolean flag = false;
            visited = new boolean[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    if (visited[r][c]) continue;

                    union.clear();
                    
                    int people = dfs(r, c);

                    if (union.size() == 1) continue;

                    people /= union.size();
                    
                    for (int[] loc : union) 
                        map[loc[0]][loc[1]] = people;
                    
                    flag = true;
                }
            }

            if (!flag) break;
            result++;

        }

        System.out.println(result);
    }

    static int dfs(int r, int c) {
        int ret = map[r][c];

        visited[r][c] = true;
        union.add(new int[] { r, c });

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            if (visited[nr][nc]) continue;

            int temp = Math.abs(map[r][c] - map[nr][nc]);

            if (temp < L || temp > R) continue;

            ret += dfs(nr, nc);
        }

        return ret;

    }

}
