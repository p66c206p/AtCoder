import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int sx = 0;
        int sy = 0;
        int gx = 0;
        int gy = 0;
        
        // board[i][j]: 迷路
        char[][] board = new char[h][w];
        for (int i = 0; i < h; i++) {
            String str = sc.next();
            for (int j = 0; j < w; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        
        // to[a]: ワープマス'a'の存在する場所のリスト
        List<int[]>[] to = new List[26];
        boolean[] used = new boolean[26];
        for (int i = 0; i < 26; i++) {
            to[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] == 'S') {
                    sx = i;
                    sy = j;
                    continue;
                }
                if (board[i][j] == 'G') {
                    gx = i;
                    gy = j;
                    continue;
                }
                
                if (board[i][j] != '.' && board[i][j] != '#') {
                    int idx = (int)(board[i][j] - 'a');
                    to[idx].add(new int[]{i, j});
                }
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
        Queue<int[]> que = new ArrayDeque<int[]>();
        que.add(new int[]{sx, sy, 0});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0];
            int y = cur[1];
            int depth = cur[2];
            
            // 移動手段:
            // A: 上下左右の通路(コスト+1)
            // B: 共通の英小文字の場所(コスト+1)
            
            // A
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                int ndepth = depth + 1;
                
                if (0 <= nx && nx < h && 0 <= ny && ny < w &&
                    board[nx][ny] != '#' && dist[nx][ny] == INF) {
                    que.add(new int[]{nx, ny, ndepth});
                    dist[nx][ny] = ndepth;
                }
            }
            
            // B
            char type = board[x][y];
            if (type != 'S' && type != 'G' && type != '.' && type != '#') {
                int idx = (int)(type - 'a');
                if (used[idx]) continue;
                
                for (int[] qData : to[idx]) {
                    int nx = qData[0];
                    int ny = qData[1];
                    int ndepth = depth + 1;
                    
                    if (0 <= nx && nx < h && 0 <= ny && ny < w &&
                        board[nx][ny] != '#' && dist[nx][ny] == INF) {
                        que.add(new int[]{nx, ny, ndepth});
                        dist[nx][ny] = ndepth;
                        used[idx] = true;
                    }
                }
            }
        }
        
        int ans = dist[gx][gy];
        if (ans == INF) ans = -1;
        System.out.println(ans);
    }
}
