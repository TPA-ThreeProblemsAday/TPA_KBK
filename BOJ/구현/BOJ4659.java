import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4659 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        String input = "";
        while (!(input = reader.readLine()).equals("end")) {
            builder.append("<" + input + "> is ");

            // 좋은 패스워드 여부
            boolean flag = false;
            // 연속된 모음, 자음 갯수
            int vowel = 0, notVowel = 0;
            // 모음 포함 여부
            boolean includeVowel = false;

            char prev = ' ';
            for (int idx = 0; idx < input.length(); idx++) {
                char cur = input.charAt(idx);

                if (isVowel(cur)) {
                    includeVowel = true;
                    vowel++;
                    notVowel = 0;
                } else {
                    notVowel++;
                    vowel = 0;
                }

                if (vowel == 3 || notVowel == 3) {
                    flag = true;
                    break;
                }

                if (prev == cur && cur != 'e' && cur != 'o') {
                    flag = true;
                    break;
                }

                prev = cur;
            }

            if (!includeVowel)
                flag = true;

            if (flag)
                builder.append("not ");

            builder.append("acceptable.\n");
        }

        System.out.println(builder);
    }

    static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
