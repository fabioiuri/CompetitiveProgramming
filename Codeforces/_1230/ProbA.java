package Codeforces._1230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProbA {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int a1 = Integer.parseInt(tokens[0]);
        int a2 = Integer.parseInt(tokens[1]);
        int a3 = Integer.parseInt(tokens[2]);
        int a4 = Integer.parseInt(tokens[3]);

        boolean possible =
                a1 + a2 == a3 + a4 ||
                a1 + a3 == a2 + a4 ||
                a1 + a4 == a2 + a3 ||
                a1 == a2 + a3 + a4 ||
                a2 == a1 + a3 + a4 ||
                a3 == a1 + a2 + a4 ||
                a4 == a1 + a2 + a3;
        if (possible) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
