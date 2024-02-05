import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2252 {

    // 진입차수
    static int[] inDegree;

    // 학생 수, 키 비교 횟수
    static int N, M;

    // 간선 정보 저장
    static List<Integer>[] edges;

    static StringBuilder builder;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        // 간선 정보 저장을 위한 초기화
        edges = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++)
            edges[i] = new ArrayList<>();

        // 진입 차수 담을 배열
        inDegree = new int[N + 1];

        int from = 0, to = 0;
        while (M > 0) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            from = Integer.parseInt(tokenizer.nextToken());
            to = Integer.parseInt(tokenizer.nextToken());

            edges[from].add(to);
            // 도착 정점의 진입 차수 1 증가
            inDegree[to]++;

            M--;
        }

        solution();

        System.out.println(builder);

    }

    static Deque<Integer> deque;

    static void solution() {
        deque = new ArrayDeque<>();

        // 진입 차수가 0인 시작점 노드 추가
        for (int idx = 1; idx <= N; idx++) {
            if (inDegree[idx] == 0) {
                deque.add(idx);
            }
        }

        // 큐가 빌 때까지 수행
        while (!deque.isEmpty()) {
            int from = deque.poll();
            builder.append(from + " ");

            for (int to : edges[from]) {
                // 인접한 노드 간선 제거
                inDegree[to]--;
                // 만약에 인접 노드의 진입 차수가 0이 되면,
                if (inDegree[to] == 0)
                    // 큐에 삽입
                    deque.add(to);
            }
        }
    }
}
