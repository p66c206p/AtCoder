import java.util.*;
import java.math.BigDecimal;

public class Main {
    static int geta = 10000;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        long x = getaLong(sc.next(), geta);
        long y = getaLong(sc.next(), geta);
        long r = getaLong(sc.next(), geta);
        // System.out.println(x);
        // System.out.println(y);
        // System.out.println(r);
        
        x += 10000000000L;
        y += 10000000000L;
        
        long left = (x-r+geta-1)/geta*geta;
        long right = (x+r)/geta*geta;
        
        long count = 0;
        for(long i = left; i <= right; i += geta) {
            long tmp = r*r - (x-i)*(x-i);
            long p = longSqrt(tmp);
            
            long bottom = (y-p+geta-1)/geta*geta;
            long top = (y+p)/geta*geta;
            // System.out.println(bottom + " " + top);
            
            count += (top-bottom)/geta + 1;
        }
        System.out.println(count);
    }
    
    // 小数strをgeta倍して整数longで返す
    // ex. (314.15, 10000) -> 31415000
    // (10.00, 10000) -> 100000
    public static long getaLong(String str, int geta_) {
        BigDecimal num = new BigDecimal(str);
        BigDecimal geta = new BigDecimal(geta_);
        
        return num.multiply(geta).longValue();
    }
    
    // √2 -> 1, √3 -> 1, √4 -> 2
    public static long longSqrt(long n) {
        long x = n;
        long y = (n+1) / 2;
        while (y < x) {
            x = y;
            y = (y + n/y) / 2;
        }
        return x;
    }
}
