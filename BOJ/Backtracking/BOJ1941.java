import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1941 {
    static BufferedReader reader;

    static char[][] map;

    static int[] combR;
    static int[] combC;

    static int[] selected;

    static int answer;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        map = new char[5][5];
        combR = new int[25];
        combC = new int[25];
        selected = new int[7];

        // 초기화
        String input = null;
        for (int r = 0; r < 5; r++) {
            input = reader.readLine();
            for (int c = 0; c < 5; c++)
                map[r][c] = input.charAt(c);
        }

        // 좌표 계산
        for (int i = 0; i < 25; i++) {
            combR[i] = i / 5;
            combC[i] = i % 5;
        }

        combination(0, 0);

        System.out.println(answer);
    }

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static void combination(int depth, int selIdx) {
        if (depth == 7) {
            // 상하좌우 인접 확인
            bfs();
            return;
        }

        if (selIdx == 25)
            return;

        selected[depth] = selIdx;
        combination(depth + 1, selIdx + 1);
        combination(depth, selIdx + 1);
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[7];

        visited[0] = true;
        queue.add(selected[0]);
        // 선택한 사람 수
        int cnt = 1;
        // 도연 파
        int doyeon = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 도연파인지 확인
            if (map[combR[cur]][combC[cur]] == 'Y')
                doyeon++;

            for (int d = 0; d < 4; d++) {
                for (int next = 1; next < 7; next++) {
                    if (visited[next])
                        continue;
                    if (combR[cur] + dr[d] != combR[selected[next]]
                            || combC[cur] + dc[d] != combC[selected[next]])
                        continue;

                    visited[next] = true;
                    cnt++;
                    queue.add(selected[next]);
                }
            }

        }

        if (cnt == 7 && doyeon <= 3) {
            answer++;
        }

    }
}
