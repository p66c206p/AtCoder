import java.util.*;

public class Main {
    static long MOD = 1000000007;
    static long inv2 = 500000004;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextLong();
        }
        
        // ans: 数列の全ペア(i,j)の積の和
        // 言い換え:
        // abcd -> ans = ab ac ad bc bd cd
        // (a+b+c+d)^2 = a^2+...d^2 + 2(ab ac ad ... cd)
        // ans = ((a+b+c+d)^2 - (a^2+...d^2))/2 (inv2を掛ける)
        
        // ans = (□ - ＼) / 2 = ⊿
        
        // □
        long all = 0;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
            sum %= MOD;
        }
        all = (sum * sum) % MOD;
        
        // ＼
        long line = 0;
        for (int i = 0; i < n; i++) {
            line += (array[i] * array[i]) % MOD;
            line %= MOD;
        }
        
        // ⊿
        long ans = all - line;
        if (ans < 0) ans += MOD;
        ans *= inv2;
        ans %= MOD;
        System.out.println(ans);
    }
}
