package Codeforces._1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob1106A {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int n = Integer.parseInt(line);
        boolean[][] m = new boolean[n][n];
        // Read matrix
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            for (int j = 0; j < n; j++) {
                m[i][j] = line.charAt(j) == 'X';
            }
        }
        // Find crosses
        int crosses = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    if (m[i][j] && m[i - 1][j - 1] && m[i - 1][j + 1] && m[i + 1][j - 1] && m[i + 1][j + 1])
                        crosses++;
                } catch (Exception e) {
                    // good trick right? :)
                }
            }
        }
        System.out.println(crosses);
    }
}
