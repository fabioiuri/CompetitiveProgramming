package CP_Classes._01_DataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _541 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;

        while((n = Integer.parseInt(br.readLine())) != 0) {
            boolean[][] matrix = new boolean[n][n];
            String[] tokens;

            // Read matrix
            for (int i = 0; i < n; i++) {
                tokens = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(tokens[j]) == 1;
                }
            }

            boolean[] rowParity = new boolean[n];
            boolean[] colParity = new boolean[n];
            int nCorruptedRows = 0;
            int nCorruptedCols = 0;
            // Compute row parities
            for (int i = 0; i < n; i++) {
                int nActiveBits = 0;
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j]) nActiveBits++;
                }
                if (nActiveBits % 2 == 0) rowParity[i] = true;
                else nCorruptedRows++;
            }
            // Compute col parities
            for (int i = 0; i < n; i++) {
                int nActiveBits = 0;
                for (int j = 0; j < n; j++) {
                    if (matrix[j][i]) nActiveBits++;
                }
                if (nActiveBits % 2 == 0) colParity[i] = true;
                else nCorruptedCols++;
            }

            // Compute bit-matrix corruptness
            if (nCorruptedCols == 0 && nCorruptedRows == 0) {
                // No issue found
                System.out.println("OK");
            } else if (nCorruptedCols == 1 && nCorruptedRows == 1) {
                // Changing 1 bit can only fix if a column + row are corrupted

                // Get indexes of corrupted row and col
                int corruptedCol = -1;
                int corruptedRow = -1;
                for (int i = 0; i < n; i++) {
                    if (!rowParity[i]){
                        corruptedRow = i;
                        break;
                    }
                }
                for (int i = 0; i < n; i++) {
                    if (!colParity[i]){
                        corruptedCol = i;
                        break;
                    }
                }

                // Change bit at (corruptedRow, corruptedCol) is the 0-based index solution
                System.out.println("Change bit (" + (corruptedRow + 1) + "," + (corruptedCol + 1) + ")");
            } else {
                // Impossible to fix with 1 bit change only
                System.out.println("Corrupt");
            }
        }
    }
}
