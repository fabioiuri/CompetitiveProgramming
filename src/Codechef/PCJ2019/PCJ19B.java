package Codechef.PCJ2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PCJ19B {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double A = Float.parseFloat(br.readLine());

        if (A > 90) {
            System.out.println(360 - 2*A);
        } else {
            System.out.println(2*A);
        }

    }
}
