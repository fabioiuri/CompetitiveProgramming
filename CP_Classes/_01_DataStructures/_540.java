package CP_Classes._01_DataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class _540 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ctd = 1;
        String line;

        while (!(line = br.readLine()).equals("0")) {
            System.out.println("Scenario #" + ctd++);

            // Read teams and team members
            int T = Integer.parseInt(line); // n. teams

            Queue<Integer> teamQueue = new Queue<>(); // maintains order of team appearances
            boolean[] teamInQueue = new boolean[T]; // saves if at least 1 member of team are enqueued
            ArrayList<Queue<Integer>> teamQueues = new ArrayList<>(); // maintains order of each team member appearances
            HashMap<Integer, Integer> playerTeams = new HashMap<>(); // maintains map from player id to team id

            for (int t = 0; t < T; t++) {
                String[] tokens = br.readLine().split(" ");
                teamQueues.add(new Queue<>()); // Create team queue
                teamInQueue[t] = false;

                for (int i = 1; i < tokens.length; i++) {
                    int playerId = Integer.parseInt(tokens[i]);
                    // Associate player id - team queue
                    playerTeams.put(playerId, t);
                }
            }

            // Read and execute instructions
            while (!(line = br.readLine()).equals("STOP")) {
                String[] tokens = line.split(" ");
                if (tokens[0].equals("ENQUEUE")) {
                    int playerId = Integer.parseInt(tokens[1]);
                    int teamId = playerTeams.get(playerId);

                    // Enqueue team in main queue if not in yet
                    if (!teamInQueue[teamId]) {
                        teamInQueue[teamId] = true;
                        teamQueue.enqueue(teamId);
                    }

                    // Enqueue player in it's team queue
                    teamQueues.get(teamId).enqueue(playerId);

                } else {
                    // Get team in head of main queue
                    int teamId = teamQueue.head();
                    Queue<Integer> tQueue = teamQueues.get(teamId);
                    System.out.println(tQueue.dequeue());

                    // If team queue is empty, remove it from main queue
                    if (tQueue.size() == 0) {
                        teamQueue.dequeue();
                        teamInQueue[teamId] = false;
                    }
                }
            }

            System.out.println(); // line break between scenarios
        }
    }

    /** Simple Queue implementation. **/
    static class Queue<T> {
        private List<T> values;

        Queue(){
            this.values = new LinkedList<>();
        }

        void enqueue(T v){
            this.values.add(v);
        }

        T dequeue(){
            return this.values.remove(0);
        }

        T head() {
            return this.values.get(0);
        }

        int size() {
            return this.values.size();
        }
    }
}
