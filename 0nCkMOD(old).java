public static long nCkMOD(long n, long k, long mod) {
    if (k > n - k) return nCkMOD(n, n - k, mod);

    long res = 1;
    for (int i = 0; i < k; i++) {
        res = (res * (n - i)) % mod;
    }
    for (int i = 1; i <= k; i++) {
        res = (res * inverse(i, mod)) % mod;
    }

    return res % mod;
}

private static long inverse(long a, long mod) {
    return modpow(a, mod - 2, mod);
}

private static long modpow(long a, long n, long mod) {
    long res = 1;

    while (n > 0) {
        if (n % 2 == 1) res = (res * a) % mod;
        a = (a * a) % mod;
        n >>= 1;
    }

    return res % mod;
}
