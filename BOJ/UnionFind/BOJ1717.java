import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1717 {

    static BufferedReader reader;

    static int n, m;

    static int[] p;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        String[] input = reader.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        p = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            makeSet(i);
        }

        int operation = 0, x = 0, y = 0;
        for (int i = 0; i < m; i++) {
            input = reader.readLine().split(" ");
            operation = Integer.parseInt(input[0]);
            x = Integer.parseInt(input[1]);
            y = Integer.parseInt(input[2]);

            if (operation == 0) {
                union(x, y);
                continue;
            }

            if (findSet(x) == findSet(y))
                builder.append("YES\n");
            else
                builder.append("NO\n");
        }

        System.out.println(builder);
    }

    static void makeSet(int x) {
        p[x] = x;
    }

    static int findSet(int x) {
        if (p[x] == x)
            return x;
        else // 경로 압축
            return p[x] = findSet(p[x]);
    }

    static void union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (px == py)
            return;
        else
            p[py] = px;
    }

}
