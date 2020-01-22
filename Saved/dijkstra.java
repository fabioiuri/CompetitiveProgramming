package Saved;

import java.util.*;

public class dijkstra {

    public static void main(String arg[]) {
        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges
        List<List<Node> > adj = new ArrayList<List<Node> >();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the DPQ graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        // Calculate the single source shortest path
        int dist[] = dijkstra(adj, V, source);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dist[i]);
    }

    static int[] dijkstra(List<List<Node>> adj, int V, int src)
    {
        int dist[] = new int[V];
        PriorityQueue<Node> pq = new PriorityQueue<>(V, new Node());
        Set<Integer> settled = new HashSet<>();

        for (int i = 0; i < V; i++) dist[i] = Integer.MAX_VALUE;

        pq.add(new Node(src, 0));
        dist[src] = 0;

        while (settled.size() != V) {
            int u = pq.remove().node;  // remove the minimum distance node

            settled.add(u);
            int edgeDistance = -1;
            int newDistance = -1;

            // All the neighbors of v
            for (int i = 0; i < adj.get(u).size(); i++) {
                Node v = adj.get(u).get(i);
                if (!settled.contains(v.node)) {
                    edgeDistance = v.cost;
                    newDistance = dist[u] + edgeDistance;

                    // If new distance is cheaper in cost
                    if (newDistance < dist[v.node])
                        dist[v.node] = newDistance;

                    pq.add(new Node(v.node, dist[v.node]));
                }
            }
        }

        return dist;
    }

    static class Node implements Comparator<Node> {
        public int node, cost;

        public Node() { }

        public Node(int node, int cost)
        {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2)
        {
            if (node1.cost < node2.cost) return -1;
            if (node1.cost > node2.cost) return 1;
            return 0;
        }
    }
}
