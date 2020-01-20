package edx.itmo.week_01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Prob6 {
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

    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            int l1 = in.nextInt();
            int l2 = in.nextInt();
            int l3 = in.nextInt();
            double x1 = 0;
            double y1 = l1 / 2d;
            double x2 = l2 / 2d;
            double y2 = 0;
            double x3 = x2;
            double y3 = y1;
            double ans = (Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)) +
                    Math.sqrt(Math.pow(x1-x3, 2) + Math.pow(y1-y3, 2)) +
                    Math.sqrt(Math.pow(x2-x3, 2) + Math.pow(y2-y3, 2))) / 3d;
            out.println(ans);
        }
    }
}
