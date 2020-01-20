package CP_Classes._03_DynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _674 {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] coins = new int[]{1, 5, 10, 25, 50};
        int[] ways = new int[7500];
        ways[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int v = coins[i]; v < ways.length; v++) {
                ways[v] += ways[v - coins[i]];
            }
        }

        try {
            while (true) {
                int n = Integer.parseInt(br.readLine());
                System.out.println(ways[n]);
            }
        } catch (Exception e) { }
    }
}
