/**
 * Iterative DFS
 */

package Saved;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class dfs {

    public static void main(String[] args) {
        LinkedList<Integer>[] adj = new LinkedList[5];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();

        adj[2].add(1);
        adj[2].add(4);
        adj[4].add(0);
        adj[0].add(1);
        adj[1].add(2);
        adj[1].add(3);
        DFS(adj, 2);
    }

    static void DFS(LinkedList<Integer>[] adj, int s)
    {
        Vector<Boolean> visited = new Vector<>(adj.length);
        for (int i = 0; i < adj.length; i++)
            visited.add(false);

        Stack<Integer> stack = new Stack<>();
        stack.push(s);

        while(!stack.empty())
        {
            s = stack.peek();
            stack.pop();

            if(!visited.get(s))
            {
                System.out.print(s + " ");
                visited.set(s, true);
            }

            Iterator<Integer> itr = adj[s].iterator();
            while (itr.hasNext())
            {
                int v = itr.next();
                if(!visited.get(v)) stack.push(v);
            }
        }
    }
}
