import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ15663 {

    static BufferedReader reader;
    static StringBuilder builder;

    static int N, M;

    static int[] array;
    static boolean[] isUsed;
    static int[] selected;

    public static void main(String[] args) throws IOException{
        reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        array = new int[N];
        selected = new int[M];
        isUsed = new boolean[N];

        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(input[i]);
        }

        // 수열 정렬
        Arrays.sort(array);

        makePermutation(0);

        System.out.println(builder);
    }

    static void makePermutation(int choice) {

        // 출력
        if (choice == M) {
            for (int i = 0; i < M; i++) {
                builder.append(selected[i] + " ");
            }
            builder.append("\n");

            return;
        }

        int past = -1;
        for (int i = 0; i < N; i++) {
            if (isUsed[i] || past == array[i])
                continue;
            isUsed[i] = true;
            selected[choice] = array[i];
            past = array[i];
            makePermutation(choice + 1);
            isUsed[i] = false;
        }

    }
}
