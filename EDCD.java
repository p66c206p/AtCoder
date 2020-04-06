import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        int[] weight = new int[n];
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }
        
        // dp[i][w]: 品物0~iまで考慮した上で、重量wでの最高価値
        int[][] dp = new int[n + 1][W + 1];
        for (int w = 0; w <= W; w++) {
            dp[0][w] = 0;
        }
        
        // 品物0, (0,1), (0,1,2), ... だけを考えた時を順々にやる
        for (int i = 0; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                // 品物iを選択できる(重量[i]がw以下)
                if (weight[i] <= w) {
                    // 選択できる場合、選択する方/しない方の大きい方を入力
                    // (選択するには重量[i]分減らしたとこから遷移する必要がある)
                    dp[i + 1][w] = Math.max(dp[i][w - weight[i]] + value[i], dp[i][w]);
                } else {
                    // 選択できない場合、それまでと値は変わらない
                    dp[i + 1][w] = dp[i][w];
                }
            }
        }
        
        System.out.println(dp[n][W]);
    }
}
