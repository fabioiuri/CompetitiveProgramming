package Codechef.AUG19B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Type: Difference Array
 * Difficulty: easy
 */
public class ZOMCAV {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int[] c = new int[n];
            int[] h = new int[n];
            int[] flagList = new int[n+10];

            String[] tokens = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                c[i] = Integer.parseInt(tokens[i]);
            }

            tokens = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                h[i] = Integer.parseInt(tokens[i]);
            }

            for (int i = 1; i <= n; i++) {
                int left = Math.max(1, i - c[i-1]);
                int right = Math.min(n, i + c[i-1]);
                flagList[left-1]++;
                flagList[right]--;
            }

            int[] radiations = new int[n];
            radiations[0] = flagList[0];
            for (int i = 1; i < n; i++) {
                radiations[i] = radiations[i-1] + flagList[i];
            }

            //System.out.println(Arrays.toString(radiations));

            Arrays.sort(radiations);
            Arrays.sort(h);

            if (Arrays.equals(radiations, h)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }
    }
}
