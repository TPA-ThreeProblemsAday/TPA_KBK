import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ19942 {

    static BufferedReader reader;
    static StringBuilder builder;

    // 식재료 개수
    static int N;

    static int mp, mf, ms, mv;

    static int[][] ingredients;

    static List<Node> list;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        N = Integer.parseInt(reader.readLine());

        ingredients = new int[N + 1][5];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        // mp, mf, ms, mv
        mp = Integer.parseInt(tokenizer.nextToken());
        mf = Integer.parseInt(tokenizer.nextToken());
        ms = Integer.parseInt(tokenizer.nextToken());
        mv = Integer.parseInt(tokenizer.nextToken());

        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 5; j++) {
                ingredients[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        list = new ArrayList<>();

        powerset(1, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        Collections.sort(list, (o1, o2) -> {
            if (o1.price == o2.price)
                return o1.output.compareTo(o2.output);
            return o1.price - o2.price;
        });

        builder.append(min).append("\n");

        builder.append(list.get(0).output).append("\n");

        System.out.println(builder);

    }

    static void powerset(int idx, int choice) {

        if (idx > N) {

            String output = "";
            // 단백질, 지방, 탄수화물, 비타민
            int tp = 0, tf = 0, ts = 0, tv = 0, tc = 0;
            for (int i = 1; i <= N; i++) {
                if ((choice & (1 << i)) > 0) {
                    tp += ingredients[i][0];
                    tf += ingredients[i][1];
                    ts += ingredients[i][2];
                    tv += ingredients[i][3];
                    tc += ingredients[i][4];

                    output += i + " ";
                }
            }

            if (tp >= mp && tf >= mf && ts >= ms && tv >= mv) {
                // min값 비교
                if (min >= tc) {
                    min = tc;
                    list.add(new Node(tc, output));
                }
            }

            return;
        }

        // 선택
        powerset(idx + 1, choice | (1 << idx));

        // 선택 X
        powerset(idx + 1, choice);
    }

    static class Node {
        int price;
        String output;

        Node(int price, String output) {
            this.price = price;
            this.output = output;
        }
    }

}
