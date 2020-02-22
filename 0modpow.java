public static long modpow(long a, long n) {
    long res = 1;
    while (n > 0) {
        long tmp = n & 1;
        if (tmp > 0) res = res * a % MOD;
        a = a * a % MOD;
        n >>= 1;
    }
    return res;
}
