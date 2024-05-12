import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {
    static int N;
    // 수열 저장 배열
    static int[] arr;
    // 정답 저장 배열
    static int[] ret;
    // 짝짓기 문제
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        stack = new Stack<>();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        arr = new int[N];
        ret = new int[N];

        Arrays.fill(ret, -1);

        // 메인 아이디어 : 오큰수가 결정이 안 되어있으면, 담아놓기
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());

            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                ret[stack.pop()] = arr[i];
            }
            // 저장
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            sb.append(ret[i] + " ");

        System.out.println(sb);
    }
}
