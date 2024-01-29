import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15685 {
    static BufferedReader reader;
    // 드래곤 커브 개수
    static int N;
    // 드래곤 커브 일부 여부
    static boolean[][] visited = new boolean[103][103];
    // 드래곤 커브 정보
    static int X, Y, D, G;

    // 답안
    static int ans;

    static int INCREASE_X = 0, DECREASE_Y = 1, DECREASE_X = 2, INCREASE_Y = 3;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        // 드래곤 커브 수
        N = Integer.parseInt(reader.readLine());

        StringTokenizer tokenizer = null;
        ArrayList<Integer> direction = null;
        for (int n = 0; n < N; n++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            direction = new ArrayList<>();

            X = Integer.parseInt(tokenizer.nextToken());
            Y = Integer.parseInt(tokenizer.nextToken());
            D = Integer.parseInt(tokenizer.nextToken());
            G = Integer.parseInt(tokenizer.nextToken());

            visited[X][Y] = true;
            direction.add(D);

            while (G > 0) {
                // 끝에서부터 방향 전환 + 선 더하기
                for (int d = direction.size() - 1; d >= 0; d--) {
                    direction.add((direction.get(d) + 1) % 4);
                }
                G--;
            }

            for (int dir : direction) {
                if (dir == INCREASE_X)
                    X++;
                else if (dir == DECREASE_X)
                    X--;
                else if (dir == INCREASE_Y)
                    Y++;
                else if (dir == DECREASE_Y)
                    Y--;

                visited[X][Y] = true;
            }
        }

        checkMap();

        System.out.println(ans);

    }

    static void checkMap() {

        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if (!visited[i][j])
                    continue;
                if (!visited[i][j + 1])
                    continue;
                if (!visited[i + 1][j])
                    continue;
                if (!visited[i + 1][j + 1])
                    continue;

                ans++;
            }
        }
    }
}
