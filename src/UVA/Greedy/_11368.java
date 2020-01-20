/**
 * @problem https://uva.onlinejudge.org/external/113/11368.pdf
 * @author Fabio Colaco (fabioiuri@live.com)
 */
package UVA.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _11368 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            String[] tokens = br.readLine().split(" ");
            ArrayList<Pair> points = new ArrayList<>(n);
            for (int i = 0; i < 2*n; i+=2) {
                int w = Integer.parseInt(tokens[i]);
                int h = Integer.parseInt(tokens[i+1]);
                points.add(new Pair(w, h));
            }

            points.sort(new Comparator<Pair>() {
                @Override
                public int compare(Pair t0, Pair t1) {
                    if (t1.w == t0.w) {
                        return t0.h - t1.h;
                    }
                    return t1.w - t0.w;
                }
            });

            // Idea: after sorting the points by width (desc) and by height (asc),
            // create empty height list, and then populate it:
            // - If there is no height larger than the current, add a new element at
            //   the end of this list, with this new height.
            // - If there exists a height larger than the current, find it, and set it
            //   to the current height (simulating a height replace).
            //
            // Note: since the widths are all sorted, we only need to take care of
            // handling the heights. On width ties, we sort by height (asc) so that we
            // force adding a new height to the heightsTaken list when widths are equal,
            // allowing us to not commit the error of replacing a larger height with a
            // smaller one when we can't merge 2 dolls (equal widths cannot be merged).

            ArrayList<Integer> heightsTaken = new ArrayList<>(n);
            for (Pair point : points) {
                int idx = upperBound(heightsTaken, point.h);
                // Must be upper bound because in case of tie (same height),
                // idx must be == heightsTaken.size() so that we insert a new
                // group. if we try lower_bound, same heights might get
                // idx = heightsTaken.size() - 1 and then a set(idx, h) is made
                // which is wrong.
                if (idx == heightsTaken.size()) {
                    heightsTaken.add(point.h);
                } else {
                    heightsTaken.set(idx, point.h);
                }
            }

            System.out.println(heightsTaken.size());
        }
    }

    static class Pair {
        int w, h;

        public Pair(int w, int h) {
            this.w = w;
            this.h = h;
        }
    }

    private static int upperBound(ArrayList<Integer> arr, int p){
        int low = 0; int high = arr.size();
        while(low < high) {
            int middle = ((high + low) / 2);
            if (arr.get(middle) > p) high = middle;
            else low = middle + 1;
        }
        return low; // highest idx to insert w/o breaking ordering
    }
}
