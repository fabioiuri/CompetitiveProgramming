package UVA.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Prob10152 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine().trim());
            LinkedList<String> curr = new LinkedList<>();
            LinkedList<String> desired = new LinkedList<>();
            LinkedHashSet<String> pushes = new LinkedHashSet<>();

            for (int i = 0; i < n; i++) {
                curr.addFirst(br.readLine());
            }
            for (int i = 0; i < n; i++) {
                desired.addFirst(br.readLine());
            }

            //System.out.println(Arrays.toString(curr.toArray()));
            //System.out.println(Arrays.toString(desired.toArray()));

            while (!curr.isEmpty()) {
                //System.out.println("-> " + curr.peekFirst() + " , " + desired.peekFirst());
                String rem = curr.removeFirst();
                if (desired.peekFirst().equals(rem)) {
                    desired.removeFirst();
                } else {
                    curr.add(rem);
                    pushes.remove(rem);
                    pushes.add(rem);
                }
            }

            for (String push : pushes) {
                System.out.println(push);
            }
            System.out.println();
        }
    }
}
