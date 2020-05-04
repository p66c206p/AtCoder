import java.util.*;

public class Main {
    static int[] array;
    static int n;
    static int m;
    static int q;
    static int[] a;
    static int[] b;
    static int[] c;
    static int[] d;
    static int ans = 0;
    
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        
        array = new int[n];
        
        a = new int[q];
        b = new int[q];
        c = new int[q];
        d = new int[q];
        for (int i = 0; i < q; i++) {
            a[i] = sc.nextInt() - 1;
            b[i] = sc.nextInt() - 1;
            c[i] = sc.nextInt();
            d[i] = sc.nextInt();
        }
        
        dfs(0, 1);
        
        System.out.println(ans);
    }
    
    public static void dfs(int index, int now) {
        // 終了条件
        if (index == n) {
            int score = 0;
            for (int i = 0; i < q; i++) {
                if (array[b[i]] - array[a[i]] == c[i]) score += d[i];
            }
            ans = Math.max(ans, score);
            return;
        }
        
        // array: 全てが1以上m以下の、広義単調増加の数列
        for (int i = now; i <= m; i++) {
            array[index] = i;
            dfs(index + 1, i);
        }
    }
}
