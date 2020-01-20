package UVA.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Prob11389 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        while (!s.equals("0 0 0")) {
            String[] tokens = s.split(" ");
            int n = Integer.parseInt(tokens[0]);
            int d = Integer.parseInt(tokens[1]);
            int r = Integer.parseInt(tokens[2]);

            int[] morning = new int[n];
            int[] afternoon = new int[n];

            tokens = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                morning[i] = -Integer.parseInt(tokens[i]);
            }
            tokens = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                afternoon[i] = Integer.parseInt(tokens[i]);
            }

            Arrays.sort(morning);
            Arrays.sort(afternoon);

            int extraHours = 0;
            for (int i = 0; i < n; i++) {
                int diff = Math.abs(morning[i]) + afternoon[i] - d;
                extraHours += Math.max(0, diff);
            }

            System.out.println(extraHours * r);

            s = br.readLine();
        }
    }
}
