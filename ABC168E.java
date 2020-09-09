import java.util.*;

public class Main {
    static long MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        // ans:
        // n個の中から1個以上選ぶ選び方
        // (ただし、仲の悪いペアを含まない)
        // 仲が悪い＝「AiAj + BiBj = 0」
        
        // どうやって数える？:
        // 仲の悪いペアをSi、Tiとする
        // -> S1T1,S2T2,...は独立に考えてOKになる
        // 「Siから1つ以上かつTiから1つ以上」でNG
        // = 「Siからのみ1つ以上 + Tiからのみ1つ以上 + SiTiから1つも選ばない」はOK
        
        // ex. 123 45
        // 123->2^3-1 45->2^2-1 
        // ok = 2^3-1 + 2^2-1 + 1 = 11
        
        // AiAj + BiBj = 0
        // Ai/Bi = -Bj/Aj
        // ex. (1/3,-3/1)はNG
        // -> こういう集合S,Tを持ちたい。
        // -> 1/3が来たら1/3のSに、-3/1が来たら1/3のTに入れる。
        // Sは正/非負、Tは非正/正にする。
        
        // maps, mapt: 仲が悪いペアの集合
        // ex. 1/3 -3/1 1/3
        // -> maps: 1/3=2, mapt: 1/3=1 
        Map<String, Integer> maps = new HashMap<String, Integer>();
        Map<String, Integer> mapt = new HashMap<String, Integer>();
        
        // zero: 0/0の数（zeroは誰とも組めない）
        int zero = 0;
        
        // 集合S, Tに格納する
        for (int i = 0; i < n; i++) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            
            if (a == 0 && b == 0) {
                zero++;
                continue;
            }
            
            // 約分する
            long g = gcd(a, b);
            a /= g;
            b /= g;
            // System.out.println(a + " " + b);
            
            // S or Tのフォーマットにする
            if (a < 0 && b < 0) {
                a *= -1;
                b *= -1;
            }
            if (a >= 0 && b < 0) {
                a *= -1;
                b *= -1;
            }
            // System.out.println(a + " " + b);
            
            boolean s = (a > 0 && b >= 0);
            if (s) {
                String key = a + " " + b;
                int val = maps.getOrDefault(key, 0);
                maps.put(key, ++val);
                val = mapt.getOrDefault(key, 0);
                mapt.put(key, val);
            } else {
                String key = b + " " + -a;
                int val = mapt.getOrDefault(key, 0);
                mapt.put(key, ++val);
                val = maps.getOrDefault(key, 0);
                maps.put(key, val);
            }
        }
        // System.out.println(maps.toString());
        // System.out.println(mapt.toString());
        
        long ans = 1;
        for (String key : maps.keySet()) {
            long now = 0;
            int vals = maps.getOrDefault(key, 0);
            int valt = mapt.getOrDefault(key, 0);
            
            now += modpow(2, vals) - 1;
            now %= MOD;
            now += modpow(2, valt) - 1;
            now %= MOD;
            now += 1;
            now %= MOD;
            
            ans *= now;
            ans %= MOD;
        }
        ans += zero;
        ans %= MOD;
        ans -= 1;
        if (ans < 0) ans += MOD;
        System.out.println(ans);
    }
    
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        
        return gcd(b, a % b);
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
