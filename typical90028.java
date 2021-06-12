import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = 1100;
        int m = sc.nextInt();
        
        int[] lx = new int[m];
        int[] ly = new int[m];
        int[] rx = new int[m];
        int[] ry = new int[m];
        for(int i = 0; i < m; i++) {
            lx[i] = sc.nextInt();
            ly[i] = sc.nextInt();
            rx[i] = sc.nextInt();
            ry[i] = sc.nextInt();
        }
        
        // imos法: 累積和の区間版
        // sum[i]: 時刻iで放送中の番組数
        int[][] sum = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            // 時刻start[i]で放送番組が1増える
            // 時刻end[i]+1で放送番組が1減る
            sum[lx[i]][ly[i]]++;
            sum[rx[i]][ly[i]]--;
            sum[lx[i]][ry[i]]--;
            sum[rx[i]][ry[i]]++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum[i+1][j] += sum[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j+1] += sum[i][j];
            }
        }
        
        int[] count = new int[m+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count[sum[i][j]]++;
            }
        }
        for (int i = 1; i <= m; i++) {
            System.out.println(count[i]);
        }
    }
}
