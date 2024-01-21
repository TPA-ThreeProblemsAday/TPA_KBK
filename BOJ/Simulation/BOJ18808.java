import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ18808 {

    static BufferedReader reader;

    static int[][] map;
    static int R, C, K;

    static int row, col;
    static int[][] sticker;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        map = new int[R][C];
        sticker = new int[11][11];

        // 스티커 정보 입력
        for (int k = 0; k < K; k++) {
            input = reader.readLine().split(" ");
            row = Integer.parseInt(input[0]);
            col = Integer.parseInt(input[1]);

            for (int r = 0; r < row; r++) {
                input = reader.readLine().split(" ");
                for (int c = 0; c < col; c++) {
                    sticker[r][c] = Integer.parseInt(input[c]);
                }
            }

            outer: for (int d = 0; d < 4; d++) {

                for (int r = 0; r <= R - row; r++) {
                    for (int c = 0; c <= C - col; c++) {
                        if (attachSticker(r, c))
                            break outer;
                    }
                }

                rotate();
            }

        }

        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1)
                    ans++;
            }
        }

        System.out.println(ans);

    }

    static boolean attachSticker(int r, int c) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[r + i][c + j] == 1 && sticker[i][j] == 1)
                    return false;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (sticker[i][j] == 1)
                    map[r + i][c + j] = 1;
            }
        }

        return true;
    }

    // 90도 회전
    static void rotate() {
        int[][] temp = new int[11][11];

        // 스티커 복사
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                temp[r][c] = sticker[r][c];
            }
        }

        for (int r = 0; r < col; r++) {
            for (int c = 0; c < row; c++) {
                sticker[r][c] = temp[row - c - 1][r];
            }
        }

        col = swap(row, row = col);
    }

    static int swap(int a, int b) {
        return a;
    }

}
