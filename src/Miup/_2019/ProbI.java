package Miup._2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProbI {

    public static void main(String[] args) throws IOException { // todo: WA
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Shield> shields = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().split(" ");
            shields.add(new Shield(Long.parseLong(tokens[0]), Long.parseLong(tokens[1]), Long.parseLong(tokens[2])));
        }

        // compute right attr
        shields.sort(new Comparator<Shield>() {
            @Override
            public int compare(Shield shield1, Shield shield2) {
                return -Long.compare(shield1.x, shield2.x);
            }
        });

        TreeMap<Long, Shield> tmap = new TreeMap<>();
        for (Shield shield : shields) {
            Map.Entry<Long, Shield> biggerShieldEntry = tmap.higherEntry(shield.y);
            Shield biggerShield = null;
            if (biggerShieldEntry != null) {
                biggerShield = biggerShieldEntry.getValue();
            }

            if (biggerShield == null) {
                tmap.clear();
                tmap.put(shield.y, shield);
                shield.right = shield.x + shield.p;
            } else if (biggerShield.x > shield.x + shield.p) {
                tmap.put(shield.y, shield);
                shield.right = shield.x + shield.p;
            } else {
                tmap.put(shield.y, shield);
                shield.right = biggerShield.x - 1;
            }
        }

        // compute left attr
        shields.sort(new Comparator<Shield>() {
            @Override
            public int compare(Shield shield1, Shield shield2) {
                return Long.compare(shield1.x, shield2.x);
            }
        });

        tmap.clear();
        for (Shield shield : shields) {
            Map.Entry<Long, Shield> biggerShieldEntry = tmap.higherEntry(shield.y);
            Shield biggerShield = null;
            if (biggerShieldEntry != null) {
                biggerShield = biggerShieldEntry.getValue();
            }

            if (biggerShield == null) {
                tmap.clear();
                tmap.put(shield.y, shield);
                shield.left = shield.x - shield.p;
            } else if (biggerShield.x < shield.x - shield.p) {
                tmap.put(shield.y, shield);
                shield.left = shield.x - shield.p;
            } else {
                tmap.put(shield.y, shield);
                shield.left = biggerShield.x + 1;
            }
        }

        long[] xs = new long[n];
        for (int i = 0; i < shields.size(); i++) {
            xs[i] = shields.get(i).x;
        }
        //System.out.println(Arrays.toString(xs));

        shields.get(0).left = shields.get(0).x;
        for (int i = 1; i < shields.size(); i++) {
            Shield curr = shields.get(i);
            int firstProtectedXidx = lowerBound(xs, curr.left);
            //if (firstProtectedXidx >= 0 && firstProtectedXidx < n) {
                curr.left = shields.get(firstProtectedXidx).x;

            //}
        }

        shields.get(n-1).right = shields.get(n-1).x;
        for (int i = n-2; i >= 0; i--) {
            Shield curr = shields.get(i);
            int firstProtectedXidx = upperBound(xs, curr.right)-1;
            //if (firstProtectedXidx >= 0 && firstProtectedXidx < n) {
                curr.right = shields.get(firstProtectedXidx).x;

           // }
        }

        // compute left attr
        shields.sort(new Comparator<Shield>() {
            @Override
            public int compare(Shield shield1, Shield shield2) {
                return Long.compare(shield1.left, shield2.left);
            }
        });

        LinkedList<Shield> chosenShields = new LinkedList<>();
        chosenShields.add(shields.get(0));
        long protectedX = shields.get(0).right;
        for (int i = 1; i < shields.size(); i++) {
            Shield prev = chosenShields.getFirst();
            Shield curr = shields.get(i);

            if (curr.left <= prev.left && curr.right > protectedX) {
                chosenShields.removeFirst();
                chosenShields.addFirst(curr);
                protectedX = curr.right;
            } else if (protectedX < curr.x) {
                curr.left = Math.max(protectedX + 1, curr.left);
                chosenShields.addFirst(curr);
                protectedX = curr.right;

            }
        }


        //System.out.println(Arrays.toString(shields.toArray()));
        //System.out.println(Arrays.toString(chosenShields.toArray()));
        System.out.println(chosenShields.size());

    }

    static class Shield {
        long x, y, p, left, right;

        Shield(long x, long y, long p) {
            this.x = x;
            this.y = y;
            this.p = p;
        }

        @Override
        public String toString() {
            return "(x=" + x +", y=" + y + ", p=" + p + ", left=" + left +", right=" + right + ")";
        }
    }

    private static int lowerBound(long[] arr, long p){
        int low = 0; int high = arr.length;
        while(low < high) {
            int middle = ((high + low) / 2);
            if (p > arr[middle]) low = middle + 1;
            else high = middle;
        }
        return low; // lowest idx to insert w/o breaking ordering
    }

    private static int upperBound(long[] arr, long p){
        int low = 0; int high = arr.length;
        while(low < high) {
            int middle = ((high + low) / 2);
            if (arr[middle] > p) high = middle;
            else low = middle + 1;
        }
        return low; // highest idx to insert w/o breaking ordering
    }
}
