package Codechef.JCWR2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Type: math
 * Difficulty: very easy
 */
public class JCWC05 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            if (n > 4) {
                System.out.println(String.format("%.6f", 1.0));
            } else if (n == 4){
                System.out.println(String.format("%.6f", 6/7.0)); // 6 / 7
            } else if (n == 3){
                System.out.println(String.format("%.6f", 5/7.0)); // 5 / 7
            } else if (n == 2) {
                System.out.println(String.format("%.6f", 4/7.0)); // 4 / 7
            } else {
                System.out.println(String.format("%.6f", 3/7.0)); // 3 / 7
            }
        }
    }
}
