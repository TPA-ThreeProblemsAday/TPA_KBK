import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9012 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        N = Integer.parseInt(reader.readLine());
        while (N-- > 0) {

            boolean flag = solution(reader.readLine());

            if (flag)
                builder.append("YES\n");
            else
                builder.append("NO\n");
        }

        System.out.println(builder);
    }

    static boolean solution(String input) {
        Stack<Character> stack = new Stack<>();
        char ch = ' ';

        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);

            if (ch == '(')
                stack.add('(');
            else if (!stack.isEmpty()) {
                stack.pop();
            } else
                return false;
        }

        if (!stack.isEmpty())
            return false;

        return true;
    }
}
