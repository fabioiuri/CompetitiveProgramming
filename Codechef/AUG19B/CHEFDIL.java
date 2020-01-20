package Codechef.AUG19B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Type: Simulation
 * Difficulty: easy-medium
 */
public class CHEFDIL {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String s = br.readLine();
            short[] arr = new short[s.length()];

            for (int i = 0; i < s.length(); i++) {
                arr[i] = Short.parseShort("" + s.charAt(i));
            }

            boolean clean;
            boolean done;
            do {
                done = true;
                clean = true;
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == 1) {
                        done = false;
                        clean = false;

                        arr[i] = -1;
                        if (i - 1 >= 0 && arr[i-1] != -1) { arr[i-1] = (short) ((arr[i-1] == 0) ? 1 : 0); };
                        if (i + 1 < arr.length && arr[i+1] != -1) { arr[i+1] = (short) ((arr[i+1] == 0) ? 1 : 0); };

                    } else if (arr[i] == 0) {
                        clean = false;
                    }
                }
            } while (!clean && !done);

            if (clean) {
                System.out.println("WIN");
            } else {
                System.out.println("LOSE");
            }
        }
    }
}
