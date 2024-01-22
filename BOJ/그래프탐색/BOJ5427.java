import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ5427 {
    static BufferedReader reader;
    static StringBuilder builder;

    // 불이 번짐 여부
    static int[][] visited;

    // 상근이 이동 거리
    static int[][] dist;

    static char[][] map;

    static int R, C;

    static Queue<Pair> fire;
    static Queue<Pair> person;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        int test = Integer.parseInt(reader.readLine());

        for (int t = 0; t < test; t++) {
            init();

            fire();

            int ans = person();

            if (ans == -1)
                builder.append("IMPOSSIBLE" + "\n");
            else
                builder.append(ans + "\n");
        }

        System.out.println(builder);
    }

    static void init() throws IOException {
        String[] input = reader.readLine().split(" ");

        C = Integer.parseInt(input[0]);
        R = Integer.parseInt(input[1]);

        map = new char[R][C];
        dist = new int[R][C];
        visited = new int[R][C];

        fire = new LinkedList<>();
        person = new LinkedList<>();

        String temp = null;
        for (int row = 0; row < R; row++) {
            temp = reader.readLine();
            for (int col = 0; col < C; col++) {
                map[row][col] = temp.charAt(col);

                if (map[row][col] == '@') {
                    dist[row][col] = 0;
                    visited[row][col] = -1;
                    person.add(new Pair(row, col));
                } else if (map[row][col] == '*') {
                    visited[row][col] = 0;
                    dist[row][col] = -1;
                    fire.add(new Pair(row, col));
                } else {
                    dist[row][col] = -1;
                    visited[row][col] = -1;
                }
            }
        }
    }

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static void fire() {
        while (!fire.isEmpty()) {
            Pair cur = fire.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;

                if (map[nr][nc] == '#' || visited[nr][nc] >= 0)
                    continue;

                visited[nr][nc] = visited[cur.row][cur.col] + 1;
                fire.add(new Pair(nr, nc));
            }
        }
    }

    static int person() {

        while (!person.isEmpty()) {
            Pair cur = person.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    return dist[cur.row][cur.col] + 1;

                if (map[nr][nc] != '.' || dist[nr][nc] >= 0
                        || (visited[nr][nc] != -1 && visited[nr][nc] <= dist[cur.row][cur.col] + 1))
                    continue;

                dist[nr][nc] = dist[cur.row][cur.col] + 1;
                person.add(new Pair(nr, nc));
            }
        }

        return -1;
    }

    static class Pair {
        int row, col;

        public Pair(int r, int c) {
            row = r;
            col = c;
        }
    }
}
