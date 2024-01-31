import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ19942 {

    static BufferedReader reader;
    static StringBuilder builder;

    // 식재료 개수
    static int N;

    // 단백질, 지방, 탄수화물, 비타민 최소 영양성분
    static int mp, mf, ms, mv;

    static int[] p;
    static int[] f;
    static int[] s;
    static int[] v;
    static int[] c;

    static int min = Integer.MAX_VALUE;

    static int tp, tf, ts, tv, tc;

    static ArrayList<Node> chosen = new ArrayList<>();

    static class Node {
        int ingredients, costs;

        public Node(int ingredients, int costs) {
            this.ingredients = ingredients;
            this.costs = costs;
        }
    }

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        // 입력
        init();

        for (int i = 1; i < (1 << N); i++) {
            // 값 초기화
            tp = 0;
            tf = 0;
            ts = 0;
            tv = 0;
            tc = 0;

            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) > 0) {
                    tp += p[j];
                    tf += f[j];
                    ts += s[j];
                    tv += v[j];
                    tc += c[j];
                }
            }

            check(i);
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        builder.append(min + "\n");

        ArrayList<String> list = new ArrayList<>();
        String temp = null;
        for (Node node : chosen) {
            temp = "";
            if (node.costs == min) {
                for (int idx = 0; idx < N; idx++) {
                    if ((node.ingredients & (1 << idx)) > 0)
                        temp += (idx + 1) + " ";
                }
                list.add(temp);
            }
        }

        Collections.sort(list);
        builder.append(list.get(0));
        System.out.println(builder);

    }

    static void check(int ingredients) {
        if (tp < mp || tf < mf || ts < ms || tv < mv)
            return;

        // 최솟값 찾기
        if (min >= tc) {
            min = tc;
            chosen.add(new Node(ingredients, tc));
        }
    }

    static void init() throws IOException {
        N = Integer.parseInt(reader.readLine());

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        mp = Integer.parseInt(tokenizer.nextToken());
        mf = Integer.parseInt(tokenizer.nextToken());
        ms = Integer.parseInt(tokenizer.nextToken());
        mv = Integer.parseInt(tokenizer.nextToken());

        p = new int[N];
        f = new int[N];
        s = new int[N];
        v = new int[N];
        c = new int[N];

        for (int n = 0; n < N; n++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            p[n] = Integer.parseInt(tokenizer.nextToken());
            f[n] = Integer.parseInt(tokenizer.nextToken());
            s[n] = Integer.parseInt(tokenizer.nextToken());
            v[n] = Integer.parseInt(tokenizer.nextToken());
            c[n] = Integer.parseInt(tokenizer.nextToken());
        }

    }

}
