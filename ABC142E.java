import java.util.*;

public class Main {
    static long INF = 1001001001001001001L;
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] array = new int[m][2];
        
        for (int i = 0; i < m; i++) {
            int cost = sc.nextInt();
            int k = sc.nextInt();
            
            // 集合s (ex. 1011 = bit0,1,3を含む集合)
            int s = 0;
            for (int j = 0; j < k; j++) {
                int digit = sc.nextInt() - 1;
                
                // digit番目のbitを立てる
                s |= 1<<digit;
            }
            
            array[i][0] = s;
            array[i][1] = cost;
        }
        
        // dp[s] = 集合sに至るまでの最小コスト
        long[] dp = new long[1<<n];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int s = 0; s < 1<<n; s++) {
            for (int i = 0; i < m; i++) {
                // t = 集合sからi番目の鍵を使って行ける集合
                int t = s | array[i][0];
                int cost = array[i][1];
                
                // 最小コストの更新
                dp[t] = Math.min(dp[t], dp[s] + cost);
            }
        }
        
        // 演算順位に注意
        if (dp[(1<<n) - 1] == INF) dp[(1<<n) - 1] = -1;
        System.out.println(dp[(1<<n) - 1]);
    }
}
