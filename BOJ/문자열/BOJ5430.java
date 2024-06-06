import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ5430 {

    static String p, arr;
    static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        StringTokenizer tokenizer = null;
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        T = Integer.parseInt(reader.readLine());

        while (T-- > 0) {
            p = reader.readLine();
            N = Integer.parseInt(reader.readLine());
            arr = reader.readLine();

            deque.clear();

            tokenizer = new StringTokenizer(arr.substring(1, arr.length() - 1), ",");

            while (tokenizer.hasMoreTokens())
                deque.add(Integer.parseInt(tokenizer.nextToken()));

            boolean error = false, rev = false;
            for (char order : p.toCharArray()) {
                if (order == 'R') {
                    rev = !rev;
                } else if (order == 'D') {
                    if (deque.isEmpty()) {
                        error = true;
                        break;
                    }

                    if (rev)
                        deque.removeLast();
                    else
                        deque.removeFirst();

                }
            }

            if (error) {
                builder.append("error\n");
                continue;
            }

            builder.append("[");
            if (rev) {
                while (!deque.isEmpty()) {
                    builder.append(deque.removeLast());
                    if (!deque.isEmpty())
                        builder.append(",");
                }
            } else {
                while (!deque.isEmpty()) {
                    builder.append(deque.removeFirst());
                    if (!deque.isEmpty())
                        builder.append(",");
                }
            }
            builder.append("]\n");

        }

        System.out.println(builder);
    }

}
