package UVA.DataStructures.BitManipulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prob594 {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while (true) {
                line = br.readLine();
                int num = Integer.parseInt(line);
                System.out.println(num + " converts to " + toBigEndian(num));
            }
        } catch (Exception e) {
            // end
        }
    }

    public static int toBigEndian(int litleEndian) {
        // Get bytes
        int first = litleEndian & 0xFF; // 11111111 11111111 11111111 [11111111]
        int second = (litleEndian >> 8) & 0xFF; // 11111111 11111111 [11111111] 11111111
        int third = (litleEndian >> 16) & 0xFF; // 11111111 [11111111] 11111111 11111111
        int fourth = (litleEndian >> 24) & 0xFF; // [11111111] 11111111 11111111 11111111
        // Put together
        int num = 0;
        num = (num | first) << 8;
        num = (num | second) << 8;
        num = (num | third) << 8;
        num = (num | fourth);
        return num;
    }
}
