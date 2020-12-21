    // 逆元 O(log m) (m = mod)
    // ex. 2 -> 500000004
    public static long inv_mod(long a, long mod) {
        long b = mod;
        long p = 1, q = 0;
        while (b > 0) {
            long c = a / b;
            long d;
            d = a;
            a = b;
            b = d % b;
            d = p;
            p = q;
            q = d - c * q;
        }
        return p < 0 ? p + mod : p;
    }
