import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2828 {

    static int N, M, J;
    static int[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        J = Integer.parseInt(reader.readLine());
        list = new int[J];

        for (int i = 0; i < J; i++)
            list[i] = Integer.parseInt(reader.readLine());

        int start = 1, end = 1;
        int move = 0;

        for (int loc : list) {
            end = start + M - 1;
            if (start > loc) {
                move += start - loc;
                start = loc;
            } else if (end < loc) {
                move += loc - end;
                start += loc - end;
            }
        }

        System.out.println(move);
    }
}
