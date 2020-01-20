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
