package UVA.DynamicProgramming.LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class _231 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = 1;
        while (true) {
                        ArrayList<Integer> values = new ArrayList<>();

            try {
                while (true) {
                    String line = br.readLine();
                    if (line.equals("-1")) break;
                    values.add(-Integer.parseInt(line));
                }
            } catch (Exception _) { }

            if (values.isEmpty()) break;

            if (t > 1) {
                System.out.println();
            }

            LinkedList<Integer> lis = LIS(values);
            System.out.println("Test #" + t++ + ":");
            System.out.println("  maximum possible interceptions: " + lis.size());
        }
    }

    private static LinkedList<Integer> LIS(ArrayList<Integer> values) {
        int[] sequenceEndIdxs = new int[values.size()];
        int[] prevPointer = new int[values.size()];
        sequenceEndIdxs[0] = 0;
        prevPointer[0] = -1;
        int nSequences = 1;

        for (int i = 1; i < values.size(); i++) {
            Integer currValue = values.get(i);
            int seqId = upperBound(values, sequenceEndIdxs, nSequences, currValue);

            prevPointer[i] = (seqId > 0) ? sequenceEndIdxs[seqId-1] : -1;
            if (seqId == nSequences) {
                sequenceEndIdxs[nSequences++] = i;
            } else {
                sequenceEndIdxs[seqId] = i;
            }
        }

        LinkedList<Integer> solution = new LinkedList<>();
        int currIdx = sequenceEndIdxs[nSequences-1];
        while (true) {
            solution.addFirst(values.get(currIdx));
            if (prevPointer[currIdx] > -1) {
                currIdx = prevPointer[currIdx];
            } else {
                break;
            }
        }

        return solution;
    }

    private static int upperBound(ArrayList<Integer> values, int[] sequenceEndIdxs, int limit, int value){
        int low = 0; int high = limit;
        while(low < high) {
            int middle = ((high + low) / 2);
            if (value >= values.get(sequenceEndIdxs[middle])) low = middle + 1;
            else high = middle;
        }
        return low; // lowest idx to insert w/o breaking ordering
    }
}
