package Codechef.Practice.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MARRAYS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<ArrayList<Long>> dishes = new ArrayList<>();
            int maxIngListSize = 0;

            for (int d = 0; d < n; d++) {
                ArrayList<Long> ingredients = new ArrayList<>();
                String[] tokens = br.readLine().split(" ");
                for (int i = 1; i < tokens.length; i++) {
                    ingredients.add(Long.parseLong(tokens[i]));
                }
                maxIngListSize = Math.max(maxIngListSize, ingredients.size());
                dishes.add(ingredients);
            }

            long sol = 0;
            long maxPos = Long.MIN_VALUE, maxNeg = Long.MIN_VALUE;
            long newMaxPos = Long.MIN_VALUE, newMaxNeg = Long.MIN_VALUE;

            ArrayList<Long> ingredients = dishes.get(0);
            for (int j = 0; j < ingredients.size(); j++) {
                maxPos = Math.max(maxPos, - ingredients.get(j));
                maxNeg = Math.max(maxNeg, ingredients.get(j));
            }

            for (int i = 1; i < n; i++) {
                ingredients = dishes.get(i);
                for (int j = 0; j < ingredients.size(); j++) {
                    int lastElem = (j == 0) ? ingredients.size() - 1: j - 1; // circular arr

                    long v = Math.max(maxPos + ingredients.get(j) * (long)i, maxNeg - ingredients.get(j) * (long)i);
                    newMaxPos = Math.max(newMaxPos, v - ingredients.get(lastElem) * (long)(i+1));
                    newMaxNeg = Math.max(newMaxNeg, v + ingredients.get(lastElem) * (long)(i+1));

                    if (i == n - 1) {
                        sol = Math.max(sol, v);
                    }
                }
                maxPos = newMaxPos;
                maxNeg = newMaxNeg;
                newMaxPos = Long.MIN_VALUE;
                newMaxNeg = Long.MIN_VALUE;
            }

            System.out.println(sol);
        }
    }
}
