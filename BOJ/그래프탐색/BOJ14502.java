import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ14502 {

    static int N, M;
    // 연구소 원본 맵
    static int[][] map;
    // 맵 카피본
    static int[][] copy;
    // 기선택 여부
    static boolean[][] checked;

    // 안전 영역 최댓값
    static int max = -1;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int EMPTY = 0, WALL = 1, VIRUS = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        map = new int[N][M];
        copy = new int[N][M];
        checked = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        backtracking(0);

        System.out.println(max);
    }

    static void backtracking(int depth) {
        if (depth == 3) {
            // bfs 돌기
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 비어있고, 이미 선택하지 않은 곳
                if (map[i][j] == EMPTY && !checked[i][j]) {
                    map[i][j] = WALL;
                    checked[i][j] = true;

                    backtracking(depth + 1);

                    map[i][j] = EMPTY;
                    checked[i][j] = false;
                }
            }
        }
    }

    static void bfs() {
        Deque<int[]> queue = new ArrayDeque<>();

        copyMap();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == VIRUS)
                    queue.add(new int[] { i, j });
            }
        }

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node[0] + dr[d];
                int nc = node[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M)
                    continue;
                if (copy[nr][nc] != EMPTY)
                    continue;

                copy[nr][nc] = VIRUS;
                queue.add(new int[] { nr, nc });
            }
        }

        // 안전 영역 체크 및 최댓값 경신
        max = Math.max(max, checkMap());
    }

    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    static int checkMap() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == EMPTY)
                    result++;
            }
        }
        return result;
    }
}
