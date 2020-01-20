package Codechef.PCJ2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PCJ19E {

    static int n;
    static int[][] m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                m[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        if (solve(0,0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static boolean solve(int i, int j) {
        if (i >= n || j >= n || m[i][j] % 2 != 0) return false;
        if (i == n-1 && j == n-1) return true;

        return solve(i+1, j) || solve(i, j+1);
    }
}
