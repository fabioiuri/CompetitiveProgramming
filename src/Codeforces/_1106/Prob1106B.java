package Codeforces._1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Prob1106B {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        int[] remains = new int[n];
        int[] costs = new int[n];
        ArrayList<Dish> dishes = new ArrayList<>(n);
        // Read dishes qty and costs
        tokens = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            remains[i] = Integer.parseInt(tokens[i]);
            dishes.add(new Dish());
        }
        tokens = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            costs[i] = Integer.parseInt(tokens[i]);
            dishes.get(i).cost = costs[i];
            dishes.get(i).remainIdx = i;
        }
        // Sort dishes by cost
        Collections.sort(dishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                int cmp = Integer.compare(o1.cost, o2.cost);
                if (cmp == 0) cmp = Integer.compare(o1.remainIdx, o2.remainIdx);
                return cmp;
            }
        });
        // System.out.println(Arrays.toString(dishes.toArray()));
        int cheapestIdx = 0;

        // Read and serve requests
        for (int i = 0; i < m; i++) {
            tokens = br.readLine().split(" ");
            int dish = Integer.parseInt(tokens[0]) - 1;
            int qty = Integer.parseInt(tokens[1]);
            long cost = 0;
            if (qty > remains[dish]) {
                qty -= remains[dish];
                cost += (long) costs[dish] * remains[dish];
                remains[dish] = 0;

                Dish currCheapest;
                while (qty > 0) {
                    if (cheapestIdx >= n) {
                        cost = 0;
                        break;
                    }
                    currCheapest = dishes.get(cheapestIdx);

                    if (qty >= remains[currCheapest.remainIdx]) {
                        qty -= remains[currCheapest.remainIdx];
                        cost += (long) currCheapest.cost * remains[currCheapest.remainIdx];
                        remains[currCheapest.remainIdx] = 0;
                        cheapestIdx++;

                    } else {
                        remains[currCheapest.remainIdx] -= qty;
                        cost += (long) currCheapest.cost * qty;
                        qty = 0;
                    }
                }
            } else {
                remains[dish] -= qty;
                cost += (long) costs[dish] * qty;
            }
            System.out.println(cost);
        }
    }

    static class Dish {
        int cost, remainIdx;

        public String toString() {
            return "(" + remainIdx + "," + cost + ")";
        }
    }
}
