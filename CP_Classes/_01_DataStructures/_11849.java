package CP_Classes._01_DataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class _11849 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (!(line = br.readLine()).equals("0 0")) {
            String[] tokens = line.split(" ");
            int n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            int unionCount = 0;

            Set<Integer> nSet = new LinkedHashSet<>();

            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(br.readLine());
                nSet.add(v);
            }

            for (int i = 0; i < m; i++) {
                int v = Integer.parseInt(br.readLine());
                if (nSet.contains(v)) unionCount++;
            }
            System.out.println(unionCount);
        }
    }
}
