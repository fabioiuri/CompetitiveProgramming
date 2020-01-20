package Codeforces._1285;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProbB {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] tokens = br.readLine().split(" ");
            int[] arr = new int[n];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokens[i]);
                sum += (long) arr[i];
            }

            long sumOther = Math.max(kadane(0, n-1, arr), kadane(1, n, arr));
            if (sumOther < sum) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static long kadane(int start, int n, int[] a) {
        long maxS = a[start];
        long s = a[start];
        for (int i = start+1; i < n; i++) {
            s = Math.max(a[i], s + a[i]);
            maxS = Math.max(maxS, s);
        }
        return maxS;
    }

}
