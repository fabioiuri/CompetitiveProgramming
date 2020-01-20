package UVA.DataStructures.OneDimArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob414 {

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true) {
            line = br.readLine().trim();
            if (line.equals("0")) break;

            String[] tokens = line.split(" ");
            int n = Integer.parseInt(tokens[0]);
            int counts[] = new int[n];
            int maxCount = Integer.MIN_VALUE;
            // Calculate #X's per row and max X's in a row
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                counts[i] = 0;
                for (int j = 0; j < line.length(); j++) {
                    if (line.charAt(j) == 'X') counts[i]++;
                }
                maxCount = Math.max(maxCount, counts[i]);
            }
            // Calculate diffs between max X's in a row and #X's per row
            int ans = 0;
            for (int i = 0; i < n; i++) {
                ans += maxCount - counts[i];
            }
            System.out.println(ans);
        }
    }
}
