import java.util.*;

public class Main {
    static long MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        // ans: 要素が全て1以上k以下の長さnの数列の、gcdの総和
        
        // how:
        // gcdが1, 2, ... になるパターン数を数える。
        // ex. gcdが3 = gcdが3の倍数 - gcdが6,9,12...
        // -> 降順に調べていけばOK。
        // -> gcdがxの倍数 = 全ての項がxの倍数。
        
        // res[i]: gcd=iであるパターン数
        long[] res = new long[k+1];
        for (int g = k; g >= 1; g--) {
            int tmp1 = k/g;
            long tmp2 = modpow(tmp1, n);
            
            for (int i = g*2; i <= k; i += g) {
                tmp2 -= res[i];
                if (tmp2 < 0) tmp2 += MOD;
            }
            
            res[g] = tmp2;
        }
        
        long ans = 0;
        for (int i = 1; i <= k; i++) {
            ans += res[i] * i;
            ans %= MOD;
        }
        System.out.println(ans);
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
