import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1620 {

    static int N, M;
    static Map<String, Integer> map1;
    static String[] map2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        map1 = new HashMap<>();
        map2 = new String[N + 1];

        String temp = null;
        for (int i = 1; i <= N; i++) {
            temp = reader.readLine();
            map1.put(temp, i);
            map2[i] = temp;
        }

        StringBuilder builder = new StringBuilder();

        while (M-- > 0) {
            temp = reader.readLine();

            if (map1.containsKey(temp))
                builder.append(map1.get(temp) + "\n");
            else
                builder.append(map2[Integer.parseInt(temp)] + "\n");

        }

        System.out.println(builder);

    }

}
