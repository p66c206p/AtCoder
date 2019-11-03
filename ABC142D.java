import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        
        long gcd = gcd(a, b);
        
        System.out.println(prime(gcd));
    }
    
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        
        return gcd(b, a % b);
    }
    
    public static long prime(long n) {
        // nの素因数の個数(1を含む)
        int count = 1;
        
        long d = n;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (d % i == 0) {
                count++;
                while (d % i == 0) {
                    d = d / i;
                }
            }
        }
        
        if (d != 1) {
            count++;
        }
        
        return count;
    }
}
