public class Demo03Fibnacci {
    static long fib(long n) {
        return n <= 2 ? 1 : fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println(fib(45));
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
