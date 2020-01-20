package edx.itmo.week_01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Prob9 {
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

    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            int n = in.nextInt();
            int maxSecs = 300*60;
            int[] t = new int[n+1];
            t[0] = 0; // artificial value for correctness (10000 7999 1)
            for(int i = 1; i <= n; i++){
                t[i] = in.nextInt();
            }
            // reduce to knapsack problem
            int[][] k = new int[n+1][maxSecs+10];
            for(int s = 0; s <= maxSecs; s++){
                k[0][s] = 0;
            }
            for(int i = 1; i <= n; i++){
                for(int s = 0; s <= maxSecs; s++){
                    if(t[i] > s){
                        k[i][s] = k[i-1][s];
                    }else{
                        k[i][s] = Math.max(k[i-1][s], k[i-1][s-t[i]]+1);
                    }
                }
            }
            out.println(k[n][maxSecs]);
        }
    }
}
