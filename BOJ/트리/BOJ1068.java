import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1068 {
    static int N;
    static int C;
    static List<Integer>[] graph;

    static int count;

    static int root;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        graph = new List[N];

        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        int p = 0;
        for (int i = 0; i < N; i++) {
            p = Integer.parseInt(tokenizer.nextToken());

            if (p == -1)
                root = i;
            else
                graph[p].add(i);
        }

        C = Integer.parseInt(reader.readLine());
        // root노드를 지웠을 때는 따로 계산
        if (C == root)
            System.out.println(0); // 출발 노드 체크 안 됨
        else
            System.out.println(dfs(root));

    }

    static int dfs(int node) {
        int child = 0, cnt = 0;
        for (int n : graph[node]) {
            // there 탐색 걸러내기
            if (n == C)
                continue;
            cnt += dfs(n);
            child++;
        }
        if (child == 0)
            return 1;

        return cnt;
    }

}
