import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        // to: 隣接リスト(無向)
        List<int[]>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < n - 1; i++) {
            int p = sc.nextInt()-1;
            int q = sc.nextInt()-1;
            int dis = sc.nextInt();
            to[p].add(new int[]{q, dis});
            to[q].add(new int[]{p, dis});
        }
        
        int query = sc.nextInt();
        int k = sc.nextInt()-1;
        
        // dist[i]: 点iへの最短距離
        long[] dist = new long[n];
        long INF = 1001001001001001009L;
        Arrays.fill(dist, INF);
        dist[k] = 0;
        
        // BFS: (S -> Gまでの最短経路の深さ)
        Queue<long[]> que = new ArrayDeque<long[]>();
        que.add(new long[]{k, 0});
        while (!que.isEmpty()) {
            long[] cur = que.poll();
            int p = (int)cur[0];
            long depth = cur[1];
            
            for (int[] qData : to[p]) {
                int q = qData[0];
                int PtoQ = qData[1];
                long ndepth = depth + PtoQ;
                
                if (dist[q] == INF) {
                    que.add(new long[]{(long)q, ndepth});
                    dist[q] = ndepth;
                }
            }
        }
        
        while (query-- > 0) {
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            
            long ans = dist[x] + dist[y];
            System.out.println(ans);
        }
    }
}
