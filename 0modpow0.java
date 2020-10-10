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
