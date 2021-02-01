import java.util.*;

public class Main {
    static long INF = 1001001009;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        int[] z = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
            z[i] = sc.nextInt();
        }
        
        // ans: 巡回セールスマン問題
        // 条件: (頂点0からスタート, 始点に戻る)
        
        // cost[i][j]: 頂点i -> jへのコスト
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = x[i];
                int b = y[i];
                int c = z[i];
                int p = x[j];
                int q = y[j];
                int r = z[j];
                
                cost[i][j] = Math.abs(p-a)+Math.abs(q-b)+Math.max(0,r-c);
            }
        }
        
        int all = (1<<n) - 1;
        
        // dp[i][j]: 集合iを既に通り、最後にjを訪れた最小コスト
        long[][] dp = new long[all+1][n];
        // 初期化
        for (long[] dps : dp) {
            Arrays.fill(dps, INF);
        }
        
        // 始点: 頂点0のみ
        dp[1 << 0][0] = 0;
        
        for (int i = 1; i <= all; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    // 状態i,jで(i | k)へ遷移する
                    int ni = i | (1 << k);
                    int nj = k;
                    int plus = cost[j][k];
                    
                    dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + plus);
                }
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        long ans = INF;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[all][i] + cost[i][0]);
        }
        System.out.println(ans);
    }
}
