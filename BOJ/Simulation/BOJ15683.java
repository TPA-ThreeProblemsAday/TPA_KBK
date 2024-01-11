import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ15683 {
    static BufferedReader reader;
    static StringBuilder builder;

    // CCTV 및 벽 정보
    static int[][] map;
    // 비추고 있는 곳 체크
    static int[][] checkmap;

    static int R, C;

    // CCTV 위치
    static List<CCTV> cctv;
    // 방향
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static int min;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new int[R][C];
        checkmap = new int[R][C];

        min = R * C;

        cctv = new ArrayList<>();

        for (int r = 0; r < R; r++) {
            input = reader.readLine().split(" ");
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(input[c]);
                if (map[r][c] > 0 && map[r][c] <= 5) {
                    cctv.add(new CCTV(r, c));
                }
            }
        }

        solution();

        System.out.println(min);

    }

    static void solution() {
        for (int t = 0; t < (1 << (2 * cctv.size())); t++) {
            // map 모양 복사(원상복구)
            copyMap();

            int num = t;
            for (int cam = 0; cam < cctv.size(); cam++) {
                int dir = num % 4;
                num /= 4;

                int row = cctv.get(cam).row;
                int col = cctv.get(cam).col;

                // CCTV 유형
                switch (map[row][col]) {
                    case 1:
                        upd(row, col, dir);
                        break;
                    case 2:
                        upd(row, col, dir);
                        upd(row, col, dir + 2);
                        break;
                    case 3:
                        upd(row, col, dir);
                        upd(row, col, dir + 1);
                        break;
                    case 4:
                        upd(row, col, dir);
                        upd(row, col, dir + 1);
                        upd(row, col, dir + 2);
                        break;
                    default:
                        upd(row, col, dir);
                        upd(row, col, dir + 1);
                        upd(row, col, dir + 2);
                        upd(row, col, dir + 3);
                        break;
                }
            }

            int temp = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (checkmap[r][c] == 0)
                        temp++;
                }
            }

            min = Math.min(min, temp);
        }
    }

    static void copyMap() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                checkmap[r][c] = map[r][c];
            }
        }
    }

    static void upd(int row, int col, int dir) {
        dir %= 4;
        while (true) {
            row += dr[dir];
            col += dc[dir];

            if (row < 0 || col < 0 || row >= R || col >= C || map[row][col] == 6)
                break;

            if (map[row][col] != 0)
                continue;

            checkmap[row][col] = 7;
        }
    }

    static class CCTV {
        int row;
        int col;

        public CCTV(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
