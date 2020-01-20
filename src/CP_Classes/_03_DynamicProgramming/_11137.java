package CP_Classes._03_DynamicProgramming;

import java.util.Scanner;

public class _11137 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long[] ways = new long[10000];
        ways[0] = 1;
        
        for (int i = 1; i < 22; i++) {
            int cubeCoin = (int) Math.pow(i, 3);

            for (int v = cubeCoin; v < 10000; v++) {
                ways[v] += ways[v - cubeCoin];
            }
        }

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            System.out.println(ways[n]);
        }
    }
}
