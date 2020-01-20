package Swerc._2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ProbD {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int xDim, yDim;
        String[] s = br.readLine().split(" ");
        xDim = Integer.parseInt(s[0]);
        yDim = Integer.parseInt(s[1]);
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> list =  new ArrayList<>();
        int[] max = new int[xDim];
        int[] min = new int[xDim];

        for (int i = 0; i < max.length; i++) {
            max[i] = Integer.MIN_VALUE;
            min[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            int x, y;
            s = br.readLine().split(" ");
            x = Integer.parseInt(s[0]);
            y = Integer.parseInt(s[1]);
            min[x] = Math.min(min[x], y);
            max[x] = Math.max(max[x], y);
        }

        for (int i = 0; i < max.length; i++) {
            if (max[i] != Integer.MIN_VALUE) list.add(max[i]);
            if (max[i] != min[i]) {
                if (min[i] != Integer.MAX_VALUE) list.add(min[i]);
            }
        }

        Collections.sort(list);

        int bestN = -1;
        if (list.size() == 1) {
            bestN = list.get(0);
        } else if (list.size() % 2 == 0) {
            bestN = (int) Math.round((list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2d);
        } else {
            bestN = list.get(list.size() / 2);
        }

        long cost = xDim - 1;
        for (int i = 0; i < xDim; i++) {
            int diffMax = (max[i] == Integer.MIN_VALUE) ? 0 : bestN - max[i];
            int diffMin = (min[i] == Integer.MAX_VALUE) ? 0 : bestN - min[i];
            if (max[i] == min[i]) {
                cost += 2 * Math.abs(diffMax);
            } else if (diffMax >= 0 && diffMin >= 0) {
                cost += 2 * Math.abs(diffMin);
            } else if (diffMax <= 0 && diffMin <= 0) {
                cost += 2 * Math.abs(diffMax);
            } else {
                cost += 2 * Math.abs(diffMax);
                cost += 2 * Math.abs(diffMin);
            }
        }

        // System.out.println(bestN);
        System.out.println(cost);
    }

}
