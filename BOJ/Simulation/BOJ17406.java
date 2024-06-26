import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17406 {
    static int N, M, K;
    static int[][] A, temp;

    // 순열 구하기
    static boolean[] selected;

    // 배열 돌리기 명령 담기
    static List<int[]> list;

    // 배열 돌리기에 쓸 list
    static List<int[]> loc;

    static int dr[] = { 0, 1, 0, -1 };
    static int dc[] = { 1, 0, -1, 0 };

    static boolean[][] visited;

    static int r, c, s, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        A = new int[N][M];
        temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        list = new ArrayList<>();
        loc = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            r = Integer.parseInt(tokenizer.nextToken());
            c = Integer.parseInt(tokenizer.nextToken());
            s = Integer.parseInt(tokenizer.nextToken());

            list.add(new int[] { --r, --c, s });
        }

        selected = new boolean[K];
        makePermutation(0, new int[K]);

        System.out.println(min);
    }

    static void makePermutation(int depth, int[] arr) {

        // 순열 다 구했을 때,
        if (depth == K) {
            // A 배열 복사
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = A[i][j];
                }
            }

            solve(arr);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (selected[i])
                continue;

            selected[i] = true;
            arr[depth] = i;
            makePermutation(depth + 1, arr);
            selected[i] = false;
        }
    }

    static void solve(int[] arr) {

        // 순서에 따라 배열 돌리기
        for (int k = 0; k < K; k++) {
            // 배열 돌리기 연산
            int[] op = list.get(arr[k]);
            rotate(op[0], op[1], op[2]);
        }

        // 각 행 모든 수의 합 중 최솟값 구하기
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 0; j < M; j++) {
                cnt += temp[i][j];
            }
            min = Math.min(min, cnt);
        }

    }

    static int dir;
    static int sr, sc, er, ec;

    static void rotate(int r, int c, int s) {

        for (int i = 1; i <= s; i++) {
            // 시작하는 네 꼭짓점
            sr = r - 1 * i;
            sc = c - 1 * i;
            er = r + 1 * i;
            ec = c + 1 * i;

            // 초기화
            loc.clear();
            dir = 0;
            visited = new boolean[N][M];

            visited[sr][sc] = true;
            loc.add(new int[] { sr, sc });

            go(sr, sc, true);

            Deque<Integer> deque = new ArrayDeque<>();
            for (int[] l : loc)
                deque.add(temp[l[0]][l[1]]);

            deque.addFirst(deque.removeLast());

            for (int[] l : loc) {
                temp[l[0]][l[1]] = deque.poll();
            }

        }

    }

    static void go(int r, int c, boolean first) {

        if (!first && r == sr && c == sc)
            dir++;
        if (!first && r == sr && c == ec)
            dir++;
        if (!first && r == er && c == ec)
            dir++;
        if (!first && r == er && c == sc)
            dir++;

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        if (visited[nr][nc])
            return;

        visited[nr][nc] = true;
        loc.add(new int[] { nr, nc });

        go(nr, nc, false);

    }

}
