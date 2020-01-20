package UVA.DataStructures.OneDimArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob11222 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[][] probs = new int[3][10010];
        int[] solved = new int[3];
        for (int nt = 1; nt <= t; nt++) {
            // Fill buckets
            for (int i = 0; i < 3; i++) {
                String[] tokens = br.readLine().split(" ");
                int n = Integer.parseInt(tokens[0]);
                solved[i] = n;
                for (int j = 1; j <= n; j++) {
                    probs[i][Integer.parseInt(tokens[j])] = 1;
                }
            }
            // Compute diffs
            for (int i = 0; i <= 10000; i++) {
                if (probs[0][i] + probs[1][i] + probs[2][i] > 1) {
                    solved[0] -= probs[0][i];
                    solved[1] -= probs[1][i];
                    solved[2] -= probs[2][i];
                    probs[0][i] = 0; probs[1][i] = 0; probs[2][i] = 0;
                }
            }
            // Compute answer
            System.out.println("Case #" + nt + ":");
            int max = Math.max(solved[0], Math.max(solved[1], solved[2]));
            for (int i = 0; i < 3; i++) {
                if (max == solved[i]) {
                    StringBuilder ans = new StringBuilder();
                    ans.append(i+1).append(" ").append(max);
                    for (int j = 0; j <= 10000; j++) {
                        if (probs[i][j] > 0) {
                            ans.append(" ").append(j);
                        }
                    }
                    System.out.println(ans);
                }
                // Clean for next test
                for (int j = 0; j <= 10000; j++) {
                    probs[i][j] = 0;
                }
                solved[i] = 0;
            }
        }
    }
}
