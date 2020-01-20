package CP_Classes._02_RecursionBacktracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10344 {

    static int[] nums = new int[5];
    static boolean[] available;

    static boolean solve(int opNumber, int value) {
        if (opNumber == 5) return value == 23;

        for (int i = 0; i < available.length; i++) {
            if (available[i]) {
                // Try to use it
                available[i] = false;
                int add = value + nums[i];
                int sub = value - nums[i];
                int mul = value * nums[i];
                boolean possible =
                        solve(opNumber + 1, add) ||
                        solve(opNumber + 1, sub) ||
                        solve(opNumber + 1, mul);
                if (possible) {
                    return true; // Valid path discovered!
                } else {
                    // If didn't work, undo and continue exploring paths
                    available[i] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String line = br.readLine();
            if (line.equals("0 0 0 0 0")) break;

            String[] tokens = line.split(" ");
            for (int i = 0; i < tokens.length; i++) {
                nums[i] = Integer.parseInt(tokens[i]);
            }

            available = new boolean[]{true, true, true, true, true};
            boolean possible = false;
            for (int i = 0; i < nums.length && !possible; i++) {
                available[i] = false;
                boolean solve = solve(1, nums[i]);
                available[i] = true;
                possible = solve;
            }

            if (possible) System.out.println("Possible");
            else System.out.println("Impossible");
        }
    }
}
