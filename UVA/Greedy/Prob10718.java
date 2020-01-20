package UVA.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob10718 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        while (s != null) {
            String[] tokens = s.split(" ");
            long n = Long.parseLong(tokens[0]);
            long l = Long.parseLong(tokens[1]);
            long u = Long.parseLong(tokens[2]);

            long ans = 0;
            for (int i = 31; i >= 0; i--) {
                long bit = 1L << i;
                if ((n & bit) == 0) { // we can activate this bit
                    if (ans + bit <= u) { // check if does not exceed
                        ans += bit;
                    }
                } else if ((l & bit) != 0 && ans < l){
                    // only required to activate if it's necessary (due to lower bound)
                    // if ans < l and the i-th bit of l is set, then the only way for ans
                    // to become >= l, is to activate the i-th bit of ans
                    ans += bit;
                }
            }

            System.out.println(ans);

            s = br.readLine();
        }
    }
}
