import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15683 {
    static int WALL = 6, EMPTY = 0, IS_CHECKED = 100;

    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };

    static int[][] map;
    static int[][] tempMap;
    static int R, C;

    static ArrayList<int[]> cctv;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // 초기화 및 입력
        init();

        int cctvNum = cctv.size();

        // CCTV는 네가지의 설치 방향을 선택할 수 있음
        for (int i = 0; i < (1 << (2 * cctvNum)); i++) {
            copyMap();
            int temp = i;
            for (int cam = 0; cam < cctvNum; cam++) {
                int dir = temp % 4;
                temp /= 4;

                int[] cur = cctv.get(cam);
                int row = cur[0], col = cur[1];
                switch (map[row][col]) {
                    case 1:
                        check(row, col, dir);
                        break;
                    case 2:
                        check(row, col, dir);
                        check(row, col, dir + 2);
                        break;
                    case 3:
                        check(row, col, dir);
                        check(row, col, dir + 3);
                        break;
                    case 4:
                        check(row, col, dir);
                        check(row, col, dir + 2);
                        check(row, col, dir + 3);
                        break;
                    case 5:
                        check(row, col, dir);
                        check(row, col, dir + 1);
                        check(row, col, dir + 2);
                        check(row, col, dir + 3);
                        break;
                }
            }

            findMin();
        }

        System.out.println(min);
    }

    static void findMin() {
        int blackSpace = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (tempMap[r][c] == EMPTY)
                    blackSpace++;
            }
        }

        min = Integer.min(min, blackSpace);
    }

    static void check(int row, int col, int direction) {
        int nr = 0, nc = 0;
        direction %= 4;

        while (true) {
            nr = row + dr[direction];
            nc = col + dc[direction];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                break;
            if (map[nr][nc] == WALL)
                break;

            // 빈 공간
            if (map[nr][nc] == EMPTY)
                tempMap[nr][nc] = IS_CHECKED;

            row = nr;
            col = nc;
        }
    }

    static void copyMap() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                tempMap[r][c] = map[r][c];
            }
        }
    }

    static void init() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        cctv = new ArrayList<>();
        map = new int[R][C];
        tempMap = new int[R][C];

        for (int r = 0; r < R; r++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(tokenizer.nextToken());

                // CCTV 위치 저장
                if (1 <= map[r][c] && map[r][c] <= 5) {
                    cctv.add(new int[] { r, c });
                }
            }
        }
    }
}
