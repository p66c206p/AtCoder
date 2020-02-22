import java.util.*;

public class Main {
    static int MAX = 10010017;
    static int MOD = 1000000007;
    static long[] fac;  // 階乗
    static long[] finv;
    static long[] inv;  // 逆元
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        
        COMinit();
        
        long tmp = nCkMOD(1000000000, 10000000);
        System.out.println(tmp);
        
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
    // 二項係数計算(1 ≦ n ≦ 10^9, 1 ≦ k ≦ 10^7 程度)
    public static long nCkMOD(int n, int k){
        if (n < k) return 0;
        if (n < 0 || k < 0) return 0;
        
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = (res * ((n + 1) - i)) % MOD;
            res = (res * inv[i]) % MOD;
        }
        
        return res;
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
