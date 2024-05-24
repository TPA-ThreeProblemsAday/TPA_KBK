import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 시간복잡도 : 10! = 대략 362만
public class BOJ2529 {

    static int K;

    static String[] input;

    static boolean[] used;

    static int[] arr;

    static List<String> ret;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(reader.readLine());
        input = reader.readLine().split(" ");
        used = new boolean[10];
        arr = new int[K + 1];
        ret = new ArrayList<>();

        go(0, "");

        Collections.sort(ret);

        System.out.println(ret.get(ret.size() - 1));
        System.out.println(ret.get(0));

    }

    static void go(int depth, String num) {

        if (depth == K + 1) {
            ret.add(num);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (used[i])
                continue;

            if (depth == 0 || check(num.charAt(depth - 1), (char) (i + '0'), input[depth - 1])) {
                used[i] = true;
                backtracking(depth + 1, num + i);
                used[i] = false;
            }
        }

    }

    static boolean check(char x, char y, String op) {
        if (x > y && op.equals(">"))
            return true;
        if (x < y && op.equals("<"))
            return true;

        return false;
    }
}
