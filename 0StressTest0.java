import java.util.*;

public class Main {
    static long MOD = 1000000007;
    static long inv2 = 500000004;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextLong();
        }
        
        int trying = 100;
        int resize = 5;
        n = resize;
        while (trying-- > 0) {
            // 数列の生成
            array = new long[n];
            for (int i = 0; i < n; i++) {
                Random random = new Random();
                int randomValue = random.nextInt(1000000000);
                array[i] = randomValue;
            }
            
            // 解答
            // parta: 総和の2乗
            long parta = 0;
            for (int i = 0; i < n; i++) {
                parta += array[i];
                parta %= MOD;
            }
            parta = (parta * parta) % MOD;
            // System.out.println(parta);
            
            // partb: 各要素の2乗の和
            long partb = 0;
            for (int i = 0; i < n; i++) {
                partb -= (array[i] * array[i]) % MOD;
                partb %= MOD;
            }
            // System.out.println(partb);
            
            long ans = parta - partb;
            if (ans < 0) ans += MOD;
            ans %= MOD;
            ans *= inv2;
            ans %= MOD;
            // System.out.println(ans);
            
            
            // 愚直解
            long ans2 = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    ans2 += (array[i] * array[j]) % MOD;
                    ans2 %= MOD;
                }
            }
            
            
            // NGパターンを出力
            if (ans != ans2) {
                System.out.println(Arrays.toString(array));
                System.out.println(ans);
                System.out.println(ans2);
                return;
            }
        }
    }
    
    public static long modpow(long num, long n) {
        // ex. 3^10
        // 3^10 = 3^(0b1010)
        // = 3^8が1個 * 3^4が0個 * 3^2が1個 * 3^1が0個
        // (次の桁の値は(前の桁)^2になる)
        
        long res = 1;
        long digit = num;
        
        while (n > 0) {
            long lsb = n & 1;
            if (lsb == 1) {
                res *= digit;
                res %= MOD;
            }
            
            digit = digit * digit;
            digit %= MOD;
            n = n >> 1;
        }
        
        return res;
    }
}
