import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 시간복잡도 = 13 C M Xx 100
public class BOJ15686 {

    static int N, M;
    static int[][] map;
    static int[][] copy;

    static int min = Integer.MAX_VALUE;

    static List<int[]> places;
    static List<int[]> house;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        map = new int[N][N];
        places = new ArrayList<>();
        house = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());

                if (map[i][j] == 1)
                    house.add(new int[] { i, j });
                else if (map[i][j] == 2)
                    places.add(new int[] { i, j });
            }
        }

        checked = new boolean[places.size()];

        solution(0, 0);

        System.out.println(min);
    }

    static void solution(int depth, int idx) {
        if (depth == M) {
            distance();
            return;
        }

        for (int i = idx; i < places.size(); i++) {
            checked[i] = true;
            solution(depth + 1, i + 1);
            checked[i] = false;
        }
    }

    static void distance() {
        int result = 0;
        for (int[] h : house) {
            // 현재 집에서 치킨집까지 최소 거리
            int m = Integer.MAX_VALUE;
            for (int i = 0; i < places.size(); i++) {
                // 폐업 대상
                if (!checked[i])
                    continue;
                // 현재 고려 대상 치킨집
                int[] cur = places.get(i);

                int distance = Math.abs(cur[0] - h[0]) + Math.abs(cur[1] - h[1]);
                m = Math.min(m, distance);
            }
            result += m;
        }
        min = Math.min(min, result);
    }

}
