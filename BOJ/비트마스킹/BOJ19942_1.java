import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ19942_1 {

    static BufferedReader reader;
    static StringBuilder builder;

    // 식재료 개수
    static int N;

    static int mp, mf, ms, mv;

    static int[][] ingredients;

    static List<Node> list;

    static int min = Integer.MAX_VALUE;

    static int tp, tf, ts, tv, tc;

    public static void main(String[] args) throws IOException {

        // 초기화
        init();

        ArrayList<Node> list = new ArrayList<>();

        // 무조건 1개 이상
        for (int i = 1; i < (1 << N); i++) {
            tp = tf = ts = tv = tc = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) > 0) {
                    tp += ingredients[j][0];
                    tf += ingredients[j][1];
                    ts += ingredients[j][2];
                    tv += ingredients[j][3];
                    tc += ingredients[j][4];
                }
            }

            if (tp < mp || tf < mf || ts < ms || tv < mv) continue;

            if (min >= tc) {
                min = tc;
                list.add(new Node(tc, i));
            }
        }

        if (list.size() == 0) {
            System.out.println(-1);
            return;
        }

        ArrayList<String> ret = new ArrayList<>();
        String output = null;
        for(Node node : list) {
            output = "";

            if(node.cost == min) {
                for(int i =0; i < N; i++) {
                    if((node.ingredients & (1 << i)) > 0) 
                        output += (i + 1) + " ";
                }
                ret.add(output);
            }
        }

        Collections.sort(ret);

        builder.append(min).append("\n");
        builder.append(ret.get(0));

        System.out.println(builder);

    }

    static void init() throws IOException {
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

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 5; j++) {
                ingredients[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }


    static class Node {
        int cost, ingredients;

        public Node(int cost, int ingredients) {
            this.cost = cost; this.ingredients = ingredients;
        }
    }

}
