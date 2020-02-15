import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        char c[] = str.toCharArray();
        
        // dp[i][j]: i文字目からとj文字目からの共通文字列の長さ
        // 後ろから考えていく
        int[][] dp = new int[n + 1][n + 1];
        for (int j = n - 1; j >= 0; j--) {
            for (int i = j; i >= 0; i--) {
                // 文字が一致する場合、i, jが1つ後ろ時点の長さ+1になる
                if (c[i] == c[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        
        int max = 0; 
        for (int j = n - 1; j >= 0; j--) {
            for (int i = j; i >= 0; i--) {
                // 文字列の重なりを許さないので、
                // ababa, (0, 2) => 本来3だがj-iでカット
                max = Math.max(max, (Math.min(dp[i][j], j - i)));
            }
        }
        
        System.out.println(max);
    }
}
