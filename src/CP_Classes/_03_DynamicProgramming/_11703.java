package CP_Classes._03_DynamicProgramming;

import java.util.Scanner;

public class _11703 {
    public static void main(String[] args) {
            int[] DP = new int[1000000];
        DP[0] = 1;
        for (int i = 1; i < DP.length; i++) {
            double sin = Math.sin(i);
            int p1 = DP[(int) Math.floor(i - Math.sqrt(i))];
            int p2 = DP[(int) Math.floor(Math.log(i))];
            int p3 = DP[(int) Math.floor(i * sin * sin)];
            DP[i] = (p1 + p2 + p3) % 1000000;
        }
        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()){
            int i = s.nextInt();
            if(i==-1){
                break;
            }else{
                System.out.println(DP[i]);
            }
        }
    }
}
