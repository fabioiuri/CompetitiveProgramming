package Codechef.AUG19B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

// TODO: TO SOLVE (ONLY PARTIALLY SOLVED)
public class CHGORAM {

    static ArrayList<ArrayList<Integer>> tree;
    static ArrayList<StratOne> stratOneList;
    static ArrayList<StratTwo> stratTwoList;
    static boolean[] visited;
    static boolean isMaximal;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            String permutation = br.readLine().replaceAll(" ", "");

            boolean useStratOne = permutation.equals("123") || permutation.equals("321");
            isMaximal = permutation.equals("132") || permutation.equals("231");
            //System.out.println("useStratOne: " + useStratOne);
            //System.out.println("isMaximal: " + isMaximal);

            tree = new ArrayList<>();
            stratOneList = new ArrayList<>();
            stratTwoList = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                tree.add(new ArrayList<>());
            }

            String[] tokens;
            for (int i = 0; i < n - 1; i++) {
                tokens = br.readLine().split(" ");
                int x = Integer.parseInt(tokens[0]) - 1;
                int y = Integer.parseInt(tokens[1]) - 1;
                tree.get(x).add(y);
                tree.get(y).add(x);
            }

            for (int i = 0; i < n; i++) {
                if (useStratOne) stratOneList.add(new StratOne(tree.get(i).size()));
                else stratTwoList.add(new StratTwo(tree.get(i).size()));
            }

            for (int i = 0; i < n; i++) {
                bfs(i, useStratOne);
            }

            long res = 0;
            if (useStratOne) {
                for (int i = 0; i < n; i++) {
                    StratOne stratOne = stratOneList.get(i);
                    for (int j = 1; j < tree.get(i).size(); j++) {
                        for (int k = 0; k < j; k++) {
                            long combs = (long) stratOne.branchGreaterThan[j] * stratOne.branchLessThan[k];
                            combs +=  (long) stratOne.branchLessThan[j] * stratOne.branchGreaterThan[k];
                            //System.out.println(">> i=" + i + ", combs=" + combs);
                            res += combs;
                        }
                    }
                }
            } else {
                for (int i = 0; i < n; i++) {
                    StratTwo stratTwo = stratTwoList.get(i);
                    //System.out.println("i=" + i + ", tree.get(i).size()=" + tree.get(i).size());
                    for (int j = 1; j < tree.get(i).size(); j++) {
                        for (int k = 0; k < j; k++) {
                            long combs = (long) stratTwo.branchLength[j] * stratTwo.branchLength[k];
                            //System.out.println(">> i=" + i + ", combs=" + combs);
                            res += combs;
                        }
                    }
                    //long combs = (long) (stratTwo.length * (stratTwo.length - 1) / 2);
                }
            }

            System.out.println(res);
        }

    }

    static void bfs (int s, boolean stratOne) {
        LinkedList<Index> queue = new LinkedList<>();
        visited = new boolean[n];
        int originalSource = s;
        if (tree.get(originalSource).size() <= 1) {
            return;
        }

        visited[s] = true;
        queue.add(new Index(s, 0));

        while (queue.size() != 0)
        {
            Index poll = queue.poll();
            s = poll.v;
            //System.out.print(s+" ");

            Iterator<Integer> i = tree.get(s).listIterator();
            while (i.hasNext())
            {
                int v = i.next();

                if (!visited[v])
                {
                    if (originalSource > v) {
                        if (stratOne) {
                            if (poll.branchIdx != -1) {
                                stratOneList.get(originalSource).branchGreaterThan[poll.branchIdx]++;
                            }
                        } else if (isMaximal){
                            stratTwoList.get(originalSource).branchLength[poll.branchIdx]++;
                        }
                    } else {
                        if (stratOne) {
                            if (poll.branchIdx != -1) {
                                stratOneList.get(originalSource).branchLessThan[poll.branchIdx]++;
                            }
                        } else if (!isMaximal){
                            //System.out.println(">>>> increment source=" + originalSource + " due to comparison with " +  v);
                            stratTwoList.get(originalSource).branchLength[poll.branchIdx]++;
                        }
                    }

                    visited[v] = true;
                    if (originalSource == s) {
                        // still at root - assign new branch idx
                        queue.add(new Index(v, poll.branchIdx++));
                    } else {
                        // pass through branch idx
                        queue.add(new Index(v, poll.branchIdx));
                    }
                }
            }
        }
    }

    static class StratOne {
        int[] branchLessThan, branchGreaterThan;

        StratOne(int nBranches) {
            branchLessThan = new int[nBranches];
            branchGreaterThan = new int[nBranches];
        }
    }

    static class StratTwo {
        int[] branchLength;

        StratTwo(int nBranches) { branchLength = new int[nBranches]; }
    }

    static class Index {
        int v, branchIdx;

        Index(int v, int branchIdx) {
            this.v = v;
            this.branchIdx = branchIdx;
        }
    }
}
