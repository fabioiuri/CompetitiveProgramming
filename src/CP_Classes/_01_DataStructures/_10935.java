package CP_Classes._01_DataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class _10935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;

        while((n = Integer.parseInt(br.readLine())) != 0) {
            Queue<Integer> queue = new Queue<>();
            List<Integer> discarded = new LinkedList<>();

            // Insert numbers
            for (int i = 1; i <= n; i++) {
                queue.enqueue(i);
            }

            // Repeat operation until queue only has 1 element
            while (queue.size() > 1) {
                discarded.add(queue.dequeue()); // discard top card
                int newBottom = queue.dequeue();
                queue.enqueue(newBottom); // move top card to bottom
            }

            StringBuilder sb = new StringBuilder();
            if (discarded.size() > 0) sb.append(" ").append(discarded.get(0));
            for (int i = 1; i < discarded.size(); i++) {
                sb.append(", ").append(discarded.get(i));
            }
            System.out.println("Discarded cards:" + sb.toString());
            System.out.println("Remaining card: " + queue.dequeue());
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
