import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15652 {

    static StringBuilder builder;

    static int N, M;
    static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        array = new int[M];

        solution(0, 1);

        System.out.println(builder);
    }

    static void solution(int choice, int start) {
        // 출력
        if (choice == M) {
            for (int i = 0; i < M; i++) {
                builder.append(array[i] + " ");
            }
            builder.append("\n");

            return;
        }

        for (int i = start; i <= N; i++) {
            array[choice] = i;
            solution(choice + 1, i);
        }

    }
}
