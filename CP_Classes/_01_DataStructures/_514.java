package CP_Classes._01_DataStructures;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Scanner;

public class _514 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            int N = s.nextInt();
            if (N == 0) break;
            nextBlock:
            while (true) {
                ArrayDeque<Integer> incoming = new ArrayDeque<>();
                for (int i = 0; i < N; i++) {
                    incoming.addLast(i+1);
                }
                ArrayDeque<Integer> target = new ArrayDeque<>();
                for (int i = 0; i < N; i++) {
                    int read = s.nextInt();
                    if (read == 0) break nextBlock;
                    target.addLast(read);
                }
                ArrayDeque<Integer> buffer = new ArrayDeque<>();
                while (true) {
                    if (Objects.equals(buffer.peekFirst(), target.peekFirst())) {
                        buffer.removeFirst();
                        target.removeFirst();
                    } else {
                        buffer.addFirst(incoming.removeFirst());
                    }

                    if (target.isEmpty()) {
                        System.out.println("Yes");
                        break;
                    } else if (incoming.isEmpty() && !Objects.equals(buffer.peekFirst(), target.peekFirst())) {
                        System.out.println("No");
                        break;
                    }
                }
            }
            System.out.println();
        }
    }
}
