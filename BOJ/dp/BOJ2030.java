import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2030 {

    // 아이들 수, 친구 관계 수, 공명 최소 아이 수
    static int N, M, K;

    // 아이들이 가진 캔디 수 (그룹 우두머리가 가지는 캔디 수 저장)
    static int[] candies;
    // 그룹 우두머리 저장
    static int[] group;
    // 무리별 인원 수
    static int[] count;

    // N번째 친구 그룹에 대해서 뺏을 수 있는 최대 사탕의 개수 저장
    static long[][] dp;

    // 친구 그룹 정보 저장
    static ArrayList<Node> groupList;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        candies = new int[N + 1];
        count = new int[N + 1];
        group = new int[N + 1];

        tokenizer = new StringTokenizer(reader.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            candies[i] = Integer.parseInt(tokenizer.nextToken());
            count[i] = 1;
            group[i] = i;
        }

        int x = 0, y = 0;
        while (M-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            x = Integer.parseInt(tokenizer.nextToken());
            y = Integer.parseInt(tokenizer.nextToken());

            // 친구 그룹으로 묶기
            union(x, y);
        }

        // 친구 그룹 정보 만들어주기
        makeGroupList();

        int groupSize = groupList.size();
        // K - 1명의 아이까지만 사탕을 뺏을 수 있음
        dp = new long[groupSize + 1][K];

        Node curGroup = null;
        for (int i = 1; i <= groupSize; i++) {
            // 현재 그룹에 대해서
            curGroup = groupList.get(i - 1);
            for (int j = 1; j < K; j++) {
                // 사탕을 뺏을 수 있을 때,
                if (j - curGroup.count >= 0) {
                    // 현재 그룹의 사탕을 뺏는 것이 최댓값인지 판단
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curGroup.count] + curGroup.candies);
                } else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        System.out.println(dp[groupSize][K - 1]);
    }

    static void makeGroupList() {
        groupList = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            // 그룹 우두머리가 아니면
            if (group[i] != i) {
                // 그룹 우두머리를 찾아서
                int pi = findSet(i);

                // 현재 아이의 인원 수와 캔디 수 더해주기
                count[pi] += count[i];
                candies[pi] += candies[i];
            }
        }

        for (int i = 1; i <= N; i++) {
            // 그룹 우두머리가 아니면 패스
            if (group[i] != i)
                continue;
            // 그룹 정보 추가
            groupList.add(new Node(count[i], candies[i]));
        }
    }

    static int findSet(int x) {
        if (group[x] == x)
            return x;
        return group[x] = findSet(group[x]);
    }

    static void union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (px <= py)
            group[py] = px;
        else
            group[px] = py;
    }

    static class Node {
        int count;
        int candies;

        public Node(int count, int candies) {
            this.count = count;
            this.candies = candies;
        }
    }
}
