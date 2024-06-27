import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2098 {

    static int N;
    // 현재 도시, 방문 집합
    static int[][] dp, dist;

    static int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        dp = new int[N][1 << N];
        dist = new int[N][N];

        StringTokenizer tokenizer = null;
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 1));

    }

    static int dfs(int here, int visited) {
        // 모든 도시 다 돌음
        if (visited == (1 << N) - 1) {
            // 돌아갈 수 있는 길이 있을 때, 없을 때
            return dist[here][0] > 0 ? dist[here][0] : INF;
        }

        // 방문한 도시 집합과 현재도시 번호가 같을 때, 나머지 도시들을 방문하고 출발 도시로 돌아가는 최소 비용은 같음
        if (dp[here][visited] != -1) {
            return dp[here][visited];
        }

        dp[here][visited] = INF;

        for (int i = 0; i < N; i++) {
            // 이미 방문
            if ((visited & (1 << i)) > 0)
                continue;
            // 갈 수 없는 도시 (경로 없음)
            if (dist[here][i] == 0)
                continue;

            // 현재 있는 도시 here, 이미 방문한 도시들의 집합이 visited일 때,
            // 방문하지 않은 나머지 도시들을 모두 방문한뒤 출발 도시로 돌아올 때 드는 최소 비용
            dp[here][visited] = Math.min(dp[here][visited], dfs(i, visited | (1 << i)) + dist[here][i]);
        }

        return dp[here][visited];
    }
}