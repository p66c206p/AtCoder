import java.util.*;

public class Main {
    static int[] array;
    static int n;
    static int m;
    static int x;
    static int[] c;
    static int[][] a;
    static int INF = 1000000000;
    static int ans = INF;
    
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        
        // array: 長さnのbit列
        array = new int[n];
        
        // 全探索する時のデータを取得
        c = new int[n];
        a = new int[n][m];
        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt();
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        
        // arrayのパターンを全探索
        // ex.[1, 1, 1], [1, 1, 2], ... , [m, m, m]
        dfs(0, 0);
        
        // ans: costの最小値
        if (ans == INF) ans = -1;
        System.out.println(ans);
    }
    
    public static void dfs(int index, int now) {
        // 終了条件
        if (index == n) {
            // できあがったarrayで計算
            int cost = 0;
            int[] b = new int[m];
            for (int i = 0; i < n; i++) {
                if (array[i] == 1) {
                    cost += c[i];
                    for (int j = 0; j < m; j++) {
                        b[j] += a[i][j];
                    }
                }
            }
            
            for (int i = 0; i < m; i++) {
                if (b[i] < x) {
                    return;
                }
            }
            ans = Math.min(ans, cost);
            return;
        }
        
        // 次項の取る値: 0 or 1
        for (int i = 0; i < 2; i++) {
            array[index] = i;
            dfs(index + 1, i);
        }
    }
}
