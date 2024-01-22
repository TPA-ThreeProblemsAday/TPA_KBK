import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ14502 {

    // 가로 세로
    static int R, C;
    // 원본 연구소
    static int[][] originMap;

    // 벽을 세우는 시나리오
    static int[][] copyMap;

    static boolean[][] checked;

    static int EMPTY_SPACE = 0;
    static int WALL = 1;
    static int VIRUS = 2;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        originMap = new int[R][C];
        for (int r = 0; r < R; r++) {
            input = reader.readLine().split(" ");
            for (int c = 0; c < C; c++) {
                originMap[r][c] = Integer.parseInt(input[c]);
            }
        }
        checked = new boolean[R][C];
        backtracking(0);

        System.out.println(max);

    }

    static void backtracking(int choice) {

        if (choice == 3) {
            bfs();
            return;
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!checked[r][c] && originMap[r][c] == EMPTY_SPACE) {
                    checked[r][c] = true;
                    originMap[r][c] = WALL;

                    backtracking(choice + 1);

                    // 원상복구
                    checked[r][c] = false;
                    originMap[r][c] = EMPTY_SPACE;
                }
            }
        }
    }

    static Deque<Pair> deque = new ArrayDeque<>();

    static void bfs() {
        copyMap();

        // 바이러스가 퍼짐
        int nr = 0, nc = 0;
        while (!deque.isEmpty()) {
            Pair cur = deque.poll();

            for (int d = 0; d < 4; d++) {
                nr = cur.row + dr[d];
                nc = cur.col + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;
                if (copyMap[nr][nc] != EMPTY_SPACE)
                    continue;

                copyMap[nr][nc] = VIRUS;
                deque.add(new Pair(nr, nc));

            }
        }

        // 남아 있는 안전영역 비교
        int sum = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (copyMap[r][c] == EMPTY_SPACE)
                    sum++;
            }
        }

        max = Math.max(max, sum);
    }

    static void copyMap() {
        copyMap = new int[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                copyMap[r][c] = originMap[r][c];

                // 바이러스 시작 위치 추가
                if (copyMap[r][c] == VIRUS) {
                    deque.add(new Pair(r, c));
                }
            }
        }
    }

    static class Pair {
        int row, col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
