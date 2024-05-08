import java.util.Scanner;

public class BOJ1436 {
    static int N;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();

        int temp = 666;

        while (N != 0) {
            if ((temp + "").contains("666"))
                N--;
            temp++;
        }

        System.out.println(temp);
    }
}
