import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17471 {

    static int N;

    // 인구 수
    static int[] people;

    // 인접리스트
    static ArrayList<Integer>[] adj;

    static boolean[] visited, comp;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        people = new int[N + 1];
        adj = new ArrayList[N + 1];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(tokenizer.nextToken());
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            int nodes = Integer.parseInt(tokenizer.nextToken());

            for (int j = 0; j < nodes; j++) {
                adj[i].add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        // 최소 1개 선택부터 전체 선택 이전까지
        for (int i = 1; i < (1 << N) - 1; i++) {
            visited = new boolean[N + 1];
            comp = new boolean[N + 1];

            int start1 = 0, start2 = 0;

            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) > 0) {
                    comp[j + 1] = true;
                    start1 = j + 1;
                } else
                    start2 = j + 1;
            }

            int[] ret1 = dfs(start1, true);
            int[] ret2 = dfs(start2, false);

            if (ret1[0] + ret2[0] == N)
                min = Math.min(min, Math.abs(ret1[1] - ret2[1]));

        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);

    }

    static int[] dfs(int node, boolean flag) {
        visited[node] = true;

        int[] result = { 1, people[node] };
        for (int next : adj[node]) {
            if (comp[next] != flag || visited[next])
                continue;
            int[] ret = dfs(next, flag);

            result[0] += ret[0];
            result[1] += ret[1];
        }

        return result;
    }

}
