package Codeforces._1204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class ProbC {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            String tokens = br.readLine();
            for (int j = 0; j < n; j++) {
                dist[i][j] = (tokens.charAt(j) == '1') ? 1 : 10000000;
            }
            dist[i][i] = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (dist[j][k] > dist[j][i] + dist[i][k]) {
                        dist[j][k] = dist[j][i] + dist[i][k];
                    }
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        int[] p = new int[m];
        String[] tokens = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            p[i] = Integer.parseInt(tokens[i]) - 1;
        }

        LinkedList<Integer> v = new LinkedList<>();
        v.add(p[0] + 1);
        int prev = p[0];
        for (int i = 1; i < m - 1; i++) {
            if (dist[prev][p[i]] + dist[p[i]][p[i+1]] > dist[prev][p[i+1]]) {
                v.add(p[i] + 1);
                prev = p[i];
            }
        }
        v.add(p[m-1] + 1);

        System.out.println(v.size());
        StringBuilder sb = new StringBuilder();
        for (Integer integer : v) {
            sb.append(integer);
            sb.append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
