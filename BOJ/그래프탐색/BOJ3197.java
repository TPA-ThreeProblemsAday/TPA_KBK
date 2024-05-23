import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ3197 {

    static int R, C, swanR, swanC, day;
    static char[][] map;

    static boolean[][] visited;
    static boolean[][] waterVisited;
    static Deque<int[]> water, swan;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        init();

        String temp = null;
        for (int i = 0; i < R; i++) {
            temp = reader.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);

                // 백조 위치 기록
                if (map[i][j] == 'L') {
                    swanR = i;
                    swanC = j;
                }

                // 물 지점
                if (map[i][j] == '.' || map[i][j] == 'L') {
                    waterVisited[i][j] = true;
                    water.add(new int[] { i, j });
                }
            }
        }

        visited = new boolean[R][C];

        visited[swanR][swanC] = true;
        swan.add(new int[] { swanR, swanC });

        while (true) {
            if (move())
                break;

            melt();

            day++;
        }

        System.out.println(day);

    }

    static boolean move() {

        Deque<int[]> temp = new ArrayDeque<>();

        while (!swan.isEmpty()) {
            int[] s = swan.pop();

            for (int d = 0; d < 4; d++) {
                int nr = s[0] + dr[d];
                int nc = s[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;

                if (visited[nr][nc])
                    continue;

                visited[nr][nc] = true;

                if (map[nr][nc] == 'X')
                    temp.add(new int[] { nr, nc });
                else if (map[nr][nc] == '.')
                    swan.add(new int[] { nr, nc });
                else if (map[nr][nc] == 'L')
                    return true;
            }
        }

        swan = temp;

        return false;
    }

    static void melt() {

        Deque<int[]> temp = new ArrayDeque<>();

        while (!water.isEmpty()) {
            int[] w = water.pop();

            for (int d = 0; d < 4; d++) {
                int nr = w[0] + dr[d];
                int nc = w[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C || waterVisited[nr][nc])
                    continue;

                waterVisited[nr][nc] = true;

                if (map[nr][nc] == 'X') {
                    map[nr][nc] = '.';
                    temp.add(new int[] { nr, nc });
                }
            }
        }

        water = temp;
    }

    static void init() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new char[R][C];
        visited = new boolean[R][C];
        waterVisited = new boolean[R][C];

        water = new ArrayDeque<>();
        swan = new ArrayDeque<>();
    }
}
