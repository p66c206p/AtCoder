import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int n = sc.nextInt();
        
        int[][] array = new int[r+1][c+1];
        for (int i = 0; i < n; i++) {
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;
            int val = sc.nextInt();
            array[row][col] = val;
        }
        // for (int[] a : array) {
        //     System.out.println(Arrays.toString(a));
        // }
        
        // ans: board上を0,0からR,Cまで右か下に進みながら
        //      アイテムを拾った時の価値の総和の最大値
        // (ただし、1行では3個までしかアイテムを拾えない)
        
        long[][][] dp = new long[r+1][c+1][4];
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                for (int k = 0; k < 4; k++) {
                    // 取って右に行く
                    if (j+1 <= c && k != 3) {
                        dp[i][j+1][k+1] = Math.max(dp[i][j+1][k+1], dp[i][j][k] + array[i][j]);
                    }
                    // 取らずに右に行く
                    if (j+1 <= c) {
                        dp[i][j+1][k] = Math.max(dp[i][j+1][k], dp[i][j][k]);
                    }
                    // 取って下に行く (行が変われば今持ってるカウントkはリセット)
                    if (i+1 <= r && k != 3) {
                        dp[i+1][j][0] = Math.max(dp[i+1][j][0], dp[i][j][k] + array[i][j]);
                    }
                    // 取らずに下に行く (行が変われば今持ってるカウントkはリセット)
                    if (i+1 <= r) {
                        dp[i+1][j][0] = Math.max(dp[i+1][j][0], dp[i][j][k]);
                    }
                }
            }
        }
        // for (long[][] a : dp) {
        //     for (long[] b : a) {
        //         System.out.println(Arrays.toString(b));
        //     }
        // }
        // System.out.println(dp[1][5][3]);
        
        long ans = 0;
        for (int i = 0; i < 4; i++) {
            ans = Math.max(ans, dp[r][c][i]);
        }
        System.out.println(ans);
    }
}
