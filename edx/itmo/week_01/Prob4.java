package edx.itmo.week_01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Prob4 {
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
            int[] t = new int[n];
            int[] p = new int[n];
            for(int i = 0; i < n; i++){
                p[i] = in.nextInt();
            }
            for(int i = 0; i < n; i++){
                t[i] = in.nextInt();
            }
            int max = 0;
            boolean pickedT = false;
            boolean pickedP = false;
            for(int i = 0; i < n; i++){
                if(t[i] >= p[i]){
                    pickedT = true;
                    max += t[i];
                } else {
                    pickedP = true;
                    max += p[i];
                }
            }
            if(!pickedT){
                int best = 0;
                for(int i = 1; i < n; i++){
                    if(Math.abs(t[i] - p[i]) < Math.abs(t[best] - p[best])) {
                        best = i;
                    }
                }
                max = max - p[best] + t[best];
            } else if(!pickedP) {
                int best = 0;
                for(int i = 1; i < n; i++){
                    if(Math.abs(t[i] - p[i]) < Math.abs(t[best] - p[best])) {
                        best = i;
                    }
                }
                max = max - t[best] + p[best];
            }
            out.println(max);
        }
    }
}
