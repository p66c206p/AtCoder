import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        
        // ans:
        // 円上にn個の席がある。席sに今座っている。
        // 何回「席をk個移る」をしたら、席0につくか？
        
        // ex. (s=4, k=3, n=10, y=何周したか)
        // 4 + 3x = 10y
        // -> 「3x + 10y = -4」のxが分かればOK
        // (今は席4で、席を3つずつ移る時x回移動(延べy周)したら席0に行ける)
        
        // 4 + 3x ≡ 0 (mod 10) (nの倍数ならゴール)
        // -> 「x ≡ -4 * 3^(-1) (mod 10)」のxが分かればOK
        
        int t = sc.nextInt();
        while (t-- > 0) {
            long n = sc.nextInt();
            long s = sc.nextInt();
            long k = sc.nextInt();
            
            // ans: 「kx - ny = -s」なるx
            
            // まずgcd(k, -n)を求めて、「kx - ny = gcd(k, -n)」にする
            long g = gcd(k, -n);
            if (s % g != 0) {
                // 割り切れない = 右辺を1にできない = 解なし
                System.out.println(-1);
                continue;
            }
            n /= g;
            s /= g;
            k /= g; // -> k'x - n'y = -s' (以降これをkx - ny = -sとする)
            
            // 拡張ユークリッドの互除法:
            // 「kx - ny = 1」の整数解を1つ求める
            // (gcdを取ったのでk, -nは互いに素。)
            // (ax + by = gcd(a,b)は整数解が必ず存在する)
            long[] eg = extgcd(k, -n);
            g = eg[0];
            long x_ = eg[1];
            long y_ = eg[2];
            
            // kx - ny = -s
            // kx' - ny' = 1
            // -> kx ≡ -skx' (mod n)
            // -> x ≡ -sx' (mod n)
            
            long ans = -s;
            if (ans < 0) ans += n;
            ans *= x_;
            ans %= n;
            System.out.println(ans);
        }
    }
    
    // 拡張ユークリッド法: ax + by = gcd(a, b)を解く
    // (g, x, y) = 「ax+by=c」ではなく「ax+by=gcd」とした時の解の1つ
    // ex. 
    // in: (a, b) = (3, 10) 「3x + 10y = -4」
    // out: (g, x, y) = (1, -3, 1) 「3*(-3) + 10*1 = 1(=gcd(3,10))」
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
    
    public static long lcm(long m, long n) {
        return m * (n / gcd(m, n));
    }
    
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        
        return gcd(b, a % b);
    }
}
