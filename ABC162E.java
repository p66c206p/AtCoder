import java.util.*;

public class Main {
    static int MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        // ans: 要素が全て1以上k以下の長さnの数列の、gcdの総和
        long ans = 0;
        
        // 全数列のgcdを実際に計算するのは間に合わない
        // -> gcd=x(1<=x<=k)になる数列のパターン数を数える。
        
        // f(x) = gcd=xになる数列のパターン数
        // -> ans = 1*f(1) + 2*f(2) + ... + k*f(k)
        long[] f = new long[k+1];
        
        // f(x)を降順に数え上げる。
        // ex. k=12
        // f(12) <- [12,12,...,12]
        // ...
        // f(7) <- [7,7,...,7]
            // -> f(12～7) = 1
        // f(6) = f(6の倍数) - f(12)
        // f(2) = f(2の倍数) - f(4) - f(6) - ... - f(12)
            // -> f(x) = f(xの倍数) - f(2*x) - f(3*x) - ...
        for (int i = k; i >= 1; i--) {
            // ex. f(3の倍数) = 3の倍数の個数^配列の長さ
            long baisuu = modpow((k / i), n);
            
            long tmp = 0;
            for (int j = 2 * i; j <= k; j += i) {
                tmp += f[j];
                tmp %= MOD;
            }
            
            f[i] = baisuu - tmp;
            if (f[i] < 0) {
                f[i] += MOD;
            }
        }
        
        for (int i = 1; i <= k; i++) {
            // System.out.println(f[i]);
            ans += f[i] * i;
            ans %= MOD;
        }
        System.out.println(ans);
    }
    
    public static long modpow(long a, long n) {
        long res = 1;
        while (n > 0) {
            long tmp = n & 1;
            if (tmp > 0) res = res * a % MOD;
            a = a * a % MOD;
            n >>= 1;
        }
        return res;
    }
}
