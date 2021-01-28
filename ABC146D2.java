import java.util.*;

public class Main {
    static List<Integer>[] to;
    static int[] edge_idxes;
    static int[] colors;
    static int count;
    static int n;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        // to: 隣接リスト(無向)
        to = new List[n];
        edge_idxes = new int[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            to[p].add(q);
            to[q].add(p);
            edge_idxes[q] = i;
        }
        
        // colors[i]: i番目の辺の値
        colors = new int[n-1];
        
        dfs(0, 0, -1);
        
        System.out.println(count);
        for (int color : colors) {
            System.out.println(color);
        }
    }
    
    // dfs: 上の辺と被らないように、下の辺に値を配る(辺は点で管理)
    public static void dfs(int p, int val, int par) {
        int color = 1;
        for (Integer q : to[p]) {
            if (q == par) continue;
            
            if (color == val) color++;
            colors[edge_idxes[q]] = color;
            count = Math.max(count, color);
            
            dfs(q, color, p);
            color++;
        }
    }
}
