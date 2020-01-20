package Codeforces._1111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProbA {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        if (a.length() != b.length())
            System.out.println("No");
        else {
            boolean ans = true;
            for (int i = 0; i < a.length(); i++) {
                if ((isVowel(a.charAt(i)) && !isVowel(b.charAt(i))) ||
                    (!isVowel(a.charAt(i)) && isVowel(b.charAt(i)))) {
                   ans = false;
                   break;
                }
            }
            if (ans)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }

    private static boolean isVowel(char charAt) {
        return charAt == 'a' || charAt == 'e' || charAt == 'i' || charAt == 'o' || charAt == 'u';
    }
}
