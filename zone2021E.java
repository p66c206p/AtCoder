import java.util.*;

public class Main {
    static int INF = 1001001009;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        
        // ans: 0,0からh,wに行く最短距離
        
        // start, goal
        int sh = 0;
        int sw = 0;
        int gh = h-1;
        int gw = w-1;
        
        int[][][] a = new int[h][w-1][2];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w-1; j++) {
                a[i][j][0] = a[i][j][1] = sc.nextInt();
            }
        }
        int[][][] b = new int[h-1][w][2];
        for (int i = 0; i < h-1; i++) {
            for (int j = 0; j < w; j++) {
                b[i][j][0] = b[i][j][1] = sc.nextInt();
            }
        }
        
        // dist: startからの最短距離
        int[][][] dist = new int[h][w][2];
        for (int[][] aaaa : dist) {
            for (int[] aaa : aaaa) {
                Arrays.fill(aaa, INF);
            }
        }
        dist[sh][sw][0] = 0;
        
        // ダイクストラ法: BFSで距離を配る。
        // (キューの先頭の点はそう来る他最短経路がないので最短距離が確定する)
        PriorityQueue<Point> que = new PriorityQueue<>();
        que.add(new Point(sh, sw, 0, 0));
        while (!que.isEmpty()) {
            Point cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            int z = cur.z;
            int depth = cur.dist;
            
            // distより大きいdisを持つ点からは配らせない
            if (depth > dist[x][y][z]) continue;
            
            // // 確定したpとdis
            // System.out.println(p + " " + dis);
            // System.out.println(x + " " + y + " " + z);
            
            if (z == 0) {
                // 右
                if (y < w-1) {
                    int nx = x;
                    int ny = y+1;
                    int nz = z;
                    int ndepth = depth + a[x][y][z];
                    if (ndepth < dist[nx][ny][nz]) {
                        dist[nx][ny][nz] = ndepth;
                        que.add(new Point(nx, ny, nz, ndepth));
                    }
                }
                
                // 左
                if (y > 0) {
                    int nx = x;
                    int ny = y-1;
                    int nz = z;
                    int ndepth = depth + a[nx][ny][nz];
                    if (ndepth < dist[nx][ny][nz]) {
                        dist[nx][ny][nz] = ndepth;
                        que.add(new Point(nx, ny, nz, ndepth));
                    }
                }
                
                // 下
                if (x < h-1) {
                    int nx = x+1;
                    int ny = y;
                    int nz = z;
                    int ndepth = depth + b[x][y][z];
                    if (ndepth < dist[nx][ny][nz]) {
                        dist[nx][ny][nz] = ndepth;
                        que.add(new Point(nx, ny, nz, ndepth));
                    }
                }
                
                // 浮かび上がる
                if (z == 0) {
                    int nx = x;
                    int ny = y;
                    int nz = z+1;
                    int ndepth = depth + 1;
                    if (ndepth < dist[nx][ny][nz]) {
                        dist[nx][ny][nz] = ndepth;
                        que.add(new Point(nx, ny, nz, ndepth));
                    }
                }
            } else {
                // 降りる
                if (true) {
                    int nx = x;
                    int ny = y;
                    int nz = z-1;
                    int ndepth = depth;
                    if (ndepth < dist[nx][ny][nz]) {
                        dist[nx][ny][nz] = ndepth;
                        que.add(new Point(nx, ny, nz, ndepth));
                    }
                }
                
                // 上
                if (x > 0) {
                    int nx = x-1;
                    int ny = y;
                    int nz = z;
                    int ndepth = depth + 1;
                    if (ndepth < dist[nx][ny][nz]) {
                        dist[nx][ny][nz] = ndepth;
                        que.add(new Point(nx, ny, nz, ndepth));
                    }
                }
            }
        }
        
        int ans = Math.min(dist[gh][gw][0], dist[gh][gw][1]);
        System.out.println(ans);
    }
}

class Point implements Comparable<Point> {
    int x;
    int y;
    int z;
    int dist;
     
    Point(int x, int y, int z, int dist) {
        this.x = x;
        this.y = y;
        this.z = z;
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
