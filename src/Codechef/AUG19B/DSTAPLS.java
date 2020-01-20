package Codechef.AUG19B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Type: math
 * Difficulty: very easy
 */
public class DSTAPLS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            long n, k;
            String[] tokens = br.readLine().split(" ");

            n = Long.parseLong(tokens[0]);
            k = Long.parseLong(tokens[1]);

            if (k == 1 || (n / k) % k == 0) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
