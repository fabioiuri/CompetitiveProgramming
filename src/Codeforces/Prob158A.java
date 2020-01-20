package Codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob158A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        line = br.readLine().split(" ");
        int ans = 0;
        if (!line[0].equals("0")) ans++;
        for (int i = 1; i < n; i++) {
            if ((line[i].equals(line[i - 1]) || i < k)&& !line[i].equals("0")) ans++;
            else break;
        }
        System.out.println(ans);
    }
}
