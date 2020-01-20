package Codeforces._1199;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class ProbD {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        Operation[] transactionOps = new Operation[n];

        // read initial values
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }

        // read events and save:
        // - last transaction event (type 1) for each citizen
        // - list of all payout events (type 2)
        int q = Integer.parseInt(br.readLine());
        List<Operation> payoutOperations = new LinkedList<>();
        for (int i = 0; i < q; i++) {
            String event = br.readLine();
            String[] parts = event.split(" ");

            if (parts[0].equals("1")) {
                int p = Integer.parseInt(parts[1]) - 1;
                int x = Integer.parseInt(parts[2]);

                transactionOps[p] = new Operation(x, i);
            } else {
                int x = Integer.parseInt(parts[1]);
                payoutOperations.add(new Operation(x, i));
            }
        }

        // compute max payout events from one point forward
        Operation[] maxOperations = new Operation[q];
        Operation[] payoutOperationaArr = payoutOperations.toArray(new Operation[0]);
        int pointer = payoutOperationaArr.length - 1;
        for (int i = q - 1; i >= 0; i--) {
            if (i == q - 1) {
                maxOperations[i] = new Operation(0, i); // first payout gives nothing
            } else {
                maxOperations[i] = maxOperations[i+1]; // assume biggest is the next payout
            }

            // if at the current timestamp a new payout was given
            if (pointer >= 0 && payoutOperationaArr[pointer].timestamp == i) {
                // check which has the biggest return value
                maxOperations[i] = Operation.max(maxOperations[i], payoutOperationaArr[pointer]);
                pointer--;
            }
        }

        // update transactions to have into account the payouts given: arr[i] = max(arr[i], max_payout[> i])
        for (int i = 0; i < transactionOps.length; i++) {
            if (transactionOps[i] == null) {
                transactionOps[i] = new Operation(Math.max(arr[i], maxOperations[0].x), -1);
            } else if (maxOperations.length > transactionOps[i].timestamp + 1){
                transactionOps[i].x = Math.max(transactionOps[i].x, maxOperations[transactionOps[i].timestamp+1].x);
            }
        }

        // create output
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < transactionOps.length; i++) {
            if (transactionOps[i] == null) {
                sb.append(arr[i]).append(" ");
            } else {
                sb.append(transactionOps[i].x).append(" ");
            }
        }

        System.out.println(sb.toString().trim());
    }

    public static class Operation {
        private int x;
        private int timestamp;

        Operation(int x, int timestamp) {
            this.x = x;
            this.timestamp = timestamp;
        }

        static Operation max(Operation op1, Operation op2) {
            if (op1.x >= op2.x) return op1;
            return op2;
        }
    }
}
