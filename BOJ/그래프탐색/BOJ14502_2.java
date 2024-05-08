import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14502_2 {

    static int N, M;
    // 연구소 원본 맵
    static int[][] map;
    // 방문 여부
    static boolean[][] visited;

    // 안전 영역 최댓값
    static int max = -1;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static List<int[]> virusList;
    static List<int[]> wallList;

    static int EMPTY = 0, WALL = 1, VIRUS = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        map = new int[N][M];
        wallList = new ArrayList<>();
        virusList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == VIRUS)
                    virusList.add(new int[] { i, j });
                else if (map[i][j] == EMPTY)
                    wallList.add(new int[] { i, j });
            }
        }

        int[] wall1 = null, wall2 = null, wall3 = null;
        for (int i = 0; i < wallList.size(); i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    wall1 = wallList.get(i);
                    wall2 = wallList.get(j);
                    wall3 = wallList.get(k);

                    map[wall1[0]][wall1[1]] = WALL;
                    map[wall2[0]][wall2[1]] = WALL;
                    map[wall3[0]][wall3[1]] = WALL;

                    solve();

                    map[wall1[0]][wall1[1]] = EMPTY;
                    map[wall2[0]][wall2[1]] = EMPTY;
                    map[wall3[0]][wall3[1]] = EMPTY;
                }
            }
        }

        System.out.println(max);
    }

    static Deque<int[]> queue;

    static void solve() {
        visited = new boolean[N][M];
        queue = new ArrayDeque<>();

        for (int[] virus : virusList) {
            visited[virus[0]][virus[1]] = true;
            queue.add(new int[] { virus[0], virus[1] });
            bfs();
        }

        // 안전 영역 체크 및 최댓값 경신
        max = Math.max(max, checkMap());
    }

    static void bfs() {

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node[0] + dr[d];
                int nc = node[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                    continue;
                if (visited[nr][nc] || map[nr][nc] != EMPTY)
                    continue;

                visited[nr][nc] = true;
                queue.add(new int[] { nr, nc });
            }
        }

    }

    static int checkMap() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 비어있고 방문하지 않음(바이러스가 퍼지지 않음)
                if (map[i][j] == EMPTY && !visited[i][j])
                    result++;
            }
        }
        return result;
    }
}
