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
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        int r = n - k;
        int b = k;
        
        COMinit();
        
        // ans[i]: 赤玉と青玉を並べた時、連続する青玉の列がi個あるパターン数
        // -> 連続するbの列がi個あるパターン
        // ex. bbrb -> 2 bbbrr -> 1 brbrb-> 3
        long ans = 0;
        
        for (int i = 0; i < k; i++) {
            // tmp1: bbbbbの中にrをi個入れる
            // -> (b-1)個の候補からi個選ぶ
            long tmp1 = nCkMOD(b-1, i);
            
            // tmp2: 残りのrは両端か遮ったとこに置ける
            // -> i+2個の箱に無色の玉を(r-i)個入れるパターン数
            // -> r-1個のボールとi+2-1個の仕切り
            // -> (r-1)+(i+2-1) c (r-1)
            long tmp2 = nCkMOD((i+1)+(r-i), (r-i));
            
            long tmp = (tmp1 * tmp2)%MOD;
            System.out.println(tmp);
        }
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
