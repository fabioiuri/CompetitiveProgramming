package Codeforces._1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProbA {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());

        for (int q = 0; q < Q; q++) {
            String[] tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int n = Integer.parseInt(tokens[2]);
            int v = Integer.parseInt(tokens[3]);

            if (v > n * a) {
                v -= n * a;
            } else if (a >= Math.floorDiv(v, n)){
                v -= Math.floorDiv(v, n) * n;
            }

            if (b >= v) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
