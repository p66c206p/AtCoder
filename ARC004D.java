import java.util.*;

public class Main {
    static int MAX = 100005;
    static int MOD = 1000000007;
    static long[] fac;  // 階乗
    static long[] finv;
    static long[] inv;  // 逆元
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        int k = sc.nextInt();
        
        // ans:
        // 整数NをK個の整数の積で表すパターン数
        
        // ex. (n,m)=(-2,3)
        // -> (1,1,2),(1,2,1),(2,1,1)にマイナスをうまくつけたもの。
        
        // how:
        // 素因数分解する。
        // 素因数keyのval人をK個の部屋に入れるパターン数を計算する。
        // (異なる素因数同士は独立に計算できる)
        // マイナスの付け方はN<0なら奇数個、N>0なら偶数個つけるパターン数を計算。
        
        if (n < 0) {
            n *= -1;
        }
        
        COMinit();
        
        // map: nの素因数の頻度 (60 => (2 -> 2, 3 -> 1, 5 -> 1)
        // list: nの素因数のリスト (60 => [2, 2, 3, 5])
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        List<Long> list = new ArrayList<Long>();
        long m = n;
        for (long i = 2; (i * i) <= n; i++) {
            int cnt = 0;
            
            while ((m % i) == 0) {
                cnt++;
                m /= i;
                list.add(i);
            }
            
            if (cnt > 0) {
                map.put(i, cnt);
            }
        }
        if (m > 1) {
            map.put(m, 1);
            list.add(m);
        }
        
        long ans = 1;
        for (Long key : map.keySet()) {
            int val = map.get(key);
            
            // 素因数keyのボールval個と仕切りがk-1個
            ans *= nCkMOD(val+k-1, val);
            ans %= MOD;
        }
        
        // nCkについて、偶数の総和と奇数の総和は同じ
        // 5c0+5c2+5c4 = 5c1+5c3 = 2^(5-1)
        ans *= modpow(2, k-1);
        ans %= MOD;
        
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
    // 二項係数計算(1 ≦ n ≦ 10^9, 1 ≦ k ≦ 10^7 程度)
    // nCk = n! / (k! * (n-k)!)
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
    
    // 順列計算(1 ≦ k ≦ n ≦ 10^7 程度)
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
