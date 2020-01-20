package edx.itmo.week_02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Prob4 {
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
            while(n-- > 0){
                Stack stack = new Stack();
                String seq = in.nextLine();
                String ans = "YES";
                for (int i = 0; i < seq.length(); i++){
                    char c = seq.charAt(i);
                    if(c == '[' || c == '('){
                        stack.push(c);
                    }else{
                        if(stack.size() == 0) {
                            ans = "NO";
                            break;
                        }
                        char p = stack.pop();
                        if( !((c == ')' && p == '(') || (c == ']' && p == '[')) ){
                            ans = "NO";
                            break;
                        }
                    }
                }
                if(stack.size() != 0) ans = "NO";
                out.println(ans);
            }
        }
    }


    public static class Stack {
        private List<Character> values;


        public Stack(){
            this.values = new LinkedList<>();
        }

        public void push(char v){
            this.values.add(0, v);
        }

        public char pop(){
            return this.values.remove(0);
        }

        public int size(){
            return this.values.size();
        }
    }
}
