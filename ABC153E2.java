import java.util.*;

public class Main {
    static long INF = (long)1e+18;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int n = sc.nextInt();
        int V = H;
        
        int[][] array = new int[n][2];
        for (int i = 0; i < n; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            array[i][0] = v;
            array[i][1] = w;
        }
        
        // dp[i][j]: アイテムiまで見てきたときの、ダメージjの最小MP
        long[][] dp = new long[n+1][V+1];
        // 初期化
        for (long[] dps : dp) {
            Arrays.fill(dps, INF);
        }
        dp[0][0] = 0;
        
        for (int i = 0; i < n; i++) {
            int v = array[i][0];
            int w = array[i][1];
            for (int nj = 0; nj <= V; nj++) {
                for (int k = 0; k <= 1; k++) {
                    // アイテムiをk個使って状態njに来る
                    int ni = i+1;
                    int j = nj - v * k;
                    long plus = w * k;
                    
                    // カットとかcontinue
                    if (j < 0) j = 0;
                    
                    // 遷移:
                    // ni行の0~j列まではbestが確定しているので、
                    // もう1個アイテムを使うときの最善元は(ni,j)
                    if (k == 0) {
                        dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + plus);
                    } else {
                        dp[ni][nj] = Math.min(dp[ni][nj], dp[ni][j] + plus);
                    }
                }
            }
        }
        // for (long[] dps : dp) {
        //     System.out.println(Arrays.toString(dps));
        // }
        
        System.out.println(dp[n][V]);
    }
}
