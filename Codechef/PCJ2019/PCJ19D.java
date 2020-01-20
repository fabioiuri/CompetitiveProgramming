package Codechef.PCJ2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PCJ19D {

    static int a, b, c, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");

        a = Integer.parseInt(tokens[0]);
        b = Integer.parseInt(tokens[1]);
        c = Integer.parseInt(tokens[2]);
        n = Integer.parseInt(tokens[3]);

        int solI = 0, solJ = 0, solZ = 0;
        boolean found = false;
        for (int i = 0; i < 1001; i++) {
            if (found) break;
            for (int j = 0; j < 1001; j++) {
                if (found) break;
                int zc = (c == 0) ? 0 : (n - a*i - b*j);
                int curr = i*a + j*b + zc;
                if (curr == n && (c == 0 || (zc == (zc / c) * c && (zc / c) >= 0))) {
                    int z = zc / c;
//                    if (!found || (z > i || solJ > )) {
//
//                    }
                    solI = i;
                    solJ = j;
                    solZ = z;
                    found = true;
                }
            }
        }

        System.out.println(solI + " " + solJ + " " + solZ);
    }
}
