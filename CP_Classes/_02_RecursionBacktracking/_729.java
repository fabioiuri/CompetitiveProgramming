package CP_Classes._02_RecursionBacktracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class _729 {

    static void generateBitStrings(int length, int nOnes, String str) {
        if (length == nOnes) {
            System.out.println(str + String.join("", Collections.nCopies(length, "1")));
            return;
        } else if (length == 0) {
            System.out.println(str);
            return;
        }

        generateBitStrings(length - 1, nOnes, str + "0"); // inactive bit
        if (nOnes > 0) generateBitStrings(length - 1, nOnes - 1, str + "1"); // active bit
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            br.readLine(); // skip empty line
            String[] tokens = br.readLine().split(" ");
            int n = Integer.parseInt(tokens[0]);
            int h = Integer.parseInt(tokens[1]);
            generateBitStrings(n, h, "");

            if (t > 0) System.out.println(); // empty line between testcases
        }
    }
}
