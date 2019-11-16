public static long nPkMOD(long n, long k, long mod) {
    long res = 1;
    for (long i = n; i > k; i--) {
        res = res * i % mod;
    }

    return res;
}
