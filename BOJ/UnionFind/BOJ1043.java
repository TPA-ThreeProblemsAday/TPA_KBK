import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1043 {

    // 사람의 수, 파티 수
    static int N, M;

    static int[] p;

    static BufferedReader reader;

    static ArrayList<Integer>[] parties;
    static final int KNOW_TRUTH = 0;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            makeSet(i);
        }

        st = new StringTokenizer(reader.readLine(), " ");
        int people = Integer.parseInt(st.nextToken());

        // 사실을 알고 있는 그룹은 부모가 0
        for (int p = 0; p < people; p++) {
            union(KNOW_TRUTH, Integer.parseInt(st.nextToken()));
        }

        parties = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine(), " ");
            people = Integer.parseInt(st.nextToken());
            parties[i] = new ArrayList<>();

            // 파티원 정보 기록
            while (st.hasMoreTokens()) {
                parties[i].add(Integer.parseInt(st.nextToken()));
            }

            for (int p = 0; p < people - 1; p++) {
                // 진실을 아는 그룹과 접촉 여부 확인
                union(parties[i].get(p), parties[i].get(p + 1));
            }
        }

        int count = 0;
        outer: for (int i = 0; i < M; i++) {
            for (int person : parties[i]) {
                int px = findSet(person);

                if (px == KNOW_TRUTH)
                    continue outer;
            }

            count++;
        }

        System.out.println(count);

    }

    static void makeSet(int x) {
        p[x] = x;
    }

    static int findSet(int x) {
        if (p[x] == x)
            return x;
        else
            return p[x] = findSet(p[x]);
    }

    static void union(int x, int y) {
        int px = findSet(x);
        int py = findSet(y);

        if (px < py)
            p[py] = px;
        else
            p[px] = py;
    }
}
