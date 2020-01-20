package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob71A {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while(n-- > 0) {
            String s = br.readLine();
            if(s.length() > 10) {
                s = s.charAt(0) + "" + (s.length() - 2) + s.charAt(s.length()-1);
            }
            System.out.println(s);
        }
    }
}
