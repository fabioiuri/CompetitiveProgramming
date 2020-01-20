/**
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring
 * Time Complexity: O(N^2)
 */

package LeetCode.Medium;

public class Prob5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcabcba"));
        System.out.println(longestPalindrome("abcabba"));
        System.out.println(longestPalindrome("abacab"));
    }

    public static String longestPalindrome(String s) {
        int best_len = -1;
        int best_s_start = 0;
        int best_s_end = 0;
        int n = s.length();

        if (n == 0) return "";

        for (int i = 0; i < n; i++) {
            // odd length
            for (int width = 0; i + width < n && i - width >= 0; width++) {
                int len = 2 * width + 1;
                if (s.charAt(i-width) == s.charAt(i+width)) {
                    if (len > best_len) {
                        best_len = len;
                        best_s_start = i-width;
                        best_s_end = i+width;
                    }
                } else {
                    break;
                }

            }
            // even length
            for (int width = 1; i + width - 1 < n && i - width >= 0; width++) {
                int len = 2 * width;
                if (s.charAt(i-width) == s.charAt(i+width-1)) {
                    if (len > best_len) {
                        best_len = len;
                        best_s_start = i-width;
                        best_s_end = i+width-1;
                    }
                } else {
                    break;
                }
            }
        }

        return s.substring(best_s_start, best_s_end+1);
    }
}
