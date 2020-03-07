public static long nPkMOD(long n, long k, long mod) {
    long res = 1;
    for (long i = n; i > n - k; i--) {
        res = res * i % mod;
    }

    return res;
}
