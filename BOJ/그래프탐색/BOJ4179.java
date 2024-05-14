import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ4179 {

    static int R, C;
    // 미로
    static char[][] map;

    static int row, col;

    // 불 거리
    static int[][] distF;
    // 지훈 거리
    static int[][] distJ;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int INF = Integer.MAX_VALUE;
    static Deque<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new char[R][C];
        distF = new int[R][C];
        distJ = new int[R][C];

        String temp = null;
        for (int i = 0; i < R; i++) {
            temp = reader.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);

                distF[i][j] = INF;

                if (map[i][j] == 'J') {
                    row = i;
                    col = j;
                } else if (map[i][j] == 'F') {
                    distF[i][j] = 1;
                    queue.add(new int[] { i, j });
                }

            }
        }

        fire();

        queue.add(new int[] { row, col });
        distJ[row][col] = 1;

        int result = jihoon();

        if (result > -1)
            System.out.println(result);
        else
            System.out.println("IMPOSSIBLE");
    }

    static void fire() {
        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node[0] + dr[d];
                int nc = node[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;
                if (map[nr][nc] == '#')
                    continue;
                if (distF[nr][nc] < INF)
                    continue;

                distF[nr][nc] = distF[node[0]][node[1]] + 1;
                queue.add(new int[] { nr, nc });
            }
        }
    }

    static int jihoon() {

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node[0] + dr[d];
                int nc = node[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    return distJ[node[0]][node[1]];

                if (distJ[nr][nc] > 0 || map[nr][nc] == '#')
                    continue;
                    
                int temp = distJ[node[0]][node[1]] + 1;
                if (distF[nr][nc] <= temp)
                    continue;

                distJ[nr][nc] = temp;
                queue.add(new int[] { nr, nc });
            }
        }

        return -1;
    }
}
