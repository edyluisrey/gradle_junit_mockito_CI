package ejemplo;

public class Fibonacci {
    public int fibonacci(int n) {
        int a = 1;
        int b = 1;
        int c;

        for (; n > 2; --n) {
            c = a;
            a = b;
            b = b + c;
        }
        return b;
    }
}