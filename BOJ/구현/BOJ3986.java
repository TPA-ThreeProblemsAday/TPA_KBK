import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ3986 {
    static int N;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        String word = null;
        while (N-- > 0) {
            word = reader.readLine();

            Stack<Character> stack = new Stack<>();

            for (char ch : word.toCharArray()) {
                // empty 체크해줘야 함
                if (!stack.isEmpty() && stack.peek() == ch)
                    stack.pop();
                else
                    stack.push(ch);
            }

            if (stack.isEmpty())
                ans++;
        }

        System.out.println(ans);
    }
}
