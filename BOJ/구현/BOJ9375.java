import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ9375 {
    static int T;
    static int N;

    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(reader.readLine());
        map = new HashMap<>();

        StringBuilder builder = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(reader.readLine());

            for (int i = 0; i < N; i++) {
                String item = reader.readLine().split(" ")[1];
                if (map.get(item) != null) {
                    map.put(item, map.get(item) + 1);
                } else
                    map.put(item, 1);
            }

            int ans = 1;
            for (int value : map.values()) {
                // 착용하지 않는 경우의 수 + 1
                ans *= value + 1;
            }

            // 아예 전체 착용하지 않는 경우의 수 없애기
            builder.append((ans - 1) + "\n");

            map.clear();
        }

        System.out.println(builder);
    }
}
