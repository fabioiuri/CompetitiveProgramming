package UVA.DataStructures.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class _380 {

    static HashMap<Integer, LinkedList<Forward>> graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        System.out.println("CALL FORWARDING OUTPUT");
        for (int t = 1; t <= T; t++) {
            System.out.println("SYSTEM " + t);
            graph = new HashMap<>();

            // Register forwards
            int next = sc.nextInt();
            while(next != 0) {
                int source = next;
                int start = sc.nextInt();
                int end = start + sc.nextInt();
                int target = sc.nextInt();

                LinkedList<Forward> forwardList = new LinkedList<>();
                if (graph.containsKey(source)) {
                    forwardList = graph.get(source);
                }
                forwardList.add(new Forward(start, end, target));
                graph.put(source, forwardList);

                next = sc .nextInt();
            }

            // Answer to call forwarding
            next = sc.nextInt();
            while (next != 9000) {
                int time = next;
                int target = sc.nextInt();

                int rings = answerCall(time, target);
                System.out.print("AT ");
                System.out.format("%04d", time);
                System.out.print(" CALL TO ");
                System.out.format("%04d", target);
                System.out.print(" RINGS ");
                System.out.format("%04d", rings);
                System.out.println();

                next = sc.nextInt();
            }
        }
        System.out.println("END OF OUTPUT");
    }

    static int answerCall(int time, int target) {
        if (graph.containsKey(target)) {
            HashMap<Integer, Boolean> hitList = new HashMap<>();

            // follow forwarding line until: a repeated hit entry is reached or until an open line is found
            int currTarget = target;
            while (true) {
                if (hitList.containsKey(currTarget) && hitList.get(currTarget)) {
                    return 9999; // repeated hit entry... infinite loop (aka. no1 is available)
                }

                hitList.put(currTarget, true);

                boolean foundForward = false;
                LinkedList<Forward> forwards = graph.get(currTarget);
                if (forwards != null) {
                    for (Forward forward : forwards) {
                        if (forward.start <= time && forward.end >= time) {
                            currTarget = forward.target;
                            foundForward = true;
                            break;
                        }
                    }
                }

                if (!foundForward) {
                    return currTarget; // open line found
                }
            }

        }

        return target;
    }

    static class Forward {
        int start, end, target;

        public Forward(int start, int end, int target) {
            this.start = start;
            this.end = end;
            this.target = target;
        }
    }
}
