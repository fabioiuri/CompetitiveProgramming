package Miup._2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProbG {

    public static int kadane(int[] arr){
        int max = arr[0];
        int acum = arr[0];
        for(int i = 1; i < arr.length; i++){
            acum = Math.max(arr[i], acum + arr[i]);
            max = Math.max(acum, max);
        }
        return max;
    }

    public static void main(String... args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        String[] tokens = bf.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        // kadane
        int maxPosSum = kadane(arr);
        // get sum + invert array
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += arr[i];
            arr[i] = - arr[i];
        }
        // apply kadane in inverted array
        int maxNegSum = kadane(arr);
        // get best
        System.out.println(maxPosSum < 0 ? Math.min(maxPosSum, maxNegSum+sum) : Math.max(maxPosSum, maxNegSum+sum));
    }
}
