import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ16637 {

    static int N;
    static List<Character> operators;
    static List<Integer> numbers;

    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        String input = reader.readLine();

        operators = new ArrayList<>();
        numbers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if ((i & 1) > 0)
                operators.add(input.charAt(i));
            else
                numbers.add(input.charAt(i) - '0');
        }

        solution(0, numbers.get(0));

        System.out.println(result);
    }

    static void solution(int idx, int total) {
        if (idx == numbers.size() - 1) {
            result = Math.max(total, result);
            return;
        }

        int cal = calculate(total, numbers.get(idx + 1), operators.get(idx));
        solution(idx + 1, cal);

        if (idx + 2 < numbers.size()) {
            int next = calculate(numbers.get(idx + 1), numbers.get(idx + 2), operators.get(idx + 1));
            cal = calculate(total, next, operators.get(idx));
            solution(idx + 2, cal);
        }
    }

    static int calculate(int a, int b, char op) {
        if (op == '+')
            return a + b;
        else if (op == '-')
            return a - b;
        else
            return a * b;
    }
}
