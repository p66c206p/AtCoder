import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[] start = new int[m];
        int[] end = new int[m];
        for(int i = 0; i < m; i++) {
            start[i] = sc.nextInt() - 1;
            end[i] = sc.nextInt() - 1;
        }
        
        // imos法: 累積和の区間版
        // sum[i]: 時刻iで放送中の番組数
        int[] sum = new int[n+1];
        for (int i = 0; i < m; i++) {
            // 時刻start[i]で放送番組が1増える
            // 時刻end[i]+1で放送番組が1減る
            sum[start[i]]++;
            sum[end[i] + 1]--;
        }
        for (int i = 0; i < n; i++) {
            sum[i + 1] += sum[i];
        }
        
        for (int i = 0; i < n; i++) {
            System.out.print(sum[i] % 2);
        }
        System.out.println();
        
    }
}
