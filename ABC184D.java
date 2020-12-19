import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        
        // dp[i][j][k]: 状態(i,j,k)での操作回数の期待値(期待値DP)
        double[][][] dp = new double[101][101][101];
        
        // 操作:
        // 袋にi,j,k枚の金貨,銀貨,銅貨が入っている。
        // 1枚取り出し、その種類のコインを2枚戻す。
        // (i,j,k)のどれかが100枚になったら終了。
        
        for (int i = 99; i >= a; i--) {
            for (int j = 99; j >= b; j--) {
                for (int k = 99; k >= c; k--) {
                    // 遷移先が確定していないと遷移元を確定できないので、逆順に実施
                    // 遷移元の期待値 = 遷移先へ遷移する確率 * (遷移先の期待値+1)
                    // 状態(i,j,k) -> (i+1,j,k),(i,j+1,k),(i,j,k+1) へ遷移する
                    
                    dp[i][j][k] += ((double)i / (i+j+k)) * (dp[i+1][j][k] + 1);
                    dp[i][j][k] += ((double)j / (i+j+k)) * (dp[i][j+1][k] + 1);
                    dp[i][j][k] += ((double)k / (i+j+k)) * (dp[i][j][k+1] + 1);
                }
            }
        }
        
        System.out.println(dp[a][b][c]);
    }
}
