import java.util.Scanner;

public class BOJ14405 {
    static String input;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        input = scan.nextLine();

        input = input.replaceAll("pi|ka|chu", "");

        if (input.length() > 0)
            System.out.println("NO");
        else
            System.out.println("YES");
    }

}
