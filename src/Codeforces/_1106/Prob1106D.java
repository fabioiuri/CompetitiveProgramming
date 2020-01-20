package Codeforces._1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prob1106D {

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]) + 1;
        int m = Integer.parseInt(tokens[1]);
        boolean[] visited = new boolean[n];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // Read graph
        for (int i = 0; i < m; i++) {
            tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        // Start from 1st node
        int curr = 1;
        StringBuilder seq = new StringBuilder();
        visited[curr] = true;
        pq.add(curr);
        while (!pq.isEmpty()) { // While there's still a node to visit
            curr = pq.poll(); // get node with min value
            seq.append(curr).append(" ");

            // Add non-visited adjacent nodes to min priority queue
            for (Integer c : graph.get(curr)) {
                if (!visited[c]) {
                    pq.add(c);
                    visited[c] = true;
                }
            }
        }

        System.out.println(seq.toString().trim());
    }
}
