import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BOJ2589 {
    // 세로 가로 크기
    static int R, C;
    // 지도
    static char[][] map;

    // 거리 체크 배열
    static int[][] dist;

    // 육지포인트 저장
    static List<int[]> lands;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    // 보물이 묻힌 최대 거리
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new char[R][C];
        dist = new int[R][C];

        lands = new ArrayList<>();

        String temp = null;
        for (int i = 0; i < R; i++) {
            temp = reader.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = temp.charAt(j);

                if (map[i][j] == 'L')
                    lands.add(new int[] { i, j });
            }
        }

        for (int[] l : lands) {
            dist = new int[R][C];
            int ret = bfs(l[0], l[1]);
            result = Math.max(ret, result);
        }

        System.out.println(result - 1);

    }

    static Deque<int[]> queue = new ArrayDeque<>();

    static int bfs(int r, int c) {

        int max = -1;

        queue.add(new int[] { r, c });
        // 1부터 시작한 후 후에 -1
        dist[r][c] = 1;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node[0] + dr[d];
                int nc = node[1] + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;
                if (map[nr][nc] != 'L' || dist[nr][nc] > 0)
                    continue;

                dist[nr][nc] = dist[node[0]][node[1]] + 1;
                queue.add(new int[] { nr, nc });
            }

            max = dist[node[0]][node[1]];
        }

        return max;
    }
}
