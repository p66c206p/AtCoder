import java.util.*;

public class Main {
    static int MAX = 10010017;  // (1 ≦ k ≦ n ≦ 10^7 程度)
    static int MOD = 1000000007;
    static long[] fac;  // 階乗
    static long[] finv;
    static long[] inv;  // 逆元
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        
        COMinit();
        
        System.out.println(nCkMOD(10000000, 10000000));
        System.out.println(nPkMOD(10000000, 10000000));
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
    // nCk = n! / (k! * (n-k)!)
    public static long nCkMOD(int n, int k){
        if (n < k) return 0;
        if (n < 0 || k < 0) return 0;
        return fac[n] * (finv[k] * finv[n - k] % MOD) % MOD;
    }
    
    // 順列計算
    // nPk = n! / (n-k)!
    public static long nPkMOD(int n, int k){
        if (n < k) return 0;
        if (n < 0 || k < 0) return 0;
        return (fac[n] * finv[n - k]) % MOD;
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
