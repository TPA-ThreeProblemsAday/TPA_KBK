import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1182 {

    static int N;
    static int sum;
    static int[] array;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        sum = Integer.parseInt(input[1]);

        array = new int[N];

        input = reader.readLine().split(" ");

        for (int i = 0; i < N; i++)
            array[i] = Integer.parseInt(input[i]);

        solution();

        if (sum == 0)
            ans -= 1;

        System.out.println(ans);
    }

    static void solution() {

        for (int i = 0; i < (1 << N); i++) {
            int cur = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) > 0)
                    cur += array[j];
            }
            if (cur == sum)
                ans++;
        }
    }
}
