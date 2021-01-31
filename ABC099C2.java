import java.util.*;

public class Main {
    static int INF = 1001001009;
    
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int start = 0;
        int goal = n;
        
        // ans: 点0から点nに行くまでの最小コスト
        // 可能な操作: 点p -> 点q (移動コスト)
        // 1. n -> n+1      (1)
        // 2. n -> n+6^i    (1) (ex. 0 -> 6, 36, 216, ...)
        // 3. n -> n+9^i    (1) (ex. 0 -> 9, 81, 729, ...)
        
        // to: 隣接リスト(有向)
        
        // dist: startからの最短距離
        int[] dist = new int[n+1];
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
            
            // // 確定したpとdis
            // System.out.println(p + " " + dis);
            
            // 子に「自分の距離 + p→qの距離」を配る
            // 最短距離が更新される場合だけキューに入れる
            int q;
            int PtoQ;
            
            // +1のパターン
            q = p + 1;
            PtoQ = 1;
            if (q <= n && dis + PtoQ < dist[q]) {
                dist[q] = dis + PtoQ;
                que.add(new Point(q, dist[q]));
            }
            
            // +6^iのパターン
            for (int pow = 6; pow <= n; pow *= 6) {
                q = p + pow;
                PtoQ = 1;
                if (q <= n && dis + PtoQ < dist[q]) {
                    dist[q] = dis + PtoQ;
                    que.add(new Point(q, dist[q]));
                }
            }
            
            // +9^iのパターン
            for (int pow = 9; pow <= n; pow *= 9) {
                q = p + pow;
                PtoQ = 1;
                if (q <= n && dis + PtoQ < dist[q]) {
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
