import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

public class BOJ2468 {
    static BufferedReader reader;

    // 사이즈
    static int N;
    static int[][] map;

    static boolean[][] visited;

    static int ans = 1;

    static SortedSet<Integer> set;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        map = new int[N][N];

        String[] input = null;

        set = new TreeSet<>();
        for (int row = 0; row < N; row++) {
            input = reader.readLine().split(" ");
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(input[col]);
                set.add(map[row][col]);
            }
        }

        set.remove(set.last());

        for (int height : set) {
            visited = new boolean[N][N];
            int sum = 0;

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    // 지대가 강수량보다 높음
                    if (map[row][col] > height && !visited[row][col]) {
                        visited[row][col] = true;
                        queue.add(new Node(row, col));
                        bfs(height);

                        sum++;
                    }
                }
            }
            ans = Math.max(ans, sum);
            height--;
        }

        System.out.println(ans);

    }

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static Deque<Node> queue = new ArrayDeque<>();

    static void bfs(int num) {
        int nr = 0, nc = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                nr = cur.row + dr[d];
                nc = cur.col + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                    continue;
                if (map[nr][nc] <= num || visited[nr][nc])
                    continue;

                visited[nr][nc] = true;
                queue.add(new Node(nr, nc));
            }
        }
    }

    static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
