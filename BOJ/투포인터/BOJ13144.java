import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13144 {

    static int N;
    static int[] arr;

    static boolean[] visited;
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(tokenizer.nextToken());

        visited = new boolean[100001];

        int start = 0, end = 0;
        while (end < N) {
            if (!visited[arr[end]]) {
                visited[arr[end]] = true;
                end++;
            } else {
                // 현재 중복 값을 포함하는 모든 부분집합의 갯수
                ans += end - start;
                visited[arr[start]] = false;
                start++;
            }
        }

        ans += (long) (end - start) * (end - start + 1) / 2;
        System.out.println(ans);

    }
}