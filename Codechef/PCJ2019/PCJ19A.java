package Codechef.PCJ2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PCJ19A {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println((s.indexOf('x') >= 0) ? "Not Safe" : "Safe");
    }
}
