package Codeforces._1111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProbB {

    public static int n, k, m;
    public static int[] arr;
    public static long[] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        k = Integer.parseInt(tokens[1]);
        m = Integer.parseInt(tokens[2]);
        arr = new int[n];
        tokens = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }

        Arrays.sort(arr); // Sort array
        prefixSum = new long[n];
        prefixSum[0] = (long) arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i-1] + (long) arr[i];
        }

        int maxRemovalsPossible = Math.min(m, n-1); // Cant remove all (n) superheroes nor exceed the number of ops (m)
        double maxAvg = 0;
        for (int i = 0; i <= maxRemovalsPossible; i++) {
            long currM = m - i;
            if (currM < 0) break;
            long currHeroes = n - i;
            long currSum = f(i);
            // or we add k per each hero or we add all that's left of m
            currSum += Math.min(currHeroes * k, currM);
            double currAvg = currSum / (double) currHeroes;
            maxAvg = Math.max(maxAvg, currAvg);
        }

        System.out.println(maxAvg);
    }

    // Sum by removing first n-th elements
    public static long f(int i) {
        if (i == 0) return prefixSum[n-1];
        return prefixSum[n-1] - prefixSum[i-1];
    }
}
