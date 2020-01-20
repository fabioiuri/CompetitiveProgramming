/**
 * 647. Palindromic Substrings
 * https://leetcode.com/problems/palindromic-substrings/
 * Time Complexity: O(N^2)
 */

package LeetCode.Medium;

public class Prob647 {

    public static void main(String[] args) {
        System.out.println(countSubstrings("abcabcba"));
        System.out.println(countSubstrings("abcabba"));
    }

    public static int countSubstrings(String s) {
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // odd length
            for (int width = 0; i + width < n && i - width >= 0; width++) {
                if (s.charAt(i-width) == s.charAt(i+width)) {
                    count++;
                } else {
                    break;
                }
            }
            // even length
            for (int width = 1; i + width - 1 < n && i - width >= 0; width++) {
                if (s.charAt(i-width) == s.charAt(i+width-1)) {
                    count++;
                } else {
                    break;
                }
            }
        }

        return count;
    }
}
