package edx.itmo.week_02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Prob2 {
    static Scanner newInput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new Scanner(new File("input.txt"));
        } else {
            return new Scanner(System.in);
        }
    }
    static PrintWriter newOutput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new PrintWriter("output.txt");
        } else {
            return new PrintWriter(System.out);
        }
    }

    public static void main(String[] args) throws IOException {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            int n = in.nextInt();
            in.nextLine(); // read \n
            Queue queue = new Queue();
            while(n-- > 0) {
                String[] tokens = in.nextLine().split(" ");
                if (tokens[0].equals("+")) { // push
                    int val = Integer.parseInt(tokens[1]);
                    queue.push(val);
                } else { // pop
                    out.println(queue.pop());
                }
            }
        }
    }

    public static class Queue {
        private List<Integer> values;


        public Queue(){
            this.values = new LinkedList<>();
        }

        public void push(int v){
            this.values.add(v);
        }

        public int pop(){
            return this.values.remove(0);
        }
    }
}
