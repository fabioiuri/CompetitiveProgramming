package Codeforces._1215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProbA {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a1 = Integer.parseInt(br.readLine());
        int a2 = Integer.parseInt(br.readLine());
        int k1 = Integer.parseInt(br.readLine());
        int k2 = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int curr_a1 = a1;
        int curr_a2 = a2;
        int curr_cards = 0;
        int max = 0;
        for(int c = 0; c < n; c++) {
            curr_cards++;

            if (k1 <= k2) { // always try to 'kill' the easier ones
                if (curr_cards >= k1 && curr_a1 > 0) { // exhausted 1 player
                    max++;
                    curr_cards = 0; // reset card counter
                    curr_a1--;
                } else if (curr_cards >= k2 && curr_a2 > 0) {  // exhausted 1 player
                    max++;
                    curr_cards = 0; // reset card counter
                    curr_a2--;
                }
            } else {
                if (curr_cards >= k2 && curr_a2 > 0) {  // exhausted 1 player
                    max++;
                    curr_cards = 0; // reset card counter
                    curr_a2--;
                } else if (curr_cards >= k1 && curr_a1 > 0) {  // exhausted 1 player
                    max++;
                    curr_cards = 0; // reset card counter
                    curr_a1--;
                }
            }
        }

        curr_a1 = a1;
        curr_a2 = a2;
        curr_cards = 0;
        int min = 0;
        boolean full1 = (k1 == 1), full2 = (k2 == 1);
        for(int c = 0; c < n; c++) {
            curr_cards++;

            if (full1 && full2) { // if both full - must start to the 'killing'
                if (curr_a1 > 0) {
                    min++;
                    curr_a1--;
                } else if (curr_a2 > 0) {
                    min++;
                    curr_a2--;
                }
            }

            if (!full1) { // not full? then continue using cards
                if (curr_cards + 1 > (k1 - 1) * a1) { // next card will kill? set full to true
                    full1 = true;
                    curr_cards = 0; // now we start giving cards to the 2nd team, so we reset
                }
            } else if (!full2) {
                if (curr_cards + 1 > (k2 - 1) * a2) { // next card will kill? set full to true
                    full2 = true;
                }
            }
        }

        System.out.println(min + " " + max);
    }
}
