import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {
    static int N, M, H, a, b;
    static boolean[][] map;

    static int MAX = Integer.MAX_VALUE;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        H = Integer.parseInt(tokenizer.nextToken());

        map = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());

            // a번 가로선이 b, b+1를 이음
            map[a][b] = true;
        }

        min = MAX;
        go(0, 1);

        if (min == MAX)
            System.out.println(-1);
        else
            System.out.println(min);

    }

    static void go(int idx, int here) {

        // 선택 횟수가 3회 초과 or 최소값 이상이면 stop
        if (idx > 3 || idx >= min)
            return;

        // 성공한 조작값 확인
        if (check()) {
            min = Math.min(min, idx);
        }

        // here에서부터 시작
        for (int r = here; r <= H; r++) {
            for (int c = 1; c < N; c++) {
                if (map[r][c] || map[r][c + 1] || map[r][c + 1])
                    continue;

                map[r][c] = true;
                go(idx + 1, r);
                map[r][c] = false;
            }
        }

    }

    // i번 세로선 결과가 i번이 나오는지 체크
    static boolean check() {

        for (int i = 1; i <= N; i++) {
            // 사다리 결과값
            int end = i;
            for (int move = 1; move <= H; move++) {
                // 왼쪽으로 이동
                if (map[move][end - 1])
                    end--;
                // 오른쪽으로 이동
                else if (map[move][end])
                    end++;
            }

            // 만약 i번 결과가 안 나왔다면 false 리턴
            if (end != i)
                return false;
        }

        return true;
    }

}
