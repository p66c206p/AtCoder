import java.util.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        System.out.println(nCk(n, k));
    }
    
    static long nCk(int n, int k) {
        BigInteger res = new BigInteger("1");
        for(int i = 0; i < k; i++) {
            res = res.multiply(BigInteger.valueOf(n-i));
            res = res.divide(BigInteger.valueOf(i+1));
        }
        
        return res.longValue();
    }
}
