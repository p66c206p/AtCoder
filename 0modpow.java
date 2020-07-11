    public static long modpow(long num, long n) {
        // ex. 3^10
        // 3^10 = 3^(0b1010)
        // = 3^8が1個 * 3^4が0個 * 3^2が1個 * 3^1が0個
        
        long res = 1;
        while (n > 0) {
            long lsb = n & 1;
            if (lsb == 1) {
                res *= num;
                res %= MOD;
            }
            
            num *= num;
            num %= MOD;
            n = n >> 1;
        }
        return res;
    }
