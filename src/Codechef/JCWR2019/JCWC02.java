package Codechef.JCWR2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Type: DP
 * Difficulty: easy
 */
public class JCWC02 {

    static final long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            long[] evens = new long[n];
            long[] odds = new long[n];

            for (int i = 0; i < n; i++) {
                String[] tokens = br.readLine().split(" ");
                long l = Long.parseLong(tokens[0]);
                long r = Long.parseLong(tokens[1]);
                int numEvens = 0;
                int numOdds = 0;

                if (l % 2 == 0) {
                    numEvens++;
                } else {
                    numOdds++;
                }

                if (r % 2 == 0) {
                    numEvens++;
                } else {
                    numOdds++;
                }

                if (i == 0) {
                    evens[i] = numEvens;
                    odds[i] = numOdds;
                } else {
                    evens[i] = ((numEvens * evens[i-1]) % MOD + (numOdds * odds[i-1]) % MOD) % MOD;
                    odds[i] = ((numOdds * evens[i-1]) % MOD + (numEvens * odds[i-1]) % MOD) % MOD;
                }

               // System.out.println("evens[" + i + "] = " + evens[i]);
               // System.out.println("odds[" + i + "] = " + odds[i]);
            }

            System.out.println(odds[n-1]);
        }
    }
}
