package edx.itmo.week_01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Prob8 {
    static Scanner newInput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new Scanner(new File("input.txt"));
        } else {
            return new Scanner(System.in);
        }
    }
    static PrintWriter newOutput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new PrintWriter("output.txt");
        } else {
            return new PrintWriter(System.out);
        }
    }

    static int numDivisors(int n) {
        int nDivs = 0;
        for (int i=1; i<=Math.sqrt(n); i++)
        {
            if (n%i==0)
            {
                // If divisors are equal, only 1 distinct found
                if (n/i == i)
                    nDivs++;

                else // Otherwise, found 2 both
                    nDivs += 2;
            }
        }
        return nDivs;
    }

    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            int k = in.nextInt();
            int max = 0;
            int since = 1;
            int[] divisors = new int[k+1];
            for (int i = 2; i <= k; i++) {
                for (int j = i; j <= k; j += i) {
                    divisors[j]++;
                    if(divisors[j] > max){
                        max = divisors[j];
                        since = j;
                    }
                }
            }
            out.println(k-since+1);
        }
    }
}
