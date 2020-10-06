import java.util.*;

public class Main {
    static long MOD = 998244353;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int S = sc.nextInt();
        
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            array[i] = num;
        }
        
        // ans:
        // 配列の空でない部分集合Sの
        // 空でない部分集合Tの総和がSUMになるようなTの個数
        
        // ex. array = {1,2,3,...}
        
        // 部分集合S:
        // i番目で、「今までの部分集合Sらにiを加える」「{i}を追加する」をする
        // -> 部分集合Sを全列挙できる
        // i=0: 
        // i=1: 1
        // i=2: 12 2
        // i=3: 13 123 23 3
        
        // 部分集合T:
        // i番目で、「[各始点,i]の持つ部分集合Tをもたせる」をする
        // -> 部分集合Tを全列挙できる
        // i=0: 
        // i=1: {1}={,1}
        // i=2: {1,2}={,1,2,12}, {2}={,2}
        // i=3: {1,3}={,1,3,13}, {1,2,3}={,1,2,12,3,13,23,123}, {2,3}={,2,3,23}, {3}={,3}
        
        // ※空でない部分集合は考えないが、遷移させやすいのであるものとする
        
        
        // dp[i][j]: 最大のidxがiの部分集合において、総和jになる部分集合の個数
        long[][] dp = new long[n+1][S+1];
        // 初期化
        
        // sdp: dpの累積和
        long[][] sdp = new long[n+1][S+1];
        
        for (int i = 0; i < n; i++) {
            int num = array[i];
            
            // 「今までの部分集合Sらにiを加える」
            for (int j = 0; j <= S; j++) {
                for (int k = 0; k <= 1; k++) {
                    // 状態jでアイテムiをk個使う
                    int ni = i+1;
                    int nj = j + num * k;
                    
                    // カットとかcontinue
                    if (nj > S) continue;
                    
                    // 遷移
                    dp[ni][nj] += sdp[i][j];
                    dp[ni][nj] %= MOD;
                }
            }
            
            // 「{i}を追加する」
            dp[i+1][0]++;
            dp[i+1][0] %= MOD;
            if (num <= S) {
                dp[i+1][num]++;
                dp[i+1][num] %= MOD;
            }
            
            for (int j = 0; j <= S; j++) {
                sdp[i+1][j] = sdp[i][j] + dp[i+1][j];
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        long ans = 0;
        for (int i = 0; i <= n; i++) {
            ans += dp[i][S];
            ans %= MOD;
        }
        System.out.println(ans);
    }
}
