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
        // 初期化
        for (int w = 0; w <= W; w++) {
            dp[0][w] = 0;
        }
        
        // 考慮する品物を1つずつ増やして考える
        for (int i = 0; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                // 品物iを選択できる(重量[i]がw以下)
                if (w - weight[i] >= 0) {
                    // 選択できる場合、選択する方/しない方の最善を選ぶ
                    // (選択するには重量[i]分少ないとこからなら遷移できる)
                    dp[i + 1][w] = Math.max(dp[i][w - weight[i]] + value[i], dp[i][w]);
                } else {
                    // 選択できない場合、品物i-1の時と変わらない
                    dp[i + 1][w] = dp[i][w];
                }
            }
        }
        // for (int i = 0; i < n+1; i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        
        // ans: 品物nまでなめた時の、重量Wの最大価値
        System.out.println(dp[n][W]);
    }
}
