package Codeforces._1111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProbC {

    public static int n, k, A, B;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        k = Integer.parseInt(tokens[1]);
        A = Integer.parseInt(tokens[2]);
        B = Integer.parseInt(tokens[3]);
        tokens = br.readLine().split(" ");
        arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }

        Arrays.sort(arr);
        System.out.println(calc(1, 1 << n));
    }

    private static int lowerBound(long heroPos){
        int low = 0; int high = k;
        while(low < high) {
            int middle = ((high + low) / 2);
            if (heroPos > arr[middle]) low = middle + 1;
            else high = middle;
        }
        return low;
    }


    private static int upperBound(long heroPos){
        int low = 0; int high = k;
        while(low < high) {
            int middle = ((high + low) / 2);
            if (arr[middle] > heroPos) high = middle;
            else low = middle + 1;
        }
        return low;
    }

    public static long calc(int l, int r) {
        // Can split
        int idxL = lowerBound(l);
        int idxR = upperBound(r);
        int length = r - l + 1;
        int nAvengers = idxR - idxL;

        // Compute no split cost and check if splitting is worth checking
        long nosplitCost = (nAvengers == 0) ? A : (long) B * nAvengers * length;
        if (nAvengers == 0 || l == r) {
            return nosplitCost;
        }

        // Try splitting in middle
        int mid = (r + l) / 2;
        long splitCost = calc(l, mid) + calc(mid + 1, r);

//        long min = Math.min(splitCost, nosplitCost);
//        System.out.println("l="+l+",r="+r+" => " +min);
        return Math.min(splitCost, nosplitCost);
    }
}
