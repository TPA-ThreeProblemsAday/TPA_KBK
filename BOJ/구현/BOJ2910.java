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
        while (N-- > 0) {
            temp = Integer.parseInt(tokenizer.nextToken());

            if (map.get(temp) == null)
                map.put(temp, 1);
            else
                map.put(temp, map.get(temp) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> map.get(o2).compareTo(map.get(o1)));

        String output = "";
        for (int key : list) {
            for (int i = 0; i < map.get(key); i++)
                output += key + " ";
        }
        System.out.println(output);
    }

}