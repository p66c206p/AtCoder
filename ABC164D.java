import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char c[] = str.toCharArray();
        int n = str.length();
        int p = 2019;
        
        // ans: 文字列で、連続部分列がmod 2019で0になるi, jのパターン数
        // ex. 1817181712114
        //         ^^^^^
        long ans = 0;
        
        // 式変形
        // ・連続部分列がmod 2019で0になるi, j
        // -> ex. XXXX18171YYYYで、18171 ≡ 0 (mod 2019)
        // -> 18171YYYY - YYYY ≡ 0 (mod m)
        // -> 181710000 ≡ 0 (mod m)
        // -> (s[j] - s[i]) * 10^4 ≡ 0 (mod m)
        // -> (合同式で割り算はmod pと互いに素なら可能)
        // -> (gcd(2019, 10) = 1)
        // -> s[j] - s[i] ≡ 0 (mod 2019)
        // -> s[j] ≡ s[i] (mod 2019) のパターン数
        
        // s: 累積和 (mod 2019)
        // (右の桁(c[n-1])から順に見る)
        long[] s = new long[n+1];
        int ten = 1;
        for (int i = n - 1; i >= 0; i--) {
            // ex. c[i] * 10^x(mod m)
            // -> c[i] * (10^(x-1)(mod m) * 10^1 % m)
            // (10^(x-1)(mod m)は前のループで求めてる)
            
            long a = ((c[i] - '0') * ten) % p;
            s[i] = (s[i+1] + a) % p;
            ten = (ten * 10) % p;
        }
        
        // i, jのパターン数を数え上げる
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        for (int i = 0; i <= n; i++) {
            long key = s[i];
            int cnt = map.getOrDefault(key, 0);
            
            ans += cnt;
            map.put(key, ++cnt);
        }
        System.out.println(ans);
    }
}
