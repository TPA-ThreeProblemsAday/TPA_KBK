import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1062 {

    static int N, K;
    static int[] words;

    static boolean[] isUsed;

    static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        words = new int[N];

        for (int i = 0; i < N; i++) {
            String temp = reader.readLine();

            for (char ch : temp.toCharArray()) {
                words[i] |= 1 << (ch - 'a');
            }
        }

        if (K < 5) {
            System.out.println(0);
            return;
        }

        int value = 1;
        value += (1 << ('c' - 'a')) + (1 << ('t' - 'a')) + (1 << ('n' - 'a')) + (1 << ('i' - 'a'));

        go(0, 5, value);

        System.out.println(max);
    }

    static void go(int index, int depth, int value) {

        if (depth == K) {
            max = Math.max(max, check(value));
            return;
        }

        for (int i = index; i < 26; i++) {
            if ((value & (1 << i)) > 0)
                continue;

            go(i + 1, depth + 1, (value | (1 << i)));
        }
    }

    static int check(int value) {
        int cnt = 0;

        for (int word : words) {
            if ((value & word) == word)
                cnt++;
        }

        return cnt;
    }
}