package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob118A {
    public static final String vowels = "aoyeui";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = ("" + s.charAt(i)).toLowerCase().charAt(0);
            if (vowels.indexOf(c) > -1) {
                continue;
            } else {
                sb.append(".");
                sb.append(c);
            }
        }
        System.out.println(sb.toString());
    }
}
