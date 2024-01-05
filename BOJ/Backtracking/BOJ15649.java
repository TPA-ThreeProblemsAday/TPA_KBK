import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15649 {
    static StringBuilder builder;
    static boolean[] selected;
    static int[] array;

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        selected = new boolean[N + 1];
        array = new int[M];

        makePermutation(0);

        System.out.println(builder);
    }

    static void makePermutation(int choice) {

        if (choice == M) {
            for (int num : array)
                builder.append(num + " ");

            builder.append("\n");

            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!selected[i]) {
                array[choice] = i;
                selected[i] = true;
                makePermutation(choice + 1);
                selected[i] = false;
            }
        }
    }
}
