package Codeforces._1215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProbC {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s1 = br.readLine();
        String s2 = br.readLine();

        ArrayList<Move> moves = new ArrayList<>();
        Map<Move, LinkedList<Integer>> moveMap = new HashMap<>(); // maps moves to list of indexes where moves are available
        for (int i = 0; i < n; i++) {
            int c1 = (int) s1.charAt(i);
            int c2 = (int) s2.charAt(i);
            if (c1 != c2) { // create a move that requires fix
                Move currMove = new Move(c1, c2);
                moves.add(currMove);
                LinkedList<Integer> possibleIdxs = new LinkedList<>();
                if (moveMap.containsKey(currMove)) {
                    possibleIdxs = moveMap.get(currMove);
                }
                possibleIdxs.add(i+1);
                moveMap.put(currMove, possibleIdxs);
            }
        }

        boolean possible = true;
        ArrayList<Move> fixerMoves = new ArrayList<>(); // moves that fix the strings
        for (Move move : moves) {
            LinkedList<Integer> possibleIdxs = new LinkedList<>();
            if (moveMap.containsKey(move)) {
                possibleIdxs = moveMap.get(move);
            }
            if (possibleIdxs.size() == 1) { // must try to find inverse move
                Move invMove = new Move(move.to, move.from);
                int firstIdx = possibleIdxs.remove(0);
                if (moveMap.containsKey(invMove)) {
                    LinkedList<Integer> invPossibleIdxs = moveMap.get(invMove);
                    if (invPossibleIdxs.size() > 0) {
                        int secondIdx =  invPossibleIdxs.removeFirst();
                        fixerMoves.add(new Move(secondIdx, secondIdx)); // swap what we have on incorrect place
                        fixerMoves.add(new Move(firstIdx, secondIdx)); // and swap it back to the correct position
                        moveMap.put(invMove, invPossibleIdxs);
                        moveMap.put(move, possibleIdxs);
                    } else {
                        possible = false;
                        break;
                    }
                } else {
                    possible = false;
                    break;
                }
            } else if (possibleIdxs.size() > 1) { // easy - (x,y), (x,y) => (x,x), (y,y)
                int firstIdx = possibleIdxs.removeFirst();
                int secondIdx = possibleIdxs.removeFirst();
                fixerMoves.add(new Move(firstIdx, secondIdx));
                moveMap.put(move, possibleIdxs);
            }
        }

        if (possible) {
            System.out.println(fixerMoves.size());
            for (Move fixerMove : fixerMoves) {
                System.out.println(fixerMove.from + " " + fixerMove.to);
            }
        } else {
            System.out.println("-1");
        }
    }

    static class Move {
        int from, to; // from -> what we have (1st string), to -> what we need (2nd string)

        public Move(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object  other) {
            return this.from == ((Move)other).from && this.to == ((Move)other).to;
        }

        @Override
        public int hashCode()
        {
            StringBuilder sb = new StringBuilder();
            sb.append(this.from);
            sb.append(",");
            sb.append(this.to);
            return sb.toString().hashCode();
        }
    }
}
