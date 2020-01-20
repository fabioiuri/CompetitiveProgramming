import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Prob3 {

    public static final long[] t = new long[100010];
    public static final boolean[] flags = new boolean[100010];

    static Scanner newInput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new Scanner(new File("input.txt"));
        } else {
            return new Scanner(System.in);
        }
    }

    static PrintWriter newOutput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new PrintWriter("output.txt");
        } else {
            return new PrintWriter(System.out);
        }
    }

    public static long bonacci(int n) {
        if (!flags[n]) {
            flags[n] = true;
            t[n] = bonacci(n-1) + bonacci(n-2) - bonacci(n-3);
        }
        return t[n];
    }

    public static void main(String[] args) throws IOException {
        for(int i = 0; i < flags.length; i++) {
            flags[i] = false;
        }
        try (Scanner sc = newInput(); PrintWriter pw = newOutput()) {
            t[0] = sc.nextInt();
            t[1] = sc.nextInt();
            t[2] = sc.nextInt();
            flags[0] = flags[1] = flags[2] = true;
            int n = sc.nextInt();
            pw.println(bonacci(n));
        }
    }
}
