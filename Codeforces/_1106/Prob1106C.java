package Codeforces._1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Prob1106C {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] tokens = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        Arrays.sort(arr);
        int i, j;
        long sum = 0;
        for (i = 0, j = arr.length - 1; i < arr.length / 2; i++, j--) {
            sum += Math.pow(arr[i] + arr[j], 2);
        }
        System.out.println(sum);
    }
}
