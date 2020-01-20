package edx.itmo.week_02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Prob8 {
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
            in.nextLine();
            Stack stack = new Stack();
            for (int i = 0; i < n; i++) {
                String[] tokens = in.nextLine().split(" ");
                if(tokens[0].equals("add")){
                    stack.push(Integer.parseInt(tokens[1]));
                }else if(tokens[0].equals("take")){
                    stack.pop();
                }else{
                    stack.mum();
                }
            }
            int sz = stack.values.size();
            out.println(sz);
            String line = "";
            int i = stack.start;
            int ctd = sz;
            Integer[] values = stack.values.toArray(new Integer[sz]);
            while(ctd-- > 0){
                line = Integer.toString(values[i]) + " " + line;
                i = (i + 1) % sz;
            }
            out.println(line);
        }
    }


    public static class Stack {
        private LinkedList<Integer> values;
        private int start;

        public Stack(){
            this.start = 0;
            this.values = new LinkedList<>();
        }

        public void push(int v){
            this.values.add(start, v);
        }

        public int pop(){
            return this.values.remove(start);
        }

        public void mum(){
            if(values.size() == 0) return;
            int size = this.values.size();
            this.start = (this.start  + (int) Math.ceil(size / 2.0)) % values.size();
        }
    }
}
