import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ15926_배열 {

    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        String input = reader.readLine();

        Stack<Integer> stack = new Stack<>();
        boolean[] isRight = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (input.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    isRight[i] = isRight[stack.pop()] = true;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < N; i++) {
            
            if (isRight[i]) {
                ret++;
                max = Math.max(max, ret);
                continue;
            }

            ret = 0;
        }

        System.out.println(max);
    }
}
