import java.util.*;

public class Main {
    static long INF = 1000000000000000L;
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        int[][] ans = new int[h][w];
        
        int r = 0;
        int c = 0;
        int count = 0;
        int now = 0;
        while (true) {
            ans[r][c] = now;
            if (r % 2 == 0) {
                c++;
                if (c == w) {
                    c = w - 1;
                    r++;
                }
            } else {
                c--;
                if (c == -1) {
                    c = 0;
                    r++;
                }
            }
            
            array[now]--;
            if (array[now] == 0) now++;
            
            count++;
            if (count == h*w) break;
        }
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(ans[i][j]+1);
                
                if (j != w - 1) {
                    System.out.print(" ");
                } else {
                    System.out.println();
                }
            }
        }
    }
}
