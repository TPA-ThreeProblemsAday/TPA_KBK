import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16235 {

    // 겨울에 추가되는 양분
    static int[][] nutrients;

    // 현재 땅의 양분 정보
    static int[][] ground;

    // 나무의 정보
    static ArrayList<Tree>[][] trees;

    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        nutrients = new int[N][N];
        ground = new int[N][N];
        trees = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            for (int j = 0; j < N; j++) {
                nutrients[i][j] = Integer.parseInt(tokenizer.nextToken());
                ground[i][j] = 5;
                trees[i][j] = new ArrayList<>();
            }
        }

        int x = 0, y = 0, z = 0;
        for (int m = 0; m < M; m++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            x = Integer.parseInt(tokenizer.nextToken());
            y = Integer.parseInt(tokenizer.nextToken());
            z = Integer.parseInt(tokenizer.nextToken());
            // 나무 정보 추가
            trees[x - 1][y - 1].add(new Tree(z));
        }

        // K년 반복
        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(countTree());
    }

    static int countTree() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                total += trees[i][j].size();
            }
        }

        return total;
    }

    static void spring() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Collections.sort(trees[i][j]);
                for (Tree tree : trees[i][j]) {
                    if (ground[i][j] >= tree.age) {
                        ground[i][j] -= tree.age;
                        tree.age++;
                    } else {
                        tree.isDead = true;
                    }
                }
            }
        }
    }

    static void summer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Iterator<Tree> it = trees[i][j].iterator(); it.hasNext();) {
                    Tree tree = it.next();
                    if (tree.isDead) {
                        ground[i][j] += tree.age / 2;
                        it.remove();
                    }
                }
            }
        }
    }

    static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static void fall() {
        int nr = 0, nc = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Tree tree : trees[i][j]) {
                    if (tree.age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            nr = i + dr[d];
                            nc = j + dc[d];

                            if (nr < 0 || nc < 0 || nr >= N || nc >= N)
                                continue;

                            trees[nr][nc].add(new Tree(1));
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j] += nutrients[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree> {
        int age;
        boolean isDead;

        public Tree(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }
}
