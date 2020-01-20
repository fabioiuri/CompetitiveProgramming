package Codeforces._1230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProbB {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int k = Integer.parseInt(tokens[1]);
        String s = br.readLine();

        if (k == 0) {
            System.out.println(s);
        } else if (n == 1) {
            System.out.println(0);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (k <= 0) {
                    sb.append(s.charAt(i));
                } else {
                    if (i == 0 && s.charAt(i) != '1') {
                        sb.append('1');
                        k--;
                    } else if (i > 0 && s.charAt(i) != '0') {
                        sb.append('0');
                        k--;
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }
            System.out.println(sb.toString());
        }
    }
}
