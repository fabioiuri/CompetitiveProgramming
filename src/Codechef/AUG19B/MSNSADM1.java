package Codechef.AUG19B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Type: -
 * Difficulty: very easy
 */
public class MSNSADM1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] a = new int[n];
            int[] b = new int[n];

            String[] tokens = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(tokens[i]);
            }
            tokens = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                b[i] = Integer.parseInt(tokens[i]);
            }

            int max = -1;
            for (int i = 0; i < n; i++) {
                int points = Math.max(a[i] * 20 - b[i] * 10, 0);
                max = Math.max(max, points);
            }

            System.out.println(max);
        }
    }
}
