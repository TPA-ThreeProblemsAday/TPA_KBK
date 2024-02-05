import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ16236 {

    static int N;
    static int[][] map;

    // 상어 위치, 크기
    static int R, C, size;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        StringTokenizer tokenizer = null;
        for (int r = 0; r < N; r++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(tokenizer.nextToken());

                if (map[r][c] == 9) {
                    R = r;
                    C = c;
                    map[r][c] = 0;
                }
            }
        }

        size = 2;
        int ans = 0;
        int time = 0;
        while (true) {
            time = bfs();

            if (time <= 0)
                break;

            ans += time;
        }

        System.out.println(ans);

    }

    static ArrayDeque<Node> queue;
    // 방문 여부 확인
    static boolean[][] visited;
    // 상어가 물고기를 먹은 수
    static int eatenCount = 0;
    
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int bfs() {

        visited = new boolean[N][N];
        queue = new ArrayDeque<>();

        // 먹은 물고기 수가 상어 크기랑 같으면 크기 증가
        if (eatenCount == size) {
            size++;
            eatenCount = 0;
        }

        queue.add(new Node(R, C, 0));
        visited[R][C] = true;

        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        int minT = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.time >= minT)
                break;

            for (int d = 0; d < 4; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                    continue;
                if (visited[nr][nc] || map[nr][nc] > size)
                    continue;

                // 물고기를 만났을 때, 가장 가까운 물고기 찾기
                if (map[nr][nc] > 0 && map[nr][nc] < size) {
                    if (nr < minR) {
                        minR = nr;
                        minC = nc;
                        minT = cur.time + 1;
                    } else if (nr == minR && nc < minC) {
                        minC = nc;
                        minT = cur.time + 1;
                    }

                }

                queue.add(new Node(nr, nc, cur.time + 1));
                visited[nr][nc] = true;
            }

        }

        // 더이상 먹을 물고기가 없음
        if (minT == Integer.MAX_VALUE)
            return 0;

        // 물고기를 먹음
        R = minR;
        C = minC;
        map[R][C] = 0;
        eatenCount++;

        // 가장 가까운 물고기를 먹는 데 걸린 시간
        return minT;

    }

    static class Node {
        int row, col, time;

        public Node(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
