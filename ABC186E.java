import java.util.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        
        // ans:
        // 円上に席0～席(n-1)があり、席sに今座っている。
        // kずつ席を移動する時、席0に初めて座るのはいつか？
        
        // ex. (s=4, k=3, n=10, y=何周したか)
        // 4 + 3x = 10y
        // -> 「3x - 10y = -4」のxが分かればOK
        
        // 4 + 3x ≡ 0 (mod 10) (nの倍数ならゴール)
        // -> 「x ≡ -4 * 3^(-1) (mod 10)」のxが分かればOK
        
        int t = sc.nextInt();
        while (t-- > 0) {
            long n = sc.nextInt();
            long s = sc.nextInt();
            long k = sc.nextInt();
            
            // s + k*ans ≡ 0 (mod n)
            
            // (mod n)上でkで割りたいので、kとnを互いに素にする
            // gcd(n, k): 移動可能な席のmod
            long g = gcd(n, k);
            
            // 席sが(mod n/g)上に存在しない席なら到達不能
            if (s % g != 0) {
                System.out.println(-1);
                continue;
            }
            
            // 席数を(mod n) -> (mod n/g)に変換
            if (g != 1) {
                n /= g;
                s /= g;
                k /= g;
            }
            
            // k_inv = k^(-1) (mod n) ※gcd(k, n)=1でないとエラーを吐く
            long k_inv = BigInteger.valueOf(k).modInverse(BigInteger.valueOf(n)).longValue();
            
            // ans: -s * k^(-1) (mod n)
            long ans = -s;
            if (ans < 0) ans += n;
            ans *= k_inv;
            ans %= n;
            System.out.println(ans);
        }
    }
    
    public static long lcm(long m, long n) {
        return m * (n / gcd(m, n));
    }
    
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        
        return gcd(b, a % b);
    }
}
