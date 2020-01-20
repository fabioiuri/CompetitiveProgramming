package Saved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class kadane {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N];
            String[] tokens = br.readLine().split(" ");
            for (int i = 0; i < A.length; i++) {
                A[i] =  Integer.parseInt(tokens[i]);
            }
            System.out.println("kadane: " + kadane(N, A));
            System.out.println("circular kadane: " + maxCircularSum(N, A));
        }
    }

    private static int maxCircularSum(int n, int[] a) {
        int maxKadane = kadane(n, a); // Case 1: standard

        // Case 2: Now find the maximum sum that includes corner elements.
        int maxWrap = a[0], i;
        a[0] = -a[0];
        for (i = 1; i < n; i++) {
            maxWrap += a[i];
            a[i] = -a[i]; // invert the array (change sign)
        }

        // Max sum with corner elements will be: array-sum - (- max subarray sum of inverted array)
        maxWrap = maxWrap + kadane(n, a);

        return maxKadane < 0 ? Math.min(maxKadane, maxWrap) : Math.max(maxKadane, maxWrap);
    }


    private static int kadane(int n, int[] a) {
        int maxS = a[0];
        int s = a[0];
        for (int i = 1; i < n; i++) {
            s = Math.max(a[i], s + a[i]);
            maxS = Math.max(maxS, s);
        }
        return maxS;
    }
}
