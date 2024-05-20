import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Max 시간복잡도 : 3의 26승
public class BOJ1987 {
    static int R, C;
    static char[][] board;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int max;
    static boolean[] letter;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        board = new char[R][C];

        String temp = null;
        for (int i = 0; i < R; i++) {
            temp = reader.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = temp.charAt(j);
            }
        }

        letter = new boolean[26];

        letter[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(max);
    }

    static void dfs(int r, int c, int cnt) {

        max = Math.max(max, cnt);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                continue;

            int idx = board[nr][nc] - 'A';

            if (letter[idx]) continue;

            letter[idx] = true;
            dfs(nr, nc, cnt + 1);
            letter[idx] = false;
        }
    }
}
