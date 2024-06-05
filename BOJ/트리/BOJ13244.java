import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13244 {
    static int T, N, M;
    static List<Integer>[] edges;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        T = Integer.parseInt(reader.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(reader.readLine());
            M = Integer.parseInt(reader.readLine());

            edges = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++)
                edges[i] = new ArrayList<>();

            StringTokenizer st = null;
            int a = 0, b = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(reader.readLine());

                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                edges[a].add(b);
                edges[b].add(a);
            }

            if (M != N - 1) {
                builder.append("graph\n");
                continue;
            }

            visited = new boolean[N + 1];

            if (dfs(1) == N)
                builder.append("tree\n");
            else
                builder.append("graph\n");

        }

        System.out.println(builder);

    }

    static int dfs(int now) {
        int ret = 1;
        visited[now] = true;

        for (int next : edges[now]) {
            if (!visited[next])
                ret += dfs(next);
        }

        return ret;
    }
}
