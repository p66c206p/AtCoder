import java.util.*;

public class Main {
    static int MAX = 10010017;  // (1 ≦ k ≦ n ≦ 10^7 程度)
    static int MOD = 998244353;
    static long[] fac;  // 階乗
    static long[] finv;
    static long[] inv;  // 逆元
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        
        int r = n - k;
        int b = k;
        
        COMinit();
        
        // ans_i: m色の玉をn個並べる時、隣り合う組で同じ色のものがi組あるパターン数
        // ex. m = 7, n = 5, i = 2;
        // -> (1-7) * (前以外) * (前以外) ... = m * (m-1)^(n-1)
        // -> 「3個は決めてしまって、2個は後から同じ色を入れる」とする
        // -> "123"なら、1の右、2の右、3の右に同じ色を入れる
        // -> 仕切りが2つでボールが2つ = n-i-1+iCi
        // -> ans_i = m * (m-1)^(n-1-i) * n-1Ci
        
        long ans = 0;
        
        for (int i = 0; i <= k; i++) {
            long ans_i = ((long)m * modpow(m-1, n-1-i) % MOD) * nCkMOD(n-1, i);
            ans_i %= MOD;
            
            ans += ans_i;
            ans %= MOD;
            // System.out.println(nCkMOD(n, i));
        }
        System.out.println(ans);
    }
    
    // テーブルを作る前処理
    public static void COMinit() {        
        fac = new long[MAX];
        finv = new long[MAX];
        inv = new long[MAX];
        
        fac[0] = fac[1] = 1;
        finv[0] = finv[1] = 1;
        inv[1] = 1;
        for (int i = 2; i < MAX; i++){
            fac[i] = fac[i - 1] * i % MOD;
            inv[i] = MOD - inv[MOD%i] * (MOD / i) % MOD;
            finv[i] = finv[i - 1] * inv[i] % MOD;
        }
    }
    
    // 二項係数計算(1 ≦ k ≦ n ≦ 10^7 程度)
    public static long nCkMOD(int n, int k){
        if (n < k) return 0;
        if (n < 0 || k < 0) return 0;
        return fac[n] * (finv[k] * finv[n - k] % MOD) % MOD;
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
