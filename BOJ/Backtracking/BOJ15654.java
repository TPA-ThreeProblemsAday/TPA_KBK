import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ15654 {

    static int N, M;

    // 주어지는 숫자
    static int[] arr;

    static boolean[] selected;

    static int[] answer;

    static BufferedReader reader;
    static StringBuilder builder;

    public static void main(String[] args) throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        arr = new int[N];
        selected = new boolean[N];
        answer = new int[M];

        input = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        // 수열 정렬
        Arrays.sort(arr);

        makePermutation(0);

        System.out.println(builder);
    }

    static void makePermutation(int choice) {
        if (choice == M) {
            for (int i = 0; i < M; i++) {
                builder.append(answer[i] + " ");
            }
            builder.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if(selected[i]) continue;

            selected[i] = true;
            answer[choice] = arr[i];

            makePermutation(choice + 1);
            selected[i] = false;
        }
    }
}
