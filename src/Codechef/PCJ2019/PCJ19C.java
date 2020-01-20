package Codechef.PCJ2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PCJ19C {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<Integer, Integer> map = new HashMap<>();
        String[] tokens = br.readLine().split(" ");
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(tokens[i]);
            if (!map.containsKey(v)) {
                if (v % 2 == 0 && v % 3 == 0 && v % 5 != 0) {
                    map.put(v, 1);
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}
