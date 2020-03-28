import java.util.*;

public class Main {
    static int INF = 1001001009;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt() - 1;
        int y = sc.nextInt() - 1;
        
        // to: 隣接リスト(無向)
        List<int[]>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<int[]>();
        }
        
        // (i, i+1)に辺を引く
        for (int i = 0; i < n - 1; i++) {
            to[i].add(new int[]{i + 1, 1});
            to[i + 1].add(new int[]{i, 1});
        }
        
        // (x, y)に辺を引く
        to[x].add(new int[]{y, 1});
        to[y].add(new int[]{x, 1});
        
        // ans[i]: 2点の距離がiになる個数
        int[] ans = new int[n];
        
        // dist: startからの最短距離
        int[] dist = new int[n];
        // 点iをスタートとしてダイクストラ法を行う
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist, INF);
            dist[i] = 0;
            
            // ダイクストラ法: BFSで距離を配る。
            // (キューの先頭の点はそう来る他最短経路がないので最短距離が確定する)
            PriorityQueue<Point> que = new PriorityQueue<>();
            que.add(new Point(i, 0));
            while (!que.isEmpty()) {
                Point cur = que.poll();
                int p = cur.name;
                int dis = cur.dist;
                
                // distより大きいdisを持つ点からは配らせない
                if (dis > dist[p]) continue;
                
                // 子に「自分の距離 + p→qの距離」を配る
                for (int[] qData : to[p]) {
                    int q = qData[0];
                    int PtoQ = qData[1];
                    
                    // 最短距離が更新される場合だけキューに入れる
                    if (dis + PtoQ < dist[q]) {
                        dist[q] = dis + PtoQ;
                        que.add(new Point(q, dist[q]));
                    }
                }
            }
            
            for (int j = i; j < n; j++) {
                ans[dist[j]]++;
            }
        }
        for (int i = 1; i < n; i++) {
            System.out.println(ans[i]);
        }
    }
}
    
class Point implements Comparable<Point> {
    int name;
    int dist;
     
    Point(int name, int dist) {
        this.name = name;
        this.dist = dist;
    }
    
    @Override
    public int compareTo(Point o) {
        return this.dist - o.dist;    //重みの小さい順
    }
}
