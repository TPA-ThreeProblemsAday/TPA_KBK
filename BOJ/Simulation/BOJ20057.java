import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057 {
    static int size;
    static int[][] map;

    // 좌 하 우 상
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { -1, 0, 1, 0 };

    static int sandR[][] = {
            { -1, 1, -1, 1, -1, 1, -2, 2, 0, 0 },
            { 0, 0, 1, 1, -1, -1, 0, 0, 2, 1 },
            { -1, 1, -1, 1, -1, 1, -2, 2, 0, 0 },
            { 0, 0, -1, -1, 1, 1, 0, 0, -2, -1 }
    };
    static int sandC[][] = {
            { 0, 0, -1, -1, 1, 1, 0, 0, -2, -1 },
            { -1, 1, -1, 1, -1, 1, -2, 2, 0, 0 },
            { 0, 0, 1, 1, -1, -1, 0, 0, 2, 1 },
            { -1, 1, -1, 1, -1, 1, -2, 2, 0, 0 }
    };
    static int[] percent = { 7, 7, 10, 10, 1, 1, 2, 2, 5 };

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(reader.readLine());

        map = new int[size][size];

        // map 초기화
        StringTokenizer tokenizer = null;
        for (int r = 0; r < size; r++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            for (int c = 0; c < size; c++) {
                map[r][c] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int dir = 0;
        int move = 1;
        int r = size / 2, c = size / 2;

        int curM = 0;
        while (true) {
            // 토네이도 이동
            r += dr[dir];
            c += dc[dir];

            // 모래 이동
            spreadSance(r, c, dir);

            // 이동 횟수 증가
            curM++;

            // 방향을 바꿀 타이밍
            if (curM == move) {
                dir = (dir + 1) % 4;
                curM = 0;
                if (dir == 0 || dir == 2)
                    move++;
            }

            // 마지막으로 오는 곳
            if (r == 0 && c == 0)
                break;
        }

        System.out.println(ans);

    }

    static void spreadSance(int r, int c, int dir) {
        int nr = 0, nc = 0;
        // 현재 위치 모래 총량
        int totalAmount = map[r][c];

        for (int i = 0; i < percent.length; i++) {
            nr = r + sandR[dir][i];
            nc = c + sandC[dir][i];

            // 흩뿌려질 모래 양
            int curAmount = totalAmount * percent[i] / 100;

            if (nr < 0 || nc < 0 || nr >= size || nc >= size)
                ans += curAmount;
            else
                map[nr][nc] += curAmount;

            map[r][c] -= curAmount;
        }

        // 알파 좌표
        int ar = r + sandR[dir][9];
        int ac = c + sandC[dir][9];

        if (ar < 0 || ac < 0 || ar >= size || ac >= size)
            ans += map[r][c];
        else
            map[ar][ac] += map[r][c];

        map[r][c] = 0;
    }
}
