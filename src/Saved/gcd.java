package Saved;

public class gcd {

    public static void main(String[] args) {
        System.out.println(gcd(0, 2));
        System.out.println(gcd(6, 12));
        System.out.println(gcd(1, 10));
        System.out.println(gcd(14, 8));
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
