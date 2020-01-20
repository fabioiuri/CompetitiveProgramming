package CP_Classes._03_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10664 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String[] tokens = br.readLine().split(" ");
            int n = tokens.length;
            int[] weights = new int[n+1];
            int sumWeights = 0;
            for (int i = 0; i < tokens.length; i++) {
                weights[i+1] = Integer.parseInt(tokens[i]);
                sumWeights += weights[i+1];
            }

            // build dp array: dp[i][j] -> max value possible including first i-th items having j weight available
            // here, value means: amount of total weight
            int[][] dp = new int[weights.length][sumWeights / 2 + 1];

            for (int i = 1; i <= n; i++) { // for each item
                for (int w = 0; w <= sumWeights / 2; w++) { // for each possible weight
                    if (weights[i] > w) {
                        // cant include i-th item because it exceeds available weight
                        dp[i][w] = dp[i-1][w];
                    }
                    else {
                        // check if we maximize the value by evaluating:
                        // 1. not including the i-th item and maintaining w weight available, or
                        // 2. include i-th item but available weight decreases
                        dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weights[i]] + weights[i]);
                    }
                }
            }

            if (sumWeights % 2 != 0 || dp[n][sumWeights / 2] != sumWeights / 2) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
