import java.util.*;

public class Main {
    static List<int[]>[] to;
    static long[] dist;
    static int n;
    
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        // to: 隣接リスト(無向)
        to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < n - 1; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            int dis = sc.nextInt();
            to[p].add(new int[]{q, dis});
            to[q].add(new int[]{p, dis});
        }
        
        // dist[i]: kからiへの最短距離
        int query = sc.nextInt();
        int k = sc.nextInt() - 1;
        dist = new long[n];
        dfs(k, 0, -1);
        
        // p→k→qの最短距離を出力
        while (query-- > 0) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            
            System.out.println(dist[p] + dist[q]);
        }
    }
    
    // 頂点pと隣接する点に自分の値+距離を配る
    public static void dfs(int p, long d, int par) {
        dist[p] = d;
        
        for (int[] q : to[p]) {
            if (q[0] == par) continue;
            dfs(q[0], d + q[1], p);
        }
    }
}
