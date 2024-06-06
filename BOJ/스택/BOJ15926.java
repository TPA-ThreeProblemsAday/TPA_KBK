import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ15926 {

    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        String input = reader.readLine();

        Stack<Integer> stack = new Stack<>();

        // 시작점 넣어줌
        stack.add(-1);

        for (int i = 0; i < N; i++) {
            if (input.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty())
                    max = Math.max(max, i - stack.peek());
                else
                    // 분기점
                    stack.push(i);
            }
        }

        System.out.println(max);
    }
}
