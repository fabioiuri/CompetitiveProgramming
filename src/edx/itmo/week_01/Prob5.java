package edx.itmo.week_01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Prob5 {
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
            int[][] grades = new int[3][3];
            for(int i = 0; i  < 3; i++){
                for(int j = 0; j  < 3; j++){
                    grades[i][j] = in.nextInt();
                }
            }
            double max = 0;
            for(int i = 0; i  < 3; i++){
                for(int j = 0; j  < 3; j++){
                    for(int k = 0; k  < 3; k++) {
                        if(i == j || i == k || j == k) continue;
                        max = Math.max(max,
                                Math.sqrt(Math.pow(grades[i][0], 2) + Math.pow(grades[j][1], 2) + Math.pow(grades[k][2], 2)));
                    }
                }
            }
            out.println(max);
        }
    }
}
