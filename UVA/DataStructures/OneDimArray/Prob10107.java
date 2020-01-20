package UVA.DataStructures.OneDimArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Prob10107 {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        long[] arr = new long[10010];
        int size = 0;
        Arrays.fill(arr, Integer.MAX_VALUE);

        try {
            while (true) {
                line = br.readLine().trim();
                arr[size++] = Long.parseLong(line);
                Arrays.sort(arr);

                if (size % 2 == 0) {
                    System.out.println((long) Math.floor((arr[size/2-1] + arr[size/2]) / 2));
                } else {
                    System.out.println(arr[size/2]);
                }
            }
        } catch (Exception e) {
            // nothing
        }
    }
}
