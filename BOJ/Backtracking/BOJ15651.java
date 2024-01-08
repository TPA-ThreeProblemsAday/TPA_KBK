import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15651 {
    static int N, M;
    static BufferedReader reader;
    static StringBuilder builder;

    static int[] array;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        array = new int[M];

        backtracking(0);

        System.out.println(builder);
    }

    static void backtracking(int choice) {
        if (choice == M) {
            for (int i = 0; i < M; i++) {
                builder.append(array[i] + " ");
            }
            builder.append("\n");

            return;
        }

        for (int i = 1; i <= N; i++) {
            array[choice] = i;
            backtracking(choice + 1);
        }
    }
}
