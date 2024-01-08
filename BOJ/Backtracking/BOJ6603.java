import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ6603 {

    static int N;
    static int[] arr;

    static int[] ans;

    static BufferedReader reader;
    static StringBuilder builder;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        while (true) {

            String[] input = reader.readLine().split(" ");

            N = Integer.parseInt(input[0]);

            if (N == 0)
                break;

            arr = new int[N];
            ans = new int[6];
            
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(input[i + 1]);
            }

            backtracking(0, 0);

            builder.append("\n");
        }

        System.out.println(builder);
    }

    static void backtracking(int choice, int idx) {
        if (choice == 6) {
            for (int num : ans) {
                builder.append(num + " ");
            }
            builder.append("\n");

            return;
        }

        for (int i = idx; i <= N - 6 + choice; i++) {
            ans[choice] = arr[i];
            backtracking(choice + 1, i + 1);
        }
    }
}
