import java.util.*;

public class Main {
    static long INF = (long)1e+18;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        int V = (int)Math.pow(10, 3) * n;
        
        int[][] array = new int[n][2];
        for (int i = 0; i < n; i++) {
            int w = sc.nextInt();
            int v = sc.nextInt();
            array[i][0] = w;
            array[i][1] = v;
        }
        
        // dp[i][j]: アイテムiまで見てきたときの、価値jの最小重量
        long[][] dp = new long[n+1][V+1];
        // 初期化
        for (long[] dps : dp) {
            Arrays.fill(dps, INF);
        }
        dp[0][0] = 0;
        
        for (int i = 0; i < n; i++) {
            int w = array[i][0];
            int v = array[i][1];
            for (int j = 0; j <= V; j++) {
                for (int k = 0; k <= 1; k++) {
                    // 状態jでアイテムiをk個使う
                    int ni = i+1;
                    int nj = j + v * k;
                    long plus = w * k;
                    
                    // カットとかcontinue
                    if (nj > V) continue;
                    
                    // 遷移
                    dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + plus);
                }
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        // ans: dp[n][V]のうち重さがW以下のmax
        long ans = 0;
        for (int j = 0; j <= V; j++) {
            if (dp[n][j] <= W) ans = Math.max(ans, j);
        }
        System.out.println(ans);
    }
}
