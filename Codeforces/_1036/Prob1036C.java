package Codeforces._1036;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Type: Digit DP
 * Difficulty: medium
 */
public class Prob1036C {

    static long[][][] dp = new long[20][20][2]; // pos, numZeroDigits, isSmaller
    static ArrayList<Integer> num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] tokens = br.readLine().split(" ");
            long L = Long.parseLong(tokens[0]);
            long R = Long.parseLong(tokens[1]);

            num = new ArrayList<>();
            long tmp = R;
            while(tmp > 0) {
                num.add(0, (int)(tmp % 10));
                tmp /= 10;
            }

//            System.out.println(Arrays.toString(num.toArray()));

            dp = new long[20][20][2];
            long fR = calc(0, 0, false);

            num = new ArrayList<>();
            tmp = L - 1;
            while(tmp > 0) {
                num.add(0, (int)(tmp % 10));
                tmp /= 10;
            }

            dp = new long[20][20][2];
//            System.out.println(Arrays.toString(num.toArray()));

            long fL = calc(0, 0, false);

//            System.out.println("fR = " + fR);
//            System.out.println("fL = " + fL);
            System.out.println(fR - fL);
        }
    }

    static long calc(int pos, int numZeroDigits, boolean isSmaller) {
        if (pos == num.size()) { // done?
            if (numZeroDigits <= 3) return 1;
            return 0;
        }

//        System.out.println(">> pos = " + pos);
//        System.out.println(">> numZeroDigits = " + numZeroDigits);

        if (dp[pos][numZeroDigits][(isSmaller) ? 1 : 0] != 0) { // already computed?
            return dp[pos][numZeroDigits][(isSmaller) ? 1 : 0];
        }

        if (numZeroDigits > 3) { // early stop to speed up
            return dp[pos][numZeroDigits][(isSmaller) ? 1 : 0] = 0;
        }

        int maxDigit = 9;
        if (!isSmaller) { // if need to limit max digit, do it!
            maxDigit = num.get(pos);
        }


        long count = 0;
        for (int d = 0; d <= maxDigit; d++) {
            int newNumZeroDigits = numZeroDigits;
            // if the current placed digit is > 0, this means that we will form numbers with the format: X____
            // we know for a fact, that for each digit, we can set the next one to 0, therefore increasing the number
            // of zero digits by 1. Ex: 10, 20, 30, ...
            // This simplifies the whole process, due to the cases where we generate 0___ numbers, which do NOT
            // increase the number of zero digits.
            if (d > 0) { newNumZeroDigits++; }

            if (newNumZeroDigits <= 3) {
                boolean newIsSmaller = isSmaller || (!isSmaller && d < maxDigit);
                count += calc(pos + 1, newNumZeroDigits, newIsSmaller);
            }
        }

        return dp[pos][numZeroDigits][(isSmaller) ? 1 : 0] = count;
    }
}
