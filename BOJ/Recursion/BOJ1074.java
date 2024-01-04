import java.util.Scanner;

public class BOJ1074 {
    static int size;
    static int row, col;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        size = scan.nextInt();
        row = scan.nextInt();
        col = scan.nextInt();

        System.out.println(solution(row, col, size));
    }

    static int solution(int r, int c, int num) {
        if (num == 0)
            return 0;

        int half = 1 << (num - 1);

        // 1번 사각형
        if (r < half && c < half)
            return solution(r, c, num - 1);
        // 2번 사각형
        if (r < half && c >= half)
            return half * half + solution(r, c - half, num - 1);
        // 3번 사각형
        if (r >= half && c < half)
            return 2 * half * half + solution(r - half, c, num - 1);

        return 3 * half * half + solution(r - half, c - half, num - 1);
    }
}
