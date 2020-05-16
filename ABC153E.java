import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int W = sc.nextInt();
        int n = sc.nextInt();
        int[] weights = new int[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
        }
        
        // dp[i][w]: 品物0~iまでなめた時のwダメージ与えられる最小コスト
        int[][] dp = new int[n + 1][W + 1];
        // 初期化
        for (int i = 0; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                dp[i][w] = 1000000000;
            }
        }
        // 0ダメージ与えられる最小のコストは0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        
        // dp[i + 1][w] (品物iまでを使ったときの)wダメージ与えられる最小のコスト
        for (int i = 0; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                // 品物iを選択できる(重量[i]がw以下)
                if (weights[i] <= w) {
                    // 選択できる場合、選択する方/しない方の最善を選ぶ
                    // (選択するにはダメージ[i]分少ないとこからなら遷移できる)
                    dp[i + 1][w] = Math.min(dp[i + 1][w - weights[i]] + values[i], dp[i][w]);
                } else {
                    // 選択できない場合、品物i-1の時と変わらない
                    dp[i + 1][w] = Math.min(values[i], dp[i][w]);
                }
            }
        }
        
        System.out.println(dp[n][W]);
    }
}
