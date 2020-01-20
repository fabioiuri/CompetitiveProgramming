package CP_Classes._02_RecursionBacktracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10276 {

    static int[] pegs;

    static int solve(int nBall) {
        boolean canPlace = false;
        for (int i = 0; i < pegs.length; i++) {
            if ((pegs[i] == 0) ||  // unoccupied peg
                (Math.pow(Math.round(Math.sqrt(pegs[i] + nBall)), 2) == pegs[i] + nBall)) // sum is square number
            {
                pegs[i] = nBall;
                canPlace = true;
                break;
            }
        }
        return (canPlace) ? solve(nBall + 1) : nBall - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            pegs = new int[n];
            System.out.println(solve(1));
        }

    }
}
