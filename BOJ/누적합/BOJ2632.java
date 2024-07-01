import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ2632 {
    static int N;

    static int A, B;

    static int[] arr1, arr2;
    static int[] pSumA, pSumB;

    static Map<Integer, Integer> aCnt, bCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        String[] input = reader.readLine().split(" ");

        A = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);

        pSumA = new int[2 * A + 1];
        pSumB = new int[2 * B + 1];

        arr1 = new int[A + 1];
        arr2 = new int[B + 1];

        for (int i = 1; i <= A; i++) {
            arr1[i] = Integer.parseInt(reader.readLine());
            pSumA[i] = pSumA[i - 1] + arr1[i];
        }

        // 원형 자료 구조 -> 선형으로 만들기(뒤에 덧붙이기)
        for (int i = A + 1; i <= 2 * A; i++) {
            pSumA[i] = pSumA[i - 1] + arr1[i - A];
        }

        for (int i = 1; i <= B; i++) {
            arr2[i] = Integer.parseInt(reader.readLine());
            pSumB[i] = pSumB[i - 1] + arr2[i];
        }

        // 원형 자료 구조 -> 선형으로 만들기(뒤에 덧붙이기)
        for (int i = B + 1; i <= 2 * B; i++) {
            pSumB[i] = pSumB[i - 1] + arr2[i - B];
        }

        Map<Integer, Integer> aCnt = make(A, pSumA);
        Map<Integer, Integer> bCnt = make(B, pSumB);

        int ans = aCnt.getOrDefault(N, 0) + bCnt.getOrDefault(N, 0);

        for (int i = 1; i < N; i++) {
            // 조합이므로 곱하기
            ans += aCnt.getOrDefault(i, 0) * bCnt.getOrDefault(N - i, 0);
        }

        System.out.println(ans);
    }

    // 모든 경우의 수를 만듦
    static Map<Integer, Integer> make(int n, int[] pSum) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int interval = 1; interval <= n; interval++) {
            for (int start = interval; start <= n + interval - 1; start++) {
                int sum = pSum[start] - pSum[start - interval];

                // 경우의 수 더해주기
                map.put(sum, map.getOrDefault(sum, 0) + 1);

                if (interval == n)
                    break;
            }
        }

        return map;
    }
}
