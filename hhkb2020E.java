import java.util.*;

public class Main {
    static long MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        
        int blank = 0;
        
        // board[i][j]: 迷路
        char[][] board = new char[h][w];
        for (int i = 0; i < h; i++) {
            String str = sc.next();
            for (int j = 0; j < w; j++) {
                board[i][j] = str.charAt(j);
                
                if (board[i][j] == '.') blank++;
            }
        }
        
        // array[i][j]: (i,j)を含む直近の'#'までの'.'の数(上下左右)
        int[][] array = new int[h][w];
        
        for (int i = 0; i < h; i++) {
            int cnt = 0;
            int last_idx = 0;
            for (int j = 0; j < w; j++) {
                if (board[i][j] == '.') {
                    cnt++;
                } else {
                    for (int k = j-1; k >= last_idx; k--) {
                        array[i][k] += cnt;
                    }
                    cnt = 0;
                    last_idx = j+1;
                }
                
                if (j == w-1) {
                    for (int k = w-1; k >= last_idx; k--) {
                        array[i][k] += cnt;
                    }
                }
            }
        }
        
        for (int i = 0; i < w; i++) {
            int cnt = 0;
            int last_idx = 0;
            for (int j = 0; j < h; j++) {
                if (board[j][i] == '.') {
                    cnt++;
                } else {
                    for (int k = j-1; k >= last_idx; k--) {
                        array[k][i] += cnt;
                    }
                    cnt = 0;
                    last_idx = j+1;
                }
                
                if (j == h-1) {
                    for (int k = h-1; k >= last_idx; k--) {
                        array[k][i] += cnt;
                    }
                }
            }
        }
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (array[i][j] > 0) array[i][j]--;
            }
        }
        
        long[] nibeki = modpow(2, 2000*2000);
        
        long ans = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] == '#') continue;
                
                long tmp = nibeki[array[i][j]]-1;
                if (tmp < 0) tmp += MOD;
                long tmp2 = nibeki[blank - array[i][j]];
                
                long now = tmp * tmp2;
                now %= MOD;
                
                ans += now;
                ans %= MOD;
                // System.out.println(now);
            }
        }
        
        System.out.println(ans);
    }
    
    public static long[] modpow(long num, long n) {
        long[] res = new long[(int)n+1];
        res[0] = 1;
        for (int i = 1; i <= n; i++) {
            res[i] = res[i-1] * num;
            res[i] %= MOD;
        }
        
        return res;
    }
}
