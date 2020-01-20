package UVA.DynamicProgramming.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class _497 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        br.readLine(); // blank line

        for (int t = 0; t < T; t++) {
            ArrayList<Integer> values = new ArrayList<>();

            String line = br.readLine();
            while (line != null && line.trim().length() > 0) {
                values.add(Integer.parseInt(line));
                line = br.readLine();
            }

            LinkedList<Integer> lis = LIS(values);

            System.out.println("Max hits: " + lis.size());
            for (Integer i : lis) {
                System.out.println(i);
            }
            if (t != T-1) {
                System.out.println();
            }
        }
    }

    private static LinkedList<Integer> LIS(ArrayList<Integer> values) {
        int[] sequenceEndIdxs = new int[values.size()];
        int[] prevPointer = new int[values.size()];
        sequenceEndIdxs[0] = 0; prevPointer[0] = -1;
        int nSequences = 1;

        for (int i = 1; i < values.size(); i++) {
            int seqId = lowerBound(values, sequenceEndIdxs, nSequences, values.get(i));
            prevPointer[i] = (seqId > 0) ? sequenceEndIdxs[seqId-1] : -1;
            sequenceEndIdxs[seqId] = i;
            if (seqId == nSequences) nSequences++;
        }

        LinkedList<Integer> solution = new LinkedList<>();
        int currIdx = sequenceEndIdxs[nSequences-1];
        while (true) {
            solution.addFirst(values.get(currIdx));
            if (prevPointer[currIdx] > -1) currIdx = prevPointer[currIdx];
            else break;
        }

        return solution;
    }

    private static int lowerBound(ArrayList<Integer> values, int[] sequenceEndIdxs, int limit, int value){
        int low = 0; int high = limit;
        while(low < high) {
            int middle = ((high + low) / 2);
            if (value > values.get(sequenceEndIdxs[middle])) low = middle + 1;
            else high = middle;
        }
        return low; // lowest idx to insert w/o breaking ordering
    }
}
