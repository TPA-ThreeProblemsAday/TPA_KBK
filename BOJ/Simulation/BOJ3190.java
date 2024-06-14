import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ3190 {
    static int N, K, L;
    static int[][] map;
    // 우 -> 하 -> 좌 -> 상
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };

    static ArrayDeque<int[]> deque = new ArrayDeque<>();

    static int dir = 100;
    static int SNAKE = 1, APPLE = 2;
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        K = Integer.parseInt(reader.readLine());

        map = new int[N + 1][N + 1];
        // 맨 위, 맨 좌측
        deque.add(new int[] { 1, 1 });
        // 뱀의 위치 표기
        map[1][1] = SNAKE;

        int r = 0, c = 0;
        String[] input = null;
        for (int i = 0; i < K; i++) {
            input = reader.readLine().split(" ");
            r = Integer.parseInt(input[0]);
            c = Integer.parseInt(input[1]);

            // 사과 위치 표기
            map[r][c] = APPLE;
        }

        L = Integer.parseInt(reader.readLine());

        boolean flag = true;

        int pre = 0, now = 0;
        for (int i = 0; i < L; i++) {
            input = reader.readLine().split(" ");

            // 현재 주어진 시간
            now = Integer.parseInt(input[0]);

            // 지금까지 흐른 시간에서 주어진 시간까지 흐를 때,
            for (int j = pre; j < now; j++) {
                if (flag) {
                    flag = move(dir % 4);
                }

            }

            // 지금까지 흐른 시간
            pre = now;

            // 오른쪽
            if (input[1].equals("D"))
                dir++;
            // 왼쪽
            else if (input[1].equals("L"))
                dir--;

        }

        // 아직 게임이 끝나지 않았다면, 끝날때까지 진행
        while (flag) {
            flag = move(dir % 4);
        }

        System.out.println(time);
    }

    static boolean move(int dir) {
        // 시간 증가
        time++;

        // 머리
        int[] head = deque.peekLast();

        int nr = head[0] + dr[dir];
        int nc = head[1] + dc[dir];

        // 벽 또는 자기자신의 몸
        if (nr <= 0 || nc <= 0 || nr > N || nc > N || map[nr][nc] == SNAKE)
            return false;

        // 이동한 칸에 사과가 없다면
        if (map[nr][nc] != APPLE) {
            // 몸 길이 줄이기
            int[] tail = deque.poll();
            map[tail[0]][tail[1]] = 0;
        }

        // 머리 늘리기
        deque.add(new int[] { nr, nc });
        map[nr][nc] = SNAKE;

        return true;
    }
}
