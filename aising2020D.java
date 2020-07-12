import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        char[] c = str.toCharArray();
        
        // ans[i]: now = 0になるのに必要な「now %= popcount」の回数
        // (但し、i桁目のbitを反転したもの)
        // ex. 11001001 (201)
        // -> ans[0]: 01001001 % 3 = 1 -> 1 % 1 = 0 => ans=2
        // -> ans[1]: 10001001 % 3 = 2 -> 10 % 1 = 0 => ans=2
        // -> ans[2]: 11101001 % 5 = 3 -> 11 % 2 = 1 -> 1 % 1 = 0 => ans=3
        // 
        // => 最初は2*10^5桁あるので、初回だけ、(201 % 3or5)を計算して、
        //    i桁目bit反転した数を足し引きすることにより「popcountで割る」を行う
        
        int originalPopcount = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] == '1') originalPopcount++;
        }
        int plusPopcount = originalPopcount + 1;
        int minusPopcount = originalPopcount - 1;
        
        // plusMod[0]: '0'->'1'にした時のpopcountで割った余り
        // ex. 13524 -> {13524%m, 3524%m, 524%m, 24%m, 4%m}
        long[] plusMod = new long[n+1];
        int digit = 1;
        int m = plusPopcount;
        for (int i = n - 1; i >= 0; i--) {
            long num = (c[i] - '0') * digit;
            num %= m;
            
            plusMod[i] = plusMod[i+1] + num;
            plusMod[i] %= m;
            
            digit *= 2;
            digit %= m;
        }
        
        // minusMod[0]: '1'->'0'にした時のpopcountで割った余り
        long[] minusMod = new long[n+1];
        digit = 1;
        m = minusPopcount;
        if (m != 0) {
            for (int i = n - 1; i >= 0; i--) {
                long num = (c[i] - '0') * digit;
                num %= m;
                
                minusMod[i] = minusMod[i+1] + num;
                minusMod[i] %= m;
                
                digit *= 2;
                digit %= m;
            }
        }
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            
            int now = 0;
            if (c[i] == '0') {
                // '0'から'1'へ
                now = (int)(plusMod[0] + modpow(2, n-1-i, plusPopcount));
                now %= plusPopcount;
                count++;
            } else {
                // '1'を'0'へ
                if (minusPopcount == 0) {
                    System.out.println(0);
                    continue;
                }
                
                now = (int)(minusMod[0] - modpow(2, n-1-i, minusPopcount));
                if (now < 0) now += minusPopcount;
                now %= minusPopcount;
                count++;
            }
            // System.out.println(now);
            
            // 後は普通に
            while (now > 0) {
                int popcount = Integer.bitCount(now);
                now %= popcount;
                count++;
            }
            
            System.out.println(count);
        }
    }
    
    public static long modpow(long num, long n, int mod) {
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
                res %= mod;
            }
            
            digit = digit * digit;
            digit %= mod;
            n = n >> 1;
        }
        
        return res;
    }
}
