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
            
            // kx - ny = -s
            // -> kx ≡ -s (mod n)
            // -> x ≡ -s * k^(-1) (mod n/g)
            
            long[] eg = extgcd(k, -n);
            long g = eg[0];
            long x = eg[1];
            long y = eg[2];
            
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
            
            // ans: -s * k^(-1) (mod n)
            long ans = -s;
            if (ans < 0) ans += n;
            ans *= x;
            ans %= n;
            System.out.println(ans);
        }
    }
    
    // 拡張ユークリッド法: ax + by = gcdを解く
    // (g, x, y) = 「ax+by=c」ではなく「ax+by=g」とした時の解の1つ
    // ex. 
    // in: (a, b) = (3, -10) 「3x - 10y = -4」
    // out: (g, x, y) = (-1, 3, 1) 「3x - 10y = -1 -> (x,y)=(3,1)」
    public static long[] extgcd(long a, long b) {
        long x0 = 1, x1 = 0;
        long y0 = 0, y1 = 1;
        
        while (b != 0) {
            long q = a / b;
            long r = a % b;
            long x2 = x0 - q * x1;
            long y2 = y0 - q * y1;
        
            a = b; b = r;
            x0 = x1; x1 = x2;
            y0 = y1; y1 = y2;
        }
        
        long g = a, x = x0, y = y0;
        return new long[]{g, x, y};
    }
}
