package edx.itmo.week_02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Prob7 {
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
            HashMap<Integer, Integer> stack = new HashMap<>();
            int minStack = 0;
            for (int i = 0; i < n; i++) {
                int v = in.nextInt();
                if(v == 0){
                    if(minStack == 0) {
                        minStack = 1;
                        stack.put(1, 1);
                    }else{
                        int currMin = minStack;
                        int oldN = stack.get(currMin);
                        stack.put(currMin, oldN - 1);
                        int oldN2 = 0;
                        if(stack.get(currMin + 1) != null){
                            oldN2 = stack.get(currMin + 1);
                        }
                        stack.put(currMin + 1, oldN2 + 1);
                        if(oldN == 1){
                            stack.remove(currMin);
                            minStack++;
                        }
                    }
                }else{
                    minStack = 1;
                    int oldN = 0;
                    if(stack.get(minStack) != null){
                        oldN = stack.get(minStack);
                    }
                    stack.put(1, oldN + 1);
                }
            }
            Set<Integer> heights = stack.keySet();
            out.println(heights.size());
            for (int height : heights) {
                int num = stack.get(height);
                out.println(height + " " + num);
            }

        }
    }
}
