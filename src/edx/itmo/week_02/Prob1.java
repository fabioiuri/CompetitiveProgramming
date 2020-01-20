package edx.itmo.week_02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Prob1 {
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
            Stack stack = new Stack();
            while(n-- > 0) {
                String[] tokens = in.nextLine().split(" ");
                if (tokens[0].equals("+")) { // push
                    int val = Integer.parseInt(tokens[1]);
                    stack.push(val);
                } else { // pop
                    out.println(stack.pop());
                }
            }
        }
    }

    public static class Stack {
        private List<Integer> values;


        public Stack(){
            this.values = new LinkedList<>();
        }

        public void push(int v){
            this.values.add(0, v);
        }

        public int pop(){
            return this.values.remove(0);
        }
    }
}
