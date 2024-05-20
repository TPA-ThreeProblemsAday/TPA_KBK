import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ14497 {

    static int N, M;
    static int r1, c1, r2, c2;
    static int[][] map;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        input = reader.readLine().split(" ");

        r1 = Integer.parseInt(input[0]) - 1;
        c1 = Integer.parseInt(input[1]) - 1;
        r2 = Integer.parseInt(input[2]) - 1;
        c2 = Integer.parseInt(input[3]) - 1;

        map = new int[N][M];

        String temp = null;

        for (int i = 0; i < N; i++) {
            temp = reader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        System.out.println(solution());

    }

    static int solution() {
        boolean[][] visited = new boolean[N][M];
        Deque<int[]> queue = new ArrayDeque<>();
        Deque<int[]> temp = null;

        queue.add(new int[] { r1, c1 });
        visited[r1][c1] = true;

        int ans = 0;
        outer: while (true) {

            ans++;
            temp = new ArrayDeque<>();

            while (!queue.isEmpty()) {
                int[] here = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = here[0] + dr[d];
                    int nc = here[1] + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                        continue;
                    if (visited[nr][nc])
                        continue;

                    if (nr == r2 && nc == c2)
                        break outer;

                    visited[nr][nc] = true;
                    if (map[nr][nc] != 0) {
                        temp.add(new int[] { nr, nc });
                        map[nr][nc] = 0;
                    } else
                        queue.add(new int[] { nr, nc });
                }
            }

            queue = temp;
        }

        return ans;
    }

}
