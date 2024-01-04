import java.io.IOException;
import java.util.Scanner;

public class BOJ2447 {
    static int size;
    static StringBuilder builder;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        builder = new StringBuilder();

        size = scanner.nextInt();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++)
                solution(row, col, size / 3);

            builder.append("\n");
        }

        System.out.println(builder);
    }

    static void solution(int row, int col, int num) {
        if ((row / num) % 3 == 1 && (col / num) % 3 == 1)
            builder.append(" ");
        else {
            if (num == 1)
                builder.append("*");
            else
                solution(row, col, num / 3);
        }
    }
}
