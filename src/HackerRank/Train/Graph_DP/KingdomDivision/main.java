package HackerRank.Train.Graph_DP.KingdomDivision;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class main {

    static final int MOD = 1000000007;

    static ArrayList<LinkedList<Integer>> adjList;
    static boolean[] visited;
    static int n;
    static long[][] nearly, good; // [node][color] - color: 0 -> black; 1 -> red

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i);
        }
        return ret;
    }

    static long fixParam(long param) { // fix negative diffs
        if (param >= 0) return param;
        while (param < 0) {
            param += MOD;
        }
        return param;
    }

    /**
     * 2 Phases:
     *      1. Explore: populate directed graph and priority array (last = higher priority);
     *      2. Expand: start from node with most priority and compute combinations based
     *          on previous expanded nodes.
     * @param start Node to start the explore process.
     */
    static void dfs(int start) {
        // Directed graph data structure
        ArrayList<ArrayList<Integer>> directedChildren = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            directedChildren.add(new ArrayList<>());
        }
        // Auxiliary lists
        ArrayList<Integer> toExplore, toExpand, exploringNodes;
        (toExpand = new ArrayList<>()).add(start);
        (exploringNodes = new ArrayList<>()).add(start);

        // 1. Explore phase
        visited[start] = true;
        while (exploringNodes.size() > 0) {
            toExplore = new ArrayList<>(); // Reset toExplore list
            for (Integer exploreNode : exploringNodes) {
                for (Integer c : adjList.get(exploreNode)) {
                    if (!visited[c]) {
                        visited[c] = true;
                        toExplore.add(c); // So we know what to explore next
                        toExpand.add(c); // Added new priority node to expand
                        directedChildren.get(exploreNode).add(c);
                    }
                }
            }
            exploringNodes = toExplore; // Let's explore newly found nodes next
        }

        // 2. Expand phase
        int[] expandingNodesArr = convertIntegers(toExpand);
        // System.out.println(Arrays.toString(expandingNodesArr));
        for (int i = expandingNodesArr.length - 1; i >= 0; i--) {
            int node = expandingNodesArr[i];
            // System.out.println("--> " + node);
            good[node][0] = good[node][1] = 1;
            nearly[node][0] = nearly[node][1] = 1;
            for (Integer c : directedChildren.get(node)) {
                // good combinations
                good[node][0] = (good[node][0] * (good[c][0] + good[c][1] + nearly[c][0]) % MOD) % MOD;
                good[node][1] = (good[node][1] * (good[c][0] + good[c][1] + nearly[c][1]) % MOD) % MOD;
                // nearly good combinations
                nearly[node][0] = (nearly[node][0] * good[c][1]) % MOD; // my parent black, me black, my children all red
                nearly[node][1] = (nearly[node][1] * good[c][0]) % MOD; // my parent red, me red, my children all black
            }
            good[node][0] = fixParam(good[node][0] - nearly[node][0]);
            good[node][1] = fixParam(good[node][1] - nearly[node][1]);
            // System.out.println("---> " + node + " X = " + ((good[node][0] + good[node][1]) % MOD));
        }
    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adjList = new ArrayList<>(n);
        visited = new boolean[n];
        nearly = new long[n][2];
        good = new long[n][2];
        // Initialize adj list array with empty lists
        for(int i = 0; i < n ; i++){
            adjList.add(new LinkedList<>());
        }
        // Read vals and build adj list
        for (int i = 0; i < n-1; i++) {
            String[] tokens = br.readLine().split(" ");
            int node1 = Integer.parseInt(tokens[0]) - 1;
            int node2 = Integer.parseInt(tokens[1]) - 1;
            adjList.get(node1).add(node2);
            adjList.get(node2).add(node1);
        }
        dfs(0);
        System.out.println((good[0][0] + good[0][1]) % MOD);
    }
}
