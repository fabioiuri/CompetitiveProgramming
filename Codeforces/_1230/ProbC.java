package Codeforces._1230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class ProbC {

    static int n;
    static ArrayList<LinkedList<Integer>> adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        adjList = new ArrayList<>(n);
        int[] vertexPiece = new int[n];

        for (int i = 0; i < n; i++) {
            adjList.add(new LinkedList<>());
            vertexPiece[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]) - 1;
            int b = Integer.parseInt(line[1]) - 1;
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        if (m == 0) {
            System.out.println(0);
        } else if (n <= 6) {
            System.out.println(m);
        } else {
            System.out.println(maximize(vertexPiece, 0));
        }
    }

    static int maximize(int[] vertexPiece, int vertex) {
        if (vertex == n) { // count and return
            boolean[][] dominos = new boolean[6][6];
            for (int i = 0; i < n; i++) {
                for (Integer j : adjList.get(i)) {
                        dominos[vertexPiece[i]][vertexPiece[j]] = true;
                }
            }

            int count = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j <= i; j++) {
                    if (dominos[i][j]) count++;
                }
            }
            return count;

        }

        // try and expand
        int max = -1;
        for (int j = 0; j < 6; j++) {
            vertexPiece[vertex] = j;
            max = Math.max(max, maximize(vertexPiece, vertex + 1));
        }
        return max;
    }
}

