/**
 * https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/
 * 1263. Minimum Moves to Move a Box to Their Target Location
 * Time Complexity: O(N^2)
 */

package LeetCode.Hard;

import java.util.*;

public class Prob1263 {
    public static int W, H;

    public static void main(String[] args) {
        char[][] grid = {{'#','#','#','#','#','#'},
                         {'#','T','#','#','#','#'},
                         {'#','.','.','B','.','#'},
                         {'#','.','#','#','.','#'},
                         {'#','.','.','.','S','#'},
                         {'#','#','#','#','#','#'}};
        System.out.println(minPushBox(grid));
    }
    
    public static int minPushBox(char[][] grid) {
        W = grid[0].length;
        H = grid.length;

        Position targetPos = null;
        Position initialBoxPos = null;
        Position initialPlayerPos = null;

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (grid[y][x] == 'T') {
                    targetPos = new Position(x, y);
                } else if (grid[y][x] == 'B') {
                    initialBoxPos = new Position(x, y);
                } else if (grid[y][x] == 'S') {
                    initialPlayerPos = new Position(x, y);
                }
            }
        }

        if (targetPos == null || initialBoxPos == null || initialPlayerPos == null) {
            return -1;
        }

        State initialState = new State(initialBoxPos, initialPlayerPos);

        LinkedList<State> currLevelQueue = new LinkedList<>();
        currLevelQueue.add(initialState);

        LinkedList<LinkedList<State>> levelQueues = new LinkedList<>();
        levelQueues.add(currLevelQueue);

        Set<State> visited = new HashSet<>();

        // level-based bfs (each level adds +1 cost = move the box)
        int currLevel = 0;
        while (!levelQueues.isEmpty()) {
            currLevelQueue = levelQueues.removeFirst();
            while (!currLevelQueue.isEmpty()) {
                State currState = currLevelQueue.removeFirst();
                if (visited.contains(currState)) {
                    continue;
                }
                if (currState.boxPos.equals(targetPos)) {
                    return currLevel;
                }

                visited.add(currState);

                List<State> possibleStates = currState.generatePossibleStates();
                for (int i = 0; i < possibleStates.size(); i++) {
                    State possibleState = possibleStates.get(i);
                    Position boxPos = possibleState.boxPos;
                    Position playerPos = possibleState.playerPos;
                    if (grid[boxPos.y][boxPos.x] == '#') continue;
                    if (grid[playerPos.y][playerPos.x] == '#') continue;
                    if (visited.contains(possibleState)) continue;

                    if (currState.boxPos.equals(boxPos)) {
                        currLevelQueue.add(possibleState);
                    } else { // moved box - must add to next level
                        if (levelQueues.isEmpty()) { // create next level too
                            LinkedList<State> nextLevelQueue = new LinkedList<>();
                            nextLevelQueue.add(possibleState);
                            levelQueues.add(nextLevelQueue);
                        } else { // add to next level only
                            levelQueues.get(0).add(possibleState);
                        }
                    }
                }
            }

            currLevel++;
        }

        return -1;
    }

    public static class State {
        private Position boxPos;
        private Position playerPos;

        State(Position boxPosition, Position playerPosition) {
            this.boxPos = boxPosition;
            this.playerPos = playerPosition;
        }

        List<State> generatePossibleStates() {
            List<State> possibleStates = new ArrayList<>(4);

            int[] movesX = {0, 0, 1, -1};
            int[] movesY = {1, -1, 0, 0};

            for (int i = 0; i < movesX.length; i++) {
                Position newPlayerPos = new Position(this.playerPos, movesX[i], movesY[i]);
                if (newPlayerPos.inBounds()) {
                    Position newBoxPos = new Position(this.boxPos);
                    if (newPlayerPos.equals(this.boxPos)) {
                        newBoxPos.inc(movesX[i], movesY[i]);
                    }
                    if (newBoxPos.inBounds()) {
                        possibleStates.add(new State(newBoxPos, newPlayerPos));
                    }
                }
            }

            return possibleStates;
        }

        @Override
        public String toString() {
            return "[" + this.boxPos + "," + this.playerPos + "]";
        }

        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }

        @Override
        public boolean equals(Object other) {
            State otherState = (State) other;
            return this.toString().equals(otherState.toString());
        }
    }

    public static class Position {
        private int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Position(Position pos) {
            this.x = pos.x;
            this.y = pos.y;
        }

        Position(Position pos, int xInc, int yInc) {
            this(pos);
            this.inc(xInc, yInc);
        }

        void inc(int xInc, int yInc) {
            this.x += xInc;
            this.y += yInc;
        }

        boolean inBounds() {
            return (this.x >= 0 && this.x < W &&
                    this.y >= 0 && this.y < H);
        }

        @Override
        public String toString() {
            return "(" + this.x + "," + this.y + ")";
        }

        @Override
        public boolean equals(Object other) {
            Position otherPos = (Position) other;
            return this.x == otherPos.x && this.y == otherPos.y;
        }
    }
}
