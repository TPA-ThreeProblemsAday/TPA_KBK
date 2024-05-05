import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10709 {
    static int H, W;
    static char[][] joi;
    static int[][] time;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        H = Integer.parseInt(tokenizer.nextToken());
        W = Integer.parseInt(tokenizer.nextToken());

        joi = new char[H][W];
        time = new int[H][W];

        String input = null;
        for (int i = 0; i < H; i++) {
            input = reader.readLine();
            for (int j = 0; j < W; j++) {
                joi[i][j] = input.charAt(j);
                time[i][j] = -1;
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (joi[i][j] == 'c') {
                    time[i][j] = 0;
                    bfs(i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(time[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static Queue<int[]> queue = new ArrayDeque<>();

    static void bfs(int row, int col) {
        queue.add(new int[] { row, col });

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int nc = node[1] + 1;

            if (nc >= W)
                continue;

            time[node[0]][nc] = time[node[0]][node[1]] + 1;
            queue.add(new int[] { node[0], nc });

        }
    }
}