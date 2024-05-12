import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1325 {
    static int N, M;

    static List<Integer>[] graph;
    static boolean[] visited;
    static int[] ans;

    static int max = -1;

    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        graph = new ArrayList[N + 1];
        ans = new int[N + 1];
        queue = new LinkedList<>();

        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        int a = 0, b = 0;
        while (M-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());

            graph[a].add(b);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            bfs(i);
        }

        for (int value : ans) {
            if (max < value)
                max = value;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (max == ans[i])
                sb.append(i + " ");
        }

        System.out.println(sb);
    }

    static void bfs(int start) {
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int n : graph[node]) {
                if (visited[n])
                    continue;
                ans[n]++;
                visited[n] = true;
                queue.add(n);
            }
        }
    }

    // dfs는 시간 초과
    // static void dfs(int cur) {
    //     visited[cur] = true;

    //     for (int n : graph[cur]) {
    //         if (visited[n])
    //             continue;
    //         ans[n]++;
    //         dfs(n);
    //     }
    // }

}