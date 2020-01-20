package Miup._2018;

import java.io.*;

public class ProbB {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        SuffixTrie st = new SuffixTrie();
        st.insert(s);
        while((s = br.readLine())!=null && s.length() > 0){
            st.insert(s);
        }
        // System.out.println(st); // for debugging
        System.out.println(st.lcs());
    }

    public static class SuffixTrie {
        public SuffixTrieNode node;

        public SuffixTrie(){
            this.node = new SuffixTrieNode();
        }

        public void insert(String s){
            int nStrings = node.count + 1;
            for(int i = 0; i < s.length(); i++) {
                node.insert(s.substring(i), nStrings);
            }
        }

        public int lcs(){
            return node.lcs(node.count, 0);
        }

        @Override
        public String toString(){  // for debugging
            return node.toString();
        }
    }

    public static class SuffixTrieNode{
        public int count; // number of different strings that passed through
        public SuffixTrieNode[] nodes = new SuffixTrieNode[26];

        public SuffixTrieNode(){ this.count = 0; }

        public void insert(String s, int nString){
            if(nString > count + 1) {
                // 1 of the previous strings did not reach
                // this node - for sure it wont be answer for LCS
                return;
            }else if(nString == count + 1) {
                count++;
            }
            if(s.length() == 0) return;
            char c = s.charAt(0);
            int idx = c-'a';
            if(nodes[idx] == null) {
                nodes[idx] = new SuffixTrieNode();
            }
            nodes[idx].insert(s.substring(1), nString);
        }

        public int lcs(int nStrings, int deepLvl){
            int lcs = deepLvl;
            if(this.count != nStrings) {
                // if nStrings didnt go trought here, then
                // surely didnt go through the child nodes
                return 0;
            }
            for (int i = 0; i < nodes.length; i++) {
                if(nodes[i] != null) {
                    lcs = Math.max(lcs, nodes[i].lcs(nStrings, deepLvl + 1));
                }
            }
            return lcs;
        }

        @Override
        public String toString(){  // for debugging
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < nodes.length; i++){
                if(nodes[i] != null){
                    sb.append("\t--[" + (char) ('a'+i) + "](" + nodes[i].count + ")\n");
                    String[] split =  nodes[i].toString().split("\n");
                    for(String s: split) {
                        sb.append("\t" + s + "\n");
                    }
                }
            }
            return sb.toString();
        }
    }
}
