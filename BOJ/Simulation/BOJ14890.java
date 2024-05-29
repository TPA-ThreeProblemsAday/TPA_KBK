import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890 {

    static int N, L;
    static int count;
    static int[][] map1, map2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        L = Integer.parseInt(tokenizer.nextToken());

        map1 = new int[N][N];
        map2 = new int[N][N];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                map1[i][j] = Integer.parseInt(tokenizer.nextToken());
                map2[j][i] = map1[i][j];
            }
        }
        solution(map1);
        solution(map2);

        System.out.println(count);
    }

    static void solution(int[][] map) {
        for (int i = 0; i < N; i++) {
            // cnt = 같은 레벨 이어지는 것 체크
            int cnt = 1, j = 0;
            for (j = 0; j < N - 1; j++) {
                if (map[i][j] == map[i][j + 1])
                    cnt++;
                else if (map[i][j] + 1 == map[i][j + 1] && cnt >= L)
                    cnt = 1;
                else if (map[i][j] - 1 == map[i][j + 1] && cnt >= 0)
                    cnt = -L + 1;
                else
                    break;
            }

            if (j == N - 1 && cnt >= 0)
                count++;
        }
    }
}
