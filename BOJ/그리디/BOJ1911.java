import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ1911 {
    static int N, L;
    static List<int[]> len;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);

        len = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            input = reader.readLine().split(" ");

            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);

            len.add(new int[] { s, e });
        }

        Collections.sort(len, (o1, o2) -> o1[0] - o2[0]);

        int end = 0;

        // 웅덩이에 대해서
        for (int[] l : len) {

            if (l[1] <= end)
                continue;

            if (end < l[0])
                end = l[0];

            // 현재 웅덩이에 대해서 깔아야할 널빤지 개수
            int temp = (l[1] - end) / L + ((l[1] - end) % L > 0 ? 1 : 0);

            // 널빤지 길이 더해주기
            end += temp * L;

            // 널빤지 갯수 더해주기
            ans += temp;
        }

        System.out.println(ans);
    }
}
