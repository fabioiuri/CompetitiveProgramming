package edx.itmo.week_02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Prob6 {
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
            Stack[] snowmans = new Stack[n+1];
            snowmans[0] = new Stack();
            in.nextLine();
            for (int i = 1; i <= n; i++) {
                int t = in.nextInt();
                int m = in.nextInt();
                in.nextLine();
                snowmans[i] = new Stack();
                snowmans[i].addFather(snowmans[t]);
                if(m==0){
                    snowmans[i].pop();
                }else{
                    snowmans[i].push(m);
                }
            }
            long totalMass = 0;
            for (int i = 0; i <= n; i++) {
                totalMass += snowmans[i].getMass();
            }
            out.println(totalMass);
        }
    }


    public static class Stack implements Cloneable{
        private Stack father;
        private LinkedList<Integer> values;
        private long mass;

        public Stack(){
            this.values = new LinkedList<>();
            this.mass = 0;
            this.father = null;
        }

        private void addFather(Stack father){
            this.father = father;
            this.mass += father.mass;
        }

        private void removeFather(){
            if(father == null) return;
            LinkedList<Integer> newValues = (LinkedList<Integer>) father.values.clone();
            this.values.addAll(newValues);
            father = father.father;
        }

        public void push(int v){
            this.values.addFirst(v);
            this.mass += v;
        }

        public int pop(){
            while(this.values.size() == 0){
                removeFather();
            }
            int removed = this.values.removeFirst();
            this.mass -= removed;
            return removed;
        }

        public long getMass(){
            return mass;
        }
    }
}
