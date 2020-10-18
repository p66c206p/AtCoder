import java.util.*;

public class Main {
    static long INF = 1001001009;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] array = new int[n][3];
        for (int i = 0; i < n; i++) {
            array[i][0] = sc.nextInt();
            array[i][1] = sc.nextInt();
            array[i][2] = sc.nextInt();
        }
        
        // ans: 巡回セールスマン問題
        // 頂点0からスタートし、全ての頂点を1度だけ通り、頂点0に帰る時の最小コスト
        
        // cost[i][j]: 頂点i -> jへのコスト
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int a = array[i][0];
                int b = array[i][1];
                int c = array[i][2];
                int p = array[j][0];
                int q = array[j][1];
                int r = array[j][2];
                
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
        dp[0][0] = 0;
        
        for (int i = 0; i <= all; i++) {
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
        
        System.out.println(dp[all][0]);
    }
}
