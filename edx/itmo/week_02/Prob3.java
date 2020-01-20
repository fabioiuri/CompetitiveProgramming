package edx.itmo.week_02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Prob3 {
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

    public static void main(String[] args) {
        try (Scanner in = newInput(); PrintWriter out = newOutput()) {
            int n = in.nextInt();
            in.nextLine(); // read \n
            Queue queue = new Queue();
            while(n-- > 0) {
                String[] tokens = in.nextLine().split(" ");
                switch (tokens[0]) {
                    case "+":  // push
                        int val = Integer.parseInt(tokens[1]);
                        queue.push(val);
                        break;
                    case "-":  // pop
                        queue.pop();
                        break;
                    default:  // query minimum
                        out.println(queue.min());
                        break;
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static class Queue {
        private LinkedList<Integer> values;
        private AVLTree bt; // to maintain sorted items so that we can query

        public Queue(){
            this.values = new LinkedList<>();
            this.bt = new AVLTree();
        }

        public void push(int v){
            this.values.add(v);
            this.bt.insert(v);
        }

        public int pop() {
            int removed = this.values.removeFirst();
            this.bt.remove(removed);
            return removed;
        }

        public int min() {
            return bt.min();
        }
    }

    // AVL tree with repeated value compression (w/ counter for repeated vals)
    public static class AVLTree {
        public AVLNode root;

        public AVLTree(){
            root = null;
        }

        public void insert(int value){
            root = insert(root, value);
        }

        public void remove(int value){
            root = remove(root, value);
        }

        public int min(){
            if(root == null) return Integer.MIN_VALUE; // wrong call pleb
            return root.min().key;
        }

        private AVLNode insert(AVLNode node, int key){
            // Normal BST insert
            if(node == null) return (new AVLNode(key));
            if(node.key < key){
                node.right = insert(node.right, key);
            }else if(node.key > key){
                node.left = insert(node.left, key);
            }else{
                node.count++;
                return node;
            }

            // Update height of ancestor
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

            // Get getBalance of parent and check 4 possible cases
            int balance = getBalance(node);

            if(balance < -1 && key > node.right.key){
                // right right case
                return rotateLeft(node);
            }

            if(balance > 1 && key < node.left.key){
                // left left case
                return rotateRight(node);
            }

            if(balance < -1 && key < node.right.key){
                // right left case
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            if(balance > 1 && key > node.left.key){
                // left right case
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            return node;
        }

        private AVLNode remove(AVLNode node, int key)
        {
            // Normal BST delete
            if (node == null)  return null;
            if (key < node.key)
                node.left = remove(node.left, key);
            else if (key > node.key)
                node.right = remove(node.right, key);
            else
            {
                node.count--;
                if(node.count == 0) {
                    if(node.left == null || node.right == null) {
                        // node with only one child or no child
                        AVLNode temp = null;
                        if(temp == node.left) temp = node.right;
                        else temp = node.left;

                        if(temp == null) node = null; // no child case
                        else node = temp; // one child case
                    } else {
                        // node with two children: Get the inorder successor (smallest
                        // in the right subtree)
                        AVLNode minNode = node.right.min();
                        node.key = minNode.key;
                        node.count = minNode.count;
                        // Delete the inorder successor
                        node.right = remove(node.right, node.key);
                    }
                } else { // node not removed yet - no change on heights is made
                    return node;
                }
            }
            if(node == null) return null;
            // Update height
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            // Get getBalance and check the 4 cases
            int balance = getBalance(node);
            if(balance > 1 && getBalance(node.left) >= 0){  // left left case
                return rotateRight(node);
            }
            if(balance < -1 && getBalance(node.right) <= 0){ // right right case
                return rotateLeft(node);
            }
            if(balance > 1 && getBalance(node.left) < 0){  // left right case
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if(balance < -1 && getBalance(node.right) > 0){ // right left case
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
            return node;
        }

        private int getHeight(AVLNode node){
            return (node == null) ? 0 : node.height;
        }

        private int getBalance(AVLNode node){
            return getHeight(node.left) - getHeight(node.right);
        }

        private AVLNode rotateLeft(AVLNode parent){
            AVLNode child = parent.right;
            AVLNode smallerFromChild = child.left;
            // Rotate
            child.left = parent;
            parent.right = smallerFromChild;
            // Update Heights
            parent.height = Math.max(getHeight(parent.left), getHeight(parent.right)) + 1;
            child.height = Math.max(getHeight(child.left), getHeight(child.right)) + 1;
            return child;
        }

        private AVLNode rotateRight(AVLNode parent){
            AVLNode child = parent.left;
            AVLNode biggerFromChild = child.right;
            // Rotate
            child.right = parent;
            parent.left = biggerFromChild;
            // Update Heights
            parent.height = Math.max(getHeight(parent.left), getHeight(parent.right)) + 1;
            child.height = Math.max(getHeight(child.left), getHeight(child.right)) + 1;
            return child;
        }
    }

    public static class AVLNode{
        private int key, count, height;
        private AVLNode left, right;

        public AVLNode(int value){
            this.key = value;
            this.count = 1;
            this.height = 1;
        }

        public AVLNode min(){
            if(left != null) return left.min();
            return this;
        }
    }
}
