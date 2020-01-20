package Saved;

public class lcm {

    public static void main(String[] args) {
        System.out.println(lcm(1, 2));
        System.out.println(lcm(4, 8));
        System.out.println(lcm(4, 6));
    }

    public static int lcm(int a, int b) {
        if (a == 0 || b == 0)
            return 0;
        else {
            return Math.abs(a * b) / gcd(a, b);
        }
    }

    public static int gcd(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        } else {
            int absNumber1 = Math.abs(a);
            int absNumber2 = Math.abs(b);
            int biggerValue = Math.max(absNumber1, absNumber2);
            int smallerValue = Math.min(absNumber1, absNumber2);
            return gcd(biggerValue % smallerValue, smallerValue);
        }
    }
}
