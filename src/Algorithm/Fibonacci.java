package Algorithm;

public class Fibonacci {
    public static void main(String args[]){
        System.out.println(fib(0));
    }

    static int fib(int n){
        if(n==0) return 0;
        if(n<=2){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }
}
