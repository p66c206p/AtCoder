import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        Sieve sieve = new Sieve(n);
        
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += (long)sieve.factorCount[i] * i;
        }
        System.out.println(ans);
    }
}
class Sieve {
    int[] minFactor;    // 最小の素因数
    int[] factorCount;  // 約数の個数
    
    Sieve(int n) {      // (N <= 10^7)
        factorCount = new int[n + 1];
        
        Arrays.fill(factorCount, 1);
        
        for (int i = 2; i <= n; i++) {
            factorCount[i]++;
            
            for (int j = 2; i * j <= n; j++) {
                factorCount[i*j]++;
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
            int count = res.getOrDefault(f, 0);
            res.put(f, ++count);
        }
        
        // for (int i = 1; i <= x; i++) {
        //     if (!res.containsKey(i)) {
        //         res.put(i, 0);
        //     }
        // }
        
        return res;
    }
}
