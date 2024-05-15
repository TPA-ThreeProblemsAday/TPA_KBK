import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ12869 {

    static int N;

    // 순열 리스트
    static int[][] attack = new int[][] { { 9, 3, 1 },
            { 9, 1, 3 },
            { 3, 1, 9 },
            { 3, 9, 1 },
            { 1, 3, 9 },
            { 1, 9, 3 } };

    // 주어진 체력
    static int[] SCV;
    static int[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        SCV = new int[3];
        // 상태 체크
        visited = new int[61][61][61];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        for (int i = 0; i < N; i++)
            SCV[i] = Integer.parseInt(tokenizer.nextToken());

        visited[SCV[0]][SCV[1]][SCV[2]] = 1;
        queue.add(SCV);

        System.out.println(bfs());
    }

    static Deque<int[]> queue = new ArrayDeque<>();

    static int bfs() {

        while (!queue.isEmpty()) {
            int[] scv = queue.poll();

            for (int i = 0; i < attack.length; i++) {
                int[] temp = new int[3];

                temp[0] = Math.max(0, scv[0] - attack[i][0]);
                temp[1] = Math.max(0, scv[1] - attack[i][1]);
                temp[2] = Math.max(0, scv[2] - attack[i][2]);

                if (visited[temp[0]][temp[1]][temp[2]] > 0)
                    continue;

                visited[temp[0]][temp[1]][temp[2]] = visited[scv[0]][scv[1]][scv[2]] + 1;
                queue.add(temp);
            }
        }

        return visited[0][0][0] - 1;
    }

}
