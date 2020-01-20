package Miup._2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class ProbC {

    static HashMap<String, String> parentOf = new HashMap<>();
    static HashMap<String, LinkedList<String>> childrenOf = new HashMap<>();
    static HashSet<String> players = new HashSet<>();
    static HashSet<String> countedPlayerNames = new HashSet<>();

    public static void main(String[] args) throws IOException { // todo: WA
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().split(" ");
            String parentName = tokens[0];
            LinkedList<String> children = new LinkedList<>();

            for (int j = 0; j < ((tokens.length - 1) / 2); j++) {
                String child = tokens[j*2+1];
                int flag = Integer.parseInt(tokens[j*2+2]);
                children.add(child);
                parentOf.put(child, parentName);
                if (flag == 1) {
                    players.add(child);
                }
            }

            if (i == 0) {
                players.add(parentName);
            }

            childrenOf.put(parentName, children);
        }

        for (String playerName : players) {
            countPlayers(playerName);
        }

        System.out.println(countedPlayerNames.size());
    }

    static void countPlayers(String playerName) {
        if (!players.contains(playerName)) return;

        // count children

        // count father
        if (!parentOf.containsKey(playerName)) return;
        String parentName = parentOf.get(playerName);
        if (players.contains(parentName)) {
           // countedPlayerNames.add(parentName);
            countedPlayerNames.add(playerName);
        }

        // count brother
        if (childrenOf.containsKey(parentName)) {
            for (String brotherName : childrenOf.get(parentName)) {
                if (players.contains(brotherName)) {
                    //countedPlayerNames.add(brotherName);
                    countedPlayerNames.add(playerName);
                }
            }
        }

        // count grandfather
        if (!parentOf.containsKey(parentName)) return;
        String grandfatherName = parentOf.get(parentName);
        if (players.contains(grandfatherName)) {
           // countedPlayerNames.add(grandfatherName);
            countedPlayerNames.add(playerName);
        }


        // count uncle / cousin / first cousin when removed
        if (childrenOf.containsKey(grandfatherName)) {
            for (String uncleName : childrenOf.get(grandfatherName)) {
                if (players.contains(uncleName)) {
                    //countedPlayerNames.add(uncleName);
                    countedPlayerNames.add(playerName);
                }

                if (!childrenOf.containsKey(uncleName)) continue;
                LinkedList<String> cousins = childrenOf.get(uncleName);
                for (String cousinName : cousins) {
                    if (players.contains(cousinName)) {
                       // countedPlayerNames.add(cousinName);
                        countedPlayerNames.add(playerName);
                    }

                    if (!childrenOf.containsKey(cousinName)) continue;
                    LinkedList<String> firstCousinsWhenRemoved = childrenOf.get(cousinName);
                    for (String firstCousinWhenRemovedName : firstCousinsWhenRemoved) {
                        if (players.contains(firstCousinWhenRemovedName)) {
                            //countedPlayerNames.add(firstCousinWhenRemovedName);
                            countedPlayerNames.add(playerName);
                        }
                    }
                }
            }
        }
    }
}
