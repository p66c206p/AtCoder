import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        Sieve sieve = new Sieve(n);
        
        Map<Integer, Integer> map = sieve.factorMap(60);
        
        for (Integer key : map.keySet()) {
            int val = map.get(key);
            System.out.println(key + " " + val);
        }
    }
}

class Sieve {
    int[] minFactor;    // 最小の素因数

    Sieve(int n) {
        minFactor = new int[n + 1];
        Arrays.fill(minFactor, -1);
        
        for (int i = 2; i <= n; i++) {
            // 素数でないなら篩をかけない
            if (minFactor[i] != -1) continue;
            
            minFactor[i] = i;
            
            // 自分の倍数の最小の素因数を自分とする
            // (但し未確定の数に限る)
            for (int j = 2; i * j <= n; j++) {
                if (minFactor[i * j] == -1) {
                    minFactor[i * j] = i;
                }
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
