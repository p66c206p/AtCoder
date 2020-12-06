import java.util.*;

public class Main {
    static double[][][] dp;
    static boolean[][][] already;
    static int n;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        // c1, c2, c3: 1貫, 2貫, 3貫の皿の数
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        for (int i = 0; i < n; i++) {
            int tmp = sc.nextInt();
            if (tmp == 1) c1++;
            if (tmp == 2) c2++;
            if (tmp == 3) c3++;
        }
        
        // dp[i][j][k]: 状態(i,j,k)での操作回数の期待値(期待値DP)
        dp = new double[301][301][301];
        already = new boolean[301][301][301];
        
        // 操作:
        // n枚の皿から等確率で1枚選ぶ。
        // 寿司があるなら1貫食べる。
        // どの皿も0貫になったら終了。
        // -> (1貫の枚数,2貫の枚数,3貫の枚数)で状態を持つ。
        
        double ans = rec(c1, c2, c3);
        System.out.println(ans);
    }
    
    public static double rec(int i, int j, int k) {
        // 終端条件
        if (i == 0 && j == 0 && k == 0) return 0.0;
        
        // メモ済なら再帰不要
        if (already[i][j][k]) return dp[i][j][k];
        already[i][j][k] = true;
        
        // 遷移:
        // (i,j,k) =
        // -> (i-1,j,k) * iを選ぶ確率
        //    (i+1,j-1,k) * jを選ぶ確率
        //    (i,j+1,k-1) * kを選ぶ確率
        //    (i,j,k) * i,j,k以外を選ぶ確率
        
        // 移項すると:
        // (i,j,k) = 
        // -> (i-1,j,k) * iを選ぶ確率 / (1 - i,j,k以外を選ぶ確率)
        //    (i+1,j-1,k) * jを選ぶ確率 / (1 - i,j,k以外を選ぶ確率)
        //    (i,j+1,k-1) * kを選ぶ確率 / (1 - i,j,k以外を選ぶ確率)
        //    i,j,k以外を選ぶ確率 / (1 - i,j,k以外を選ぶ確率)
        
        double res = 0;
        if (i-1 >= 0) res += (double)(i) / n * (rec(i-1, j, k) + 1) / (1 - (double)(n-i-j-k) / n);
        if (j-1 >= 0) res += (double)(j) / n * (rec(i+1, j-1, k) + 1) / (1 - (double)(n-i-j-k) / n);
        if (k-1 >= 0) res += (double)(k) / n * (rec(i, j+1, k-1) + 1) / (1 - (double)(n-i-j-k) / n);
        res += (double)(n-i-j-k) / n / (1 - (double)(n-i-j-k) / n);
        
        // メモ化しながらリターン
        return dp[i][j][k] = res;
    }
}
