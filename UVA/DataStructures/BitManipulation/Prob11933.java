package UVA.DataStructures.BitManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob11933 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String line = br.readLine().trim();
            int n = Integer.parseInt(line);
            if (n == 0) break;
            int a = 0;
            int b = 0;
            boolean turn = true;
            for (int i = 0; i < 32; i++) {
                int idx = 1 << i;
                if (idx > n) break;
                int bit = (n & idx) >> i;
                if (bit == 1) {
                    if (turn) a = a | (1 << i);
                    else  b = b | (1 << i);
                    turn = !turn;
                }
            }
            System.out.println(a + " " + b);
        }
    }
}
