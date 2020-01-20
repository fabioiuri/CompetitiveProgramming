package Codechef.AUG19B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

// TODO - TO DOLVE (ONLY PARTIALLY SOLVED)
public class ENCODING {

    static final long MOD = 1000000000 + 7;
    static final BigInteger MOD_BIG = new BigInteger("1000000007");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] tokens = br.readLine().split(" ");
            int nLeft = Integer.parseInt(tokens[0]); // todo ?
            BigInteger left = new BigInteger(tokens[1]);

            tokens = br.readLine().split(" ");
            int nRight = Integer.parseInt(tokens[0]); // todo ?
            BigInteger right = new BigInteger(tokens[1]);

            BigInteger curr = left;
            long password = 0;
            long valueSum = 0;
            long f = f(curr);
            StringBuilder sb = new StringBuilder();
            //System.out.println("f(" + curr.toString() + ") = " + f);
            while (curr.max(right).equals(right)) {
                valueSum+= curr.longValue();
                f = f(curr);

                //System.out.println("f(" + curr.toString() + ") = " + f);
                password = (password + f) % MOD;
                sb.append(password).append(", ");
                curr = curr.add(BigInteger.ONE);
            }
            f = f(right);
            //System.out.println("f(" + right.toString() + ") = " + f);
            // 1 - 10: 55 (55)
            // 10 - 100: 4960 (5005)
            // 100 - 1000: 490645 (495550)
            // 1000 - 10000: 49010950

            // i: 138 - 1380
            //f(138) ate f(1380) = 927875
            //valueSum> 943437

            //f(1) ate f(1380( = 937177
            //valueSum> 952890
            // 1 - 10: 55 (55)
            // 1 - 100: 5005 (5050)
            // 1 - 1000: 495550 (500500)

            System.out.println(password);
            System.out.println("valueSum> " + valueSum);
            System.out.println("difference> " + (valueSum - password));

            System.out.println(sb.toString().substring(0, sb.toString().length() - 2));
        }
    }

    static long f (BigInteger x) {
        ArrayList<Sequence> sequences = new ArrayList<>();

        char[] s = x.toString().toCharArray();
        int length = s.length;
        char hand = s[0];
        Sequence currSequence = new Sequence(Character.getNumericValue(hand), 1, length - 1);
        for (int i = 1; i < length; i++) {
            if (hand == s[i]) {
                currSequence.length++;
            } else {
                sequences.add(currSequence);
                hand = s[i];
                currSequence = new Sequence(Character.getNumericValue(hand), 1, length - i - 1);
            }
        }
        // for the last
        sequences.add(currSequence);

        long sum = 0;
        for (Sequence seq : sequences) {
            sum = (sum + seq.computeValue()) % MOD;
        }
        return sum;
    }

    static class Sequence {
        int digit, length, e_idx;

        Sequence (int digit, int length, int e_idx) {
            this.digit = digit;
            this.length = length;
            this.e_idx = e_idx;
        }

        long computeValue() {
            return (long)digit * (BigInteger.TEN.modPow(new BigInteger("" + this.e_idx), MOD_BIG)).longValue();
        }
    }
}
