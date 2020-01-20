package Saved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class link_cut_tree {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int p = Integer.parseInt(tokens[1]);

        LinkCutTree lct = new LinkCutTree(n);

        for (int i = 0; i < p; i++) {
            tokens = br.readLine().split(" ");
            int idx1 = Integer.parseInt(tokens[1]) - 1;
            int idx2 = Integer.parseInt(tokens[2]) - 1;
            if (tokens[0].equals("LINK")) {
                boolean isConnected = lct.connected(idx1, idx2);
                System.out.println((isConnected) ? "YES" : "NO");
            } else if (tokens[0].equals("ADD")) {
                lct.link(idx1, idx2);
            } else {
                lct.cut(idx1, idx2);
            }
        }
    }

    static class LinkCutTree {
        static ArrayList<Node> nodes;

        LinkCutTree(int n) {
            nodes = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                nodes.add(new Node());
            }
        }

        void link(int x, int y) {
            link(nodes.get(x), nodes.get(y));
        }

        void cut(int x, int y) {
            cut(nodes.get(x), nodes.get(y));
        }

        boolean connected(int x, int y) {
            return connected(nodes.get(x), nodes.get(y));
        }

        private void link(Node x, Node y) {
            makeRoot(x);
            x.parent = y;
        }

        private void cut(Node x, Node y) {
            makeRoot(x);
            expose(y);
            y.right.parent = null;
            y.right = null;
        }

        void connect(Node ch, Node p, Boolean isLeftChild) {
            if (ch != null)  ch.parent = p;
            if (isLeftChild != null) {
                if (isLeftChild) p.left = ch;
                else p.right = ch;
            }
        }

        void rotate(Node x) {
            Node p = x.parent;
            Node g = p.parent;
            boolean isRootP = p.isRoot();
            boolean leftChildX = (x == p.left);
            // create 3 edges: (x.r(l),p), (p,x), (x,g)
            connect(leftChildX ? x.right : x.left, p, leftChildX);
            connect(p, x, !leftChildX);
            connect(x, g, !isRootP ? p == g.left : null);
        }

        void splay(Node x) {
            while (!x.isRoot()) {
                Node p = x.parent;
                Node g = p.parent;
                if (!p.isRoot())
                    g.push();
                p.push();
                x.push();
                if (!p.isRoot())
                    rotate((x == p.left) == (p == g.left) ? p/*zig-zig*/ : x/*zig-zag*/);
                rotate(x);
            }
            x.push();
        }

        Node expose(Node x) {
            Node last = null;
            for (Node y = x; y != null; y = y.parent) {
                splay(y);
                y.left = last;
                last = y;
            }
            splay(x);
            return last;
        }

        void makeRoot(Node x) {
            expose(x);
            x.revert = !x.revert;
        }

        private boolean connected(Node x, Node y) {
            if (x == y)
                return true;
            expose(x);
            expose(y);
            return x.parent != null;
        }
    }

    static class Node {
        Node left;
        Node right;
        Node parent;
        boolean revert;

        // tests whether x is a root of a splay tree
        boolean isRoot() {
            return parent == null || (parent.left != this && parent.right != this);
        }

        void push() {
            if (revert) {
                revert = false;
                Node t = left;
                left = right;
                right = t;
                if (left != null)   left.revert = !left.revert;
                if (right != null)  right.revert = !right.revert;
            }
        }
    }
}
