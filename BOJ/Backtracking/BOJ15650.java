import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15650 {

    static int N, M;

    static boolean[] selected;

    static int[] array;

    static StringBuilder builder;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        selected = new boolean[N + 1];
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
            if (!selected[i]) {
                array[choice] = i;
                selected[i] = true;
                solution(choice + 1, i + 1);
                selected[i] = false;
            }
        }
    }
}
