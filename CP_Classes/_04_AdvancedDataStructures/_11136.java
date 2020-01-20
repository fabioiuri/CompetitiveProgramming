package CP_Classes._04_AdvancedDataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class _11136 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;

        while ((n = Integer.parseInt(br.readLine())) != 0) {
            TreeMap<Integer, Integer> tmap = new TreeMap<>();
            long cost = 0;
            for (int i = 0; i < n; i++) {
                // Insert daily bills into tree map
                String[] tokens = br.readLine().split(" ");
                for (int j = 1; j < tokens.length; j++) {
                    int bill = Integer.parseInt(tokens[j]);
                    Integer billCount = tmap.get(bill);
                    if (billCount == null) tmap.put(bill, 1);
                    else tmap.put(bill, billCount + 1);
                }
                // Remove / update bill entries
                int biggestBill = tmap.lastKey();
                int biggestBillCount = tmap.get(biggestBill);
                if (biggestBillCount == 1) tmap.remove(biggestBill);
                else tmap.put(biggestBill, biggestBillCount - 1);

                int smallestBill = tmap.firstKey();
                int smallestBillCount = tmap.get(smallestBill);
                if (smallestBillCount == 1) tmap.remove(smallestBill);
                else tmap.put(smallestBill, smallestBillCount - 1);

                // Compute cost
                cost += biggestBill - smallestBill;
            }
            System.out.println(cost);
        }
    }
}
