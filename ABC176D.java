import java.util.*;

public class Main {
    static int INF = 1001001009;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        
        // ans: 迷路上を下記の2種類の移動でs -> gへ行く最短コスト
        // 移動A: 上下左右に+0で行ける
        // 移動B: 周囲25マスに+1で行ける
        int ans = 0;
        
        // start, goal
        int sh = sc.nextInt()-1;
        int sw = sc.nextInt()-1;
        int gh = sc.nextInt()-1;
        int gw = sc.nextInt()-1;
        
        // board[i][j]: 迷路
        char[][] board = new char[h][w];
        for (int i = 0; i < h; i++) {
            String str = sc.next();
            for (int j = 0; j < w; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        
        // 上下左右
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        
        // visited[i][j]: 当該マスを既に通ったか否か
        boolean[][] visited = new boolean[h][w];
        
        // dist: startからの最短距離
        int[][] dist = new int[h][w];
        for (int[] aaaa : dist) {
            Arrays.fill(aaaa, INF);
        }
        dist[sh][sw] = 0;
        
        // ダイクストラ法: BFSで距離を配る。
        // (キューの先頭の点はそう来る他最短経路がないので最短距離が確定する)
        PriorityQueue<Point> que = new PriorityQueue<>();
        que.add(new Point(sh, sw, 0));
        while (!que.isEmpty()) {
            Point cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            int depth = cur.dist;
            visited[x][y] = true;
            
            // 移動A
            // 移動可能ならそのマスに深さ+0を配ってキューに入れる
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                int ndepth = depth + 0;
                
                // 最短距離が更新される場合だけキューに入れる
                if (0 <= nx && nx < h && 0 <= ny && ny < w &&
                    board[nx][ny] == '.' && !visited[nx][ny] &&
                    ndepth < dist[nx][ny]) {
                    dist[nx][ny] = ndepth;
                    que.add(new Point(nx, ny, ndepth));
                }
            }
            
            // 移動B
            // 移動可能ならそのマスに深さ+1を配ってキューに入れる
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    int nx = x + i;
                    int ny = y + j;
                    int ndepth = depth + 1;
                    
                    // 最短距離が更新される場合だけキューに入れる
                    if (0 <= nx && nx < h && 0 <= ny && ny < w &&
                        board[nx][ny] == '.' && !visited[nx][ny] &&
                        ndepth < dist[nx][ny]) {
                        dist[nx][ny] = ndepth;
                        que.add(new Point(nx, ny, ndepth));
                    }
                }
            }
        }
        
        ans = dist[gh][gw];
        if (ans == INF) ans = -1;
        System.out.println(ans);
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;
    int dist;
     
    Point(int x, int y, int dist) {
        this.x = x;
        this.y = y;
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
