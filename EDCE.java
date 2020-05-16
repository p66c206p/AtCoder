import java.util.*;

public class Main {
    static long INF = 1001001001001001001L;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        int V = 0;
        int[] weights = new int[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
            V += values[i];
        }
        
        // dp[i][v]: 品物0~iまで考慮した上で、価値vでの最小重量
        long[][] dp = new long[n + 1][V + 1];
        // 初期化
        for (int i = 0; i <= n; i++) {
            for (int v = 0; v <= V; v++) {
                dp[i][v] = INF;
            }
        }
        dp[0][0] = 0;
        
        // 考慮する品物を1つずつ増やして考える
        for (int i = 0; i < n; i++) {
            for (int v = 0; v <= V; v++) {
                // 品物iを選択できる(価値[i]がv以下)
                if (v - values[i] >= 0) {
                    // 選択できる場合、選択する方/しない方の最善を選ぶ
                    // (選択するには価値[i]分少ないとこからなら遷移できる)
                    dp[i + 1][v] = Math.min(dp[i][v - values[i]] + weights[i], dp[i][v]);
                } else {
                    // 選択できない場合、品物i-1の時と変わらない
                    dp[i + 1][v] = dp[i][v];
                }
            }
        }
        
        // ans: 品物nまでなめた時の、重量がW以下で最大の価値
        int ans = 0;
        for (int v = V; v >= 0; v--) {
            if (dp[n][v] <= W) {
                ans = v;
                System.out.println(ans);
                return;
            }
        }
    }
}
