import java.util.*;
import java.util.Map.*;

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
        
        Sieve sieve = new Sieve(1000000);
        Map<Integer, Integer> factorMap = new HashMap<Integer, Integer>(); 
        
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> map = sieve.factorMap(array[i]);
            
            for (Entry<Integer, Integer> entry : map.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();
                
                if (!factorMap.containsKey(key)) {
                    factorMap.put(key, val);
                } else {
                    int exVal = factorMap.get(key);
                    factorMap.put(key, Math.max(val, exVal));
                }
            }
        }
        
        long lcm = 1;
        for (Entry<Integer, Integer> entry : factorMap.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            
            while (val-- > 0) {
                lcm = (lcm * key) % MOD;
            }
        }
        
        long count = 0;
        for (int i = 0; i < n; i++) {
            count = (count + (lcm * modinv(array[i], MOD) % MOD)) % MOD;
        }
        
        System.out.println(count);
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
        List<Integer> res = new ArrayList<Integer>();
        
        while (x != 1) {
            res.add(minFactor[x]);
            x /= minFactor[x];
        }
        
        return res;
    }
    
    // nの素因数の頻度 (60 => (2 => 2, 3 => 1, 5 => 1)
    Map<Integer, Integer> factorMap(int x) {
        Map<Integer, Integer> res = new HashMap<Integer, Integer>();
        
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
