package Codeforces._1114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class ProbB {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        int k = Integer.parseInt(tokens[2]);
        ArrayList<Pair> arr = new ArrayList<>(n);
        tokens = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr.add(new Pair(Integer.parseInt(tokens[i]), i));
        }

        // Sort elems in descendant order
        Collections.sort(arr, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return - Integer.compare(o1.val, o2.val);
            }
        });

        // Compute sum os max k*m elems
        long sum = 0;
        int[] idxs = new int[k*m];
        for (int i = 0; i < k*m; i++) {
            Pair pair = arr.get(i);
            sum += (long) pair.val;
            idxs[i] = pair.idx;
        }

        // Compute partitions
        Arrays.sort(idxs);
        StringBuilder ps = new StringBuilder();
        for (int i = 0; i < k-1; i++) {
            ps.append(idxs[m * (i+1) - 1] + 1).append(" ");
        }

        System.out.println(sum);
        System.out.println(ps.toString().trim());
    }

    static class Pair {
        int val, idx;

        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        public String toString() {
            return "(" + val + ", " + idx + ")";
        }
    }
}
