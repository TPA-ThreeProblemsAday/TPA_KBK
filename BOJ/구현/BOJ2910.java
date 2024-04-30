import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ2910 {

    static int N, C;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        N = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        map = new LinkedHashMap<>();

        int temp = 0;
        tokenizer = new StringTokenizer(reader.readLine(), " ");
        List<Integer> copy = new ArrayList<>();
        List<Integer> keys = new ArrayList<>();
        while (N-- > 0) {
            temp = Integer.parseInt(tokenizer.nextToken());
            keys.add(temp);
            copy.add(temp);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }

        Collections.sort(keys, (o1, o2) -> {
            if (map.get(o1) == map.get(o2))
                return copy.indexOf(o1) - copy.indexOf(o2);
            return map.get(o2).compareTo(map.get(o1));
        });

        String output = "";
        for (int key : keys) {
            output += key + " ";
        }
        System.out.println(output);
    }

}