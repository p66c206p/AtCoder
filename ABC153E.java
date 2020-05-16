import java.util.*;

public class Main {
    static int INF = 1001001009;
    
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
                dp[i][w] = INF;
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
                if (w - weights[i] >= 0) {
                    // 選択できる場合、選択する方/しない方の最善を選ぶ
                    // (選択するには品物"i"のダメージ[i]分少ないとこからなら遷移できる)
                    // (※個数制限なしなので自分(i)から遷移できる (ありならi-1から遷移))
                    dp[i + 1][w] = Math.min(dp[i + 1][w - weights[i]] + values[i], dp[i][w]);
                } else {
                    // 選択できない場合、ダメージ0から遷移する
                    dp[i + 1][w] = Math.min(dp[i + 1][0] + values[i], dp[i][w]);
                }
            }
        }
        // for (int i = 0; i < n+1; i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        
        System.out.println(dp[n][W]);
    }
}
