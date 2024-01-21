import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ16234 {
    static int N;
    static int L, R;
    static int[][] map;

    static int sum;
    static boolean[][] visited;
    static List<Node> unit = new ArrayList<>();

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        R = Integer.parseInt(input[2]);

        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            input = br.readLine().split(" ");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(input[c]);
            }
        }

        while (true) {
            boolean flag = false;
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c]) {
                        unit.clear();
                        unit.add(new Node(r, c));

                        sum = map[r][c];
                        visited[r][c] = true;

                        dfs(r, c);

                        if (unit.size() == 1)
                            continue;

                        for (Node node : unit) {
                            map[node.row][node.col] = sum / unit.size();
                            flag = true;
                        }
                    }
                }
            }

            if (!flag)
                break;

            ans++;
        }

        System.out.println(ans);

    }

    static void dfs(int r, int c) {
        int nr = 0, nc = 0;
        for (int d = 0; d < 4; d++) {
            nr = r + dr[d];
            nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc])
                continue;

            int cur = Math.abs(map[r][c] - map[nr][nc]);

            if (cur < L || cur > R)
                continue;

            sum += map[nr][nc];
            visited[nr][nc] = true;
            unit.add(new Node(nr, nc));
            dfs(nr, nc);
        }
    }

    static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
