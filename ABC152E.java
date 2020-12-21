import java.util.*;

public class Main {
    static int MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // ans:
        // 数列の最小公倍数 / array[i]
        
        // how:
        // 1. LCMはオーバーフローするのでmapで持つ。
        // 2. 割り算は逆元の掛け算にする。
        
        Sieve sieve = new Sieve(1000000);
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> now = sieve.factorMap(array[i]);
            
            for (Integer key : now.keySet()) {
                int val = now.get(key);
                
                int org_val = map.getOrDefault(key, 0);
                map.put(key, Math.max(org_val, val));
            }
            // System.out.println(map.toString());
        }
        
        long l = 1;
        for (Integer key : map.keySet()) {
            int val = map.get(key);
            
            while (val-- > 0) {
                l *= key;
                l %= MOD;
            }
        }
        // System.out.println(l);
        
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += l * modinv((int)array[i], MOD);
            ans %= MOD;
        }
        System.out.println(ans);
    }
    
    public static long modinv(long a, long m) {
        long b = m, u = 1, v = 0;
        
        while (b > 0) {
            long tmp = 0;
            long t = a / b;
            a -= t * b;
            
            tmp = a;
            a = b;
            b = tmp;
            
            u -= t * v;
            
            tmp = u;
            u = v;
            v = tmp;
        }
        
        u %= m;
        if (u < 0) u += m;
        
        return u;
    }
}

class Sieve {
    int[] minFactor;    // 最小の素因数

    Sieve(int n) {
        minFactor = new int[n + 1];
        minFactor[0] = -1;
        minFactor[1] = -1;
        
        for (int i = 2; i <= n; i++) {
            if (minFactor[i] != 0) continue;
            
            minFactor[i] = i;
            
            if ((long)i * i > (long)n) continue;
            
            for (int j = i * i; j <= n; j += i) {
                if (minFactor[j] != 0) continue;
                
                minFactor[j] = i;
            }
        }
    }
    
    boolean isPrime(int x) {
        if (minFactor[x] == x) {
            return true;
        }
        
        return false;
    }
    
    // nの素因数のリスト (60 => [2, 2, 3, 5])
    List<Integer> factorList(int x) {
        List<Integer> res = new ArrayList<>();
        
        while (x != 1) {
            res.add(minFactor[x]);
            x /= minFactor[x];
        }
        
        return res;
    }
    
    // nの素因数の頻度 (60 => (2 => 2, 3 => 1, 5 => 1)
    Map<Integer, Integer> factorMap(int x) {
        Map<Integer, Integer> res = new HashMap<>();
        
        List<Integer> factorList = factorList(x);
        
        for (Integer f : factorList) {
            if (!res.containsKey(f)) {
                res.put(f, 1);
            } else {
                int count = res.get(f);
                res.put(f, count + 1);
            }
        }
        
        // for (int i = 1; i <= x; i++) {
        //     if (!res.containsKey(i)) {
        //         res.put(i, 0);
        //     }
        // }
        
        return res;
    }
}
