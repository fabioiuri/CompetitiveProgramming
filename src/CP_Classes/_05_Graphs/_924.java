package CP_Classes._05_Graphs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class _924 {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                int E = Integer.parseInt(br.readLine());
                LinkedList<Integer>[] adjList = new LinkedList[E];
                // Fill adjacency list
                for (int i = 0; i < E; i++) {
                    String[] tokens = br.readLine().split(" ");
                    adjList[i] = new LinkedList<>();
                    for (int j = 1; j < tokens.length; j++) {
                        adjList[i].add(Integer.parseInt(tokens[j]));
                    }
                }

                int T = Integer.parseInt(br.readLine());
                while (T-- > 0) {
                    int s = Integer.parseInt(br.readLine());
                    boolean[] visited = new boolean[E];
                    visited[s] = true;
                    LinkedList<Integer> currDayQueue = new LinkedList<>();
                    LinkedList<Integer> nextDayQueue = new LinkedList<>();
                    currDayQueue.add(s);
                    // Apply BFS starting at s
                    int maxVisits = 0;
                    int maxVisitsDay = -1;
                    int dayIndex = 0;
                    while (true) { // for each day
                        dayIndex++;

                        while (!currDayQueue.isEmpty()) {
                            int curr = currDayQueue.remove();
                            for (int i = 0; i < adjList[curr].size(); i++) {
                                int v = adjList[curr].get(i);
                                if (!visited[v]) {
                                    nextDayQueue.add(v);
                                    visited[v] = true;
                                }
                            }
                        }
                        if (nextDayQueue.size() > 0) {
                            currDayQueue.addAll(nextDayQueue);
                            nextDayQueue = new LinkedList<>();
                        } else {
                            break; // No more to explore
                        }

                        if (currDayQueue.size() > maxVisits) {
                            maxVisits = currDayQueue.size() ;
                            maxVisitsDay = dayIndex;
                        }
                    }

                    if (maxVisitsDay == -1) {
                        System.out.println(0);
                    } else {
                        System.out.println(maxVisits + " " + maxVisitsDay);
                    }
                }
            }
        } catch (Exception e) {
            // nothing
        }
    }
}
