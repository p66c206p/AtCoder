import java.util.*;

public class Main {
    static int MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        String[][] table = new String[x + 2][y + 2];
        for (String[] t : table) {
            Arrays.fill(t, "#");
        }
        for (int i = 1; i <= x; i++) {
            String str = sc.next();
            for (int j = 1; j <= y; j++) {
                table[i][j] = String.valueOf(str.charAt(j - 1));
            }
        }
        
        int[][] dp = new int[x + 2][y + 2];
        dp[1][1] = 1;
        
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                if (i == 1 && j == 1) continue;
                if (table[i][j].equals("#")) continue;
                
                boolean upToDown = table[i - 1][j].equals(".");
                boolean leftToRight = table[i][j - 1].equals(".");
                
                if (upToDown && leftToRight) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                } else if (upToDown) {
                    dp[i][j] = dp[i - 1][j];
                } else if (leftToRight) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    table[i][j] = "#";
                    if (i == x && j == y) {
                        dp[i][j] = 0;
                    }
                }
            }
        }
        
        System.out.println(dp[x][y]);
    }
} 
