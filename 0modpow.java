        long[] nibeki = modpow(2, 2000*2000);
        
    public static long[] modpow(long num, long n) {
        long[] res = new long[(int)n+1];
        res[0] = 1;
        for (int i = 1; i <= n; i++) {
            res[i] = res[i-1] * num;
            res[i] %= MOD;
        }
        
        return res;
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
