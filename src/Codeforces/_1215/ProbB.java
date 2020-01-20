package Codeforces._1215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ProbB {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[] dpNegs = new int[n+1];
        int[] dpPos = new int[n+1];
        long ansNeg = 0, ansPos = 0;
        String[] tokens = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(tokens[i-1]);
            if (i == 1) {
                if (arr[i] > 0) {
                    dpPos[i] = 1;
                } else {
                    dpNegs[i] = 1;
                }
            } else {
                if (arr[i] > 0) {
                    dpPos[i] = dpPos[i - 1] + 1;
                    dpNegs[i] = dpNegs[i - 1];
                } else {
                    dpPos[i] = dpNegs[i - 1];
                    dpNegs[i] = dpPos[i - 1] + 1;
                }
            }
            ansNeg += dpNegs[i];
            ansPos += dpPos[i];
        }

        System.out.println(ansNeg + " " + ansPos);
    }
}
