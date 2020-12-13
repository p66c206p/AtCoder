import java.util.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        BigInteger ans = nCk(n, k);
        // long ans = nCk(n, k).longValue();
        System.out.println(ans);
    }
    
    static BigInteger nCk(int n, int k) {
        BigInteger res = new BigInteger("1");
        for(int i = 0; i < k; i++) {
            res = res.multiply(BigInteger.valueOf(n-i));
            res = res.divide(BigInteger.valueOf(i+1));
            // System.out.println(res);
        }
        
        return res;
    }
}
