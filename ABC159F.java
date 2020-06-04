import java.util.*;

public class Main {
    static long MOD = 998244353;
    
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // ans: f(L,R)の全パターンの和 (1 <= L <= R <= N)
        // f(L,R): 区間L,R中の部分列であって、和がsになるパターン数
        long ans = 0;
        
        // ans: [x^s] (f1 + f1f2 + f1f2f3 + f2 + f2f3 + f3)
        // -> (F1 = f1, F2 = f1f2 + f2, F3 = f1f2f3 + f2f3 + f3とすると、)
        // -> [x^s] (F1 + F2 + F3)
        // -> (F1 = (F0 + 1)f1, F2 = (F1 + 1)f2, F3 = (F2 + 1)f3とできる)
        // -> F1, F2, ...を都度計算して、その都度ans += [x^s]Fiする
        
        // ex. n = 3, s = 4, array[] = {2, 2, 4}
        // -> f(1,2)に1個、f(1,3)に2個、f(2,3)に1個、f(3,3)に1個 -> ans = 5
        // f1f2f3: 区間L,Rから部分列を全部取って和がいくつになるか考慮したもの
        // -> 部分列の全パターンは2^3 (各要素を選ぶか選ばないか)なので、
        // -> f1f2f3 = (1+x^2)(1+x^2)(1+x^4)
        // -> [x^4] (f1f2f3) = 2 ([2,2], [4])
        
        // dp[i][s]: [x^s] (Fi)
        long[][] dp = new long[n+1][s+1];
        for (int i = 0; i < n; i++) {
            int offset = array[i];
            
            // Fi = ((Fi-1) + 1)fi
            
            // Fi-1fi (選ぶ)
            for (int j = s; j - offset >= 0; j--) {
                dp[i+1][j] += dp[i][j - offset];
                dp[i+1][j] %= MOD;
            }
            // Fi-1fi (選ばない)
            for (int j = 0; j <= s; j++) {
                dp[i+1][j] += dp[i][j];
                dp[i+1][j] %= MOD;
            }
            
            // fi (選ぶ,選ばない)
            if (offset <= s) {
                dp[i+1][offset]++;
                dp[i+1][offset] %= MOD;
            }
            dp[i+1][0]++;
            dp[i+1][0] %= MOD;
            
            ans += dp[i+1][s];
            ans %= MOD;
        }
        System.out.println(ans);
    }
}
