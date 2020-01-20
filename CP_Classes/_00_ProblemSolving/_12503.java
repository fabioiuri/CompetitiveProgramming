package CP_Classes._00_ProblemSolving;

import java.util.Scanner;

public class _12503 {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int T = s.nextInt();

        for (int i = 0; i < T; i++) {

            int n = s.nextInt();

            int pos = 0;
            boolean[] moves = new boolean[n];

            for (int j = 0; j < n; j++) {
                boolean move;

                String line = "";

                while (line.trim().isEmpty())
                    line = s.nextLine();

                if ("LEFT".equals(line)) {
                    move = false;
                } else if ("RIGHT".equals(line)) {
                    move = true;
                } else {
                    int same = Integer.parseInt(line.split(" ")[2]);
                    move = moves[same - 1];
                }

                moves[j] = move;

                if (move) {
                    pos++;
                } else {
                    pos--;
                }
            }

            System.out.println(pos);

        }

    }
}
