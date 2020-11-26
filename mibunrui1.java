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
        
        // dist[i]: 点iへの最短距離
        dist = new long[n];
        
        // 木の直径d、端点s, tを求める
        int s = 0;
        int t = 0;
        long d = -1;
        
        // 1. 任意の点から最遠点sを求める
        dfs(0, 0, -1);
        for (int i = 0; i < n; i++) {
            if (dist[i] > d) {
                d = dist[i];
                s = i;
            }
        }
        
        // 2. sから最遠点tを求める
        dist = new long[n];
        dfs(s, 0, -1);
        d = -1;
        for (int i = 0; i < n; i++) {
            if (dist[i] > d) {
                d = dist[i];
                t = i;
            }
        }
        
        System.out.println(d + " " + (s+1) + " " + (t+1));
    }
    
    // 子qに、「pまでの距離+qへの距離」を配る
    public static void dfs(int p, long dis, int par) {
        dist[p] = dis;
        
        for (int[] qData : to[p]) {
            int q = qData[0];
            int PtoQ = qData[1];
            if (q == par) continue;
            
            dfs(q, dis + PtoQ, p);
        }
    }
}
