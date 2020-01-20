package CP_Classes._01_DataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _11743 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String number = br.readLine().replaceAll(" ", "");
            long doubledSum = 0;
            long undoubledSum = 0;
            for (int j = 0; j < number.length(); j++) {
                if (j % 2 == 0) {
                    doubledSum += sumDigits(2 * ((long) number.charAt(j) - 48));
                } else {
                    undoubledSum += ((long) number.charAt(j)  - 48);
                }
            }

            long finalSum = doubledSum + undoubledSum;
            if (finalSum % 10 == 0) System.out.println("Valid");
            else System.out.println("Invalid");
        }

    }

    static long sumDigits(long num) {
        long sum = 0;
        while (num > 0) {
            sum = sum + num % 10;
            num = num / 10;
        }
        return sum;
    }
}
