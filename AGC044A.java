import java.util.*;

public class Main {
    static long INF = 1001001001001001018L;
    static long n, a, b, c, d;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        // ans: 点nから点0に行くまでの最小コスト (N <= 10^18)
        // 可能な操作: 点p -> 点q (移動コスト)
        // 1. n -> n/2 (a) (ただし n%2 = 0)
        // 2. n -> n/3 (b) (ただし n%3 = 0)
        // 3. n -> n/5 (c) (ただし n%5 = 0)
        // 4. n -> n-1 (d)
        // 5. n -> n+1 (d)
        
        while (t--> 0) {
            n = sc.nextLong();
            a = sc.nextLong();
            b = sc.nextLong();
            c = sc.nextLong();
            d = sc.nextLong();
            
            long start = n;
            long goal = 1;
        
            // ダイクストラ法 O((N+M)logM)
            // 使用条件: (辺の重さバラバラ, 辺の重さが負でない)
            // ！配列ではなくmapで持つ。
            // <- 状態数が10^18もあるから。
            // <- (また、間引きできるので全状態を構える必要がないから。)
            Map<Long, Long> dist = dijkstra(start, goal);
            
            // ans: 頂点N->頂点1 + 頂点1->頂点0
            long ans = dist.get(goal) + d;
            System.out.println(ans);
        }
    }
    
    public static Map<Long, Long> dijkstra(long start, long goal) {
        // dist: startからの最短距離
        Map<Long, Long> dist = new HashMap<>();
        dist.put(start, 0L);
        
        // ダイクストラ法: BFSで距離を配る。
        // (キューの先頭の点はそう来る他最短経路がないので最短距離が確定する)
        PriorityQueue<Point> que = new PriorityQueue<>();
        que.add(new Point(start, 0L));
        while (!que.isEmpty()) {
            Point cur = que.poll();
            long p = cur.name;
            long dis = cur.dist;
            
            // distより大きいdisを持つ点からは配らせない
            if (dis > dist.getOrDefault(p, INF)) continue;
            
            if (p <= 1) continue;
            
            // // 確定したpとdis
            // System.out.println(p + " " + dis);
            
            // 子に「自分の距離 + p→qの距離」を配る
            // 最短距離が更新される場合だけキューに入れる
            long q;
            long PtoQ;
            
            // 遷移パターン: ex. 11 -> q (移動コスト)
            // (中間点はキューに入れない)
            // 1. 1まで-1ずつ移動
            // -> 11->1                     ((p-1)*d)
            // 2. 2の倍数(上下)に移動し、/2の点へ移動
            // -> 11(->10)->5, 11(->12)->6  (2の倍数までの距離*d + a)
            // 3. 3の倍数(上下)に移動し、/3の点へ移動
            // -> 11(->9)->3, 11(->12)->4   (3の倍数までの距離*d + b)
            // 4. 5の倍数(上下)に移動し、/5の点へ移動
            // -> 11(->10)->2, 11(->15)->3  (5の倍数までの距離*d + c)
            
            // ->goalのパターン
            q = 1;
            PtoQ = (p-1) * d;
            // if (dis + PtoQ < dist.getOrDefault(q, INF)) {
            if (dis / d + (p-1) < dist.getOrDefault(q, INF) / d) {  // オーバーフロー対策
                dist.put(q, dis + PtoQ);
                que.add(new Point(q, dist.get(q)));
            }
            
            // /2のパターン
            if (p % 2 == 0) {
                q = p / 2;
                PtoQ = a;
                if (dis + PtoQ < dist.getOrDefault(q, INF)) {
                    dist.put(q, dis + PtoQ);
                    que.add(new Point(q, dist.get(q)));
                }
            } else {
                q = p / 2;
                PtoQ = d * (p % 2) + a;
                if (dis + PtoQ < dist.getOrDefault(q, INF)) {
                    dist.put(q, dis + PtoQ);
                    que.add(new Point(q, dist.get(q)));
                }
                
                q = (p+1) / 2;
                PtoQ = d * (2 - (p % 2)) + a;
                if (dis + PtoQ < dist.getOrDefault(q, INF)) {
                    dist.put(q, dis + PtoQ);
                    que.add(new Point(q, dist.get(q)));
                }
            }
            
            // /3のパターン
            if (p % 3 == 0) {
                q = p / 3;
                PtoQ = b;
                if (dis + PtoQ < dist.getOrDefault(q, INF)) {
                    dist.put(q, dis + PtoQ);
                    que.add(new Point(q, dist.get(q)));
                }
            } else {
                q = p / 3;
                PtoQ = d * (p % 3) + b;
                if (dis + PtoQ < dist.getOrDefault(q, INF)) {
                    dist.put(q, dis + PtoQ);
                    que.add(new Point(q, dist.get(q)));
                }
                
                q = (p+2) / 3;
                PtoQ = d * (3 - (p % 3)) + b;
                if (dis + PtoQ < dist.getOrDefault(q, INF)) {
                    dist.put(q, dis + PtoQ);
                    que.add(new Point(q, dist.get(q)));
                }
            }
            
            // /5のパターン
            if (p % 5 == 0) {
                q = p / 5;
                PtoQ = c;
                if (dis + PtoQ < dist.getOrDefault(q, INF)) {
                    dist.put(q, dis + PtoQ);
                    que.add(new Point(q, dist.get(q)));
                }
            } else {
                q = p / 5;
                PtoQ = d * (p % 5) + c;
                if (dis + PtoQ < dist.getOrDefault(q, INF)) {
                    dist.put(q, dis + PtoQ);
                    que.add(new Point(q, dist.get(q)));
                }
                
                q = (p+4) / 5;
                PtoQ = d * (5 - (p % 5)) + c;
                if (dis + PtoQ < dist.getOrDefault(q, INF)) {
                    dist.put(q, dis + PtoQ);
                    que.add(new Point(q, dist.get(q)));
                }
            }
        }
        
        return dist;
    }
}
    
class Point implements Comparable<Point> {
    long name;
    long dist;
     
    Point(long name, long dist) {
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
