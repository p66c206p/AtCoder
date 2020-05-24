import java.util.*;

public class Main {
    static int INF = 1001001009;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int start = sc.nextInt() - 1;
        int goal = sc.nextInt() - 1;
        
        // to: 隣接リスト(有向)
        List<int[]>[] to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < m; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            int dis = sc.nextInt();
            to[p].add(new int[]{q, dis});
        }
        
        // dist: startからの最短距離
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        // ダイクストラ法: BFSで距離を配る。
        // (キューの先頭の点はそう来る他最短経路がないので最短距離が確定する)
        PriorityQueue<Point> que = new PriorityQueue<>();
        que.add(new Point(start, 0));
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
        System.out.println(dist[goal]);
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
    public int compareTo(Point o) { // longでもint
        //重みの小さい順
        if (this.dist < o.dist) {
            return -1;
        } 
        return 1;
    }
}
