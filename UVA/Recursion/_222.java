package UVA.Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class _222 {

    static double finalDist;
    static int n;
    static double capacity;
    static double mileCost;
    static double initCost;
    static Double[] distances;
    static Double[] gallonCosts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 1;
        while (true) {
            finalDist = Double.parseDouble(br.readLine());

            if (finalDist == -1) break;

            System.out.println("Data Set #" + T);
            String[] tokens = br.readLine().split(" ");
            capacity = Double.parseDouble(tokens[0]);
            mileCost = Double.parseDouble(tokens[1]);
            initCost = Double.parseDouble(tokens[2]);
            n = Integer.parseInt(tokens[3]);

            distances = new Double[n];
            gallonCosts = new Double[n];
            for (int i = 0; i < n; i++) {
                tokens = br.readLine().split(" ");
                distances[i] = Double.parseDouble(tokens[0]);
                gallonCosts[i] = Double.parseDouble(tokens[1]);
            }

            double ans = initCost + solve(capacity, 0);
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("minimum cost = $" + df.format(ans));

            T++;
        }
    }

    static double solve(double currGas, double currDis) {
        double minCost = Double.MAX_VALUE;
        double reachableDist = currGas * mileCost + currDis;

        if (currDis >= finalDist || reachableDist >= finalDist) {
            return 0;
        }

        for (int i = 0; i < n; i++) {
            if (currDis >= distances[i]) continue;
            if (reachableDist < distances[i]) continue;

            double newGas = capacity;
            double distTravelled = distances[i] - currDis;
            double travelGasCost =  distTravelled * (1 / mileCost);
            double afterTravelGas = currGas - travelGasCost;
            //System.out.println("afterTravelGas: " + afterTravelGas);
            //System.out.println("travelGasCost: " + travelGasCost);
            //System.out.println("currGas: " + currGas);
            if (travelGasCost > currGas) continue;
            if (afterTravelGas > 0.5 * capacity && i + 1 < n && reachableDist >= distances[i+1]) continue;


            double addedGas = capacity - afterTravelGas;
            double paidAmmount = 2 + Math.round((addedGas * gallonCosts[i])) / 100.0;
            double totalCost = paidAmmount + solve(newGas, distances[i]);

            minCost = Math.min(minCost, totalCost);
        }

        return minCost;
    }
}
