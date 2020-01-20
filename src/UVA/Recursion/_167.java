package UVA.Recursion;

import java.util.Scanner;

public class _167 {

    static int[][] board;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            board = new int[8][8];
            for (int j = 0; j < 8; j++) {
                for (int m = 0; m < 8; m++) {
                    board[j][m] = sc.nextInt();
                }
            }

            boolean[][] places = new boolean[8][8];
            boolean[] rows = new boolean[8];
            System.out.format("%5.5s", solve(0, rows, places));
            System.out.println();
        }
    }

    static int solve(int c, boolean[] rows, boolean[][] places) {
        if (c == 8) return 0;

        int best = 0;
        for (int r = 0; r < 8; r++) {
            if (rows[r]) { continue; } // row occupied
            boolean diagonalOccupied = false;
            for (int backRow = r - 1, backCol = c - 1; backRow >= 0 && backCol >= 0 && !diagonalOccupied; backRow--, backCol--) {
                if (places[backRow][backCol]) diagonalOccupied = true;
            }
            for (int topRow = r + 1, backCol = c - 1; topRow < 8 && backCol >= 0 && !diagonalOccupied; topRow++, backCol--) {
                if (places[topRow][backCol]) diagonalOccupied = true;
            }
            if (diagonalOccupied) { continue; } // one of diagonals occupied
            // put a queen, call next col, and remove queen to continue search current column
            rows[r] = true;
            places[r][c] = true;
            best = Math.max(best, solve(c+1, rows, places) + board[r][c]);
            rows[r] = false;
            places[r][c] = false;
        }

        return best;
    }
}
