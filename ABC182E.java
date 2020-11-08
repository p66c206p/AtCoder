import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        char[][] board = new char[h][w];
        for (char[] bo : board) {
            Arrays.fill(bo, ' ');
        }
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            board[a][b] = '.';
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            board[a][b] = '#';
        }
        
        // yoko
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] != '.') continue;
                
                int tmpj = j-1;
                while (true) {
                    if (tmpj < 0) break;
                    if (board[i][tmpj] != ' ') break;
                    board[i][tmpj] = 'a';
                    tmpj--;
                }
                tmpj = j+1;
                while (true) {
                    if (tmpj >= w) break;
                    if (board[i][tmpj] != ' ') break;
                    board[i][tmpj] = 'a';
                    tmpj++;
                }
            }
        }
        // tate
        for (int j = 0; j < w; j++) {
            for (int i = 0; i < h; i++) {
                if (board[i][j] != '.') continue;
                
                int tmpi = i-1;
                while (true) {
                    if (tmpi < 0) break;
                    if (board[tmpi][j] != ' ' && board[tmpi][j] != 'a') break;
                    board[tmpi][j] = 'b';
                    tmpi--;
                }
                tmpi = i+1;
                while (true) {
                    if (tmpi >= h) break;
                    if (board[tmpi][j] != ' ' && board[tmpi][j] != 'a') break;
                    board[tmpi][j] = 'b';
                    tmpi++;
                }
            }
        }
        // for (char[] bo : board) {
        //     System.out.println(Arrays.toString(bo));
        // }
        
        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] == '.' || board[i][j] == 'a' || board[i][j] == 'b') count++;
            }
        }
        
        System.out.println(count);
    }
}
