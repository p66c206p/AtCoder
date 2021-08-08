import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int sx = 0;
        int sy = 0;
        int gx = h-1;
        int gy = w-1;
        
        // ans:
        // パンチすると任意の2x2の'#'を'.'にできる。
        // 最低何回パンチが必要か？
        
        // how:
        // パンチするとは、Pの位置にコスト+1で行けるということ。
        // .PPP.
        // PPPPP
        // PPxPP
        // PPPPP
        // .PPP.
        
        // board[i][j]: 迷路
        char[][] board = new char[h][w];
        for (int i = 0; i < h; i++) {
            String str = sc.next();
            for (int j = 0; j < w; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        
        // dist[x][y]: (x,y)へ行く最短コスト
        int[][] dist = new int[h][w];
        int INF = 1001001009;
        for (int[] dis : dist) {
            Arrays.fill(dis, INF);
        }
        dist[sx][sy] = 0;
        
        // 上下左右
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        
        // BFS: (S(x,y) -> G(x,y)までの最短経路の深さ)
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.offerFirst(new int[]{sx, sy, 0});
        while (!que.isEmpty()) {
            int[] cur = que.pollFirst();
            int x = cur[0];
            int y = cur[1];
            int depth = cur[2];
            
            // 移動手段:
            // A: 上下左右の通路(コスト+0)
            // B: 自分を中心とした5x5(コスト+1)
            
            // A
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                int ndepth = depth + 0;
                
                if (0 <= nx && nx < h && 0 <= ny && ny < w &&
                    board[nx][ny] != '#' && ndepth < dist[nx][ny]) {
                    que.offerFirst(new int[]{nx, ny, ndepth});
                    dist[nx][ny] = ndepth;
                }
            }
            
            // B
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    if (i == -2 && j == -2) continue;
                    if (i == -2 && j == 2) continue;
                    if (i == 2 && j == -2) continue;
                    if (i == 2 && j == 2) continue;
                    int nx = x + i;
                    int ny = y + j;
                    int ndepth = depth + 1;
                
                    if (0 <= nx && nx < h && 0 <= ny && ny < w &&
                        ndepth < dist[nx][ny]) {
                        que.offerLast(new int[]{nx, ny, ndepth});
                        dist[nx][ny] = ndepth;
                    }
                }
            }
        }
        
        int ans = dist[gx][gy];
        if (ans == INF) ans = -1;
        System.out.println(ans);
    }
}
