package CP_Classes._03_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10130 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] values = new int[n];
            int[] weights = new int[n];

            for (int i = 0; i < n; i++) {
                String[] tokens = br.readLine().split(" ");
                values[i] = Integer.parseInt(tokens[0]);
                weights[i] = Integer.parseInt(tokens[1]);
            }

            // Get max weights for each person + compute max weight to define dp array
            int g = Integer.parseInt(br.readLine());
            int[] ps = new int[g];
            int maxWeight = -1;
            for (int i = 0; i < g; i++) {
                ps[i] = Integer.parseInt(br.readLine());
                maxWeight = Math.max(maxWeight, ps[i]);
            }

            // build dp array: dp[i][j] -> max value possible including first i-th items having j weight available
            int[][] dp = new int[n+1][maxWeight+1];

            int sum = 0;
            for (int j = 0; j < ps.length; j++) { // for each person
                int currMaxWeight = ps[j];
                for (int i = 1; i <= n; i++) { // for each item
                    for (int w = 0; w <= currMaxWeight; w++) { // for each possible weight
                        if (weights[i-1] > w) {
                            // cant include i-th item because it exceeds available weight
                            dp[i][w] = dp[i-1][w];
                        }
                        else {
                            // check if we maximize the value by evaluating:
                            // 1. not including the i-th item and maintaining w weight available, or
                            // 2. include i-th item but available weight decreases
                            dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weights[i-1]] + values[i-1]);
                        }
                    }
                }
                sum += dp[n][currMaxWeight]; // sum best possible result for current person
            }

            System.out.println(sum);
        }
    }
}
