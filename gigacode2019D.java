import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        long k = sc.nextLong();
        long v = sc.nextLong();
        
        // 入力
        long[][] array = new long[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                array[i][j] = sc.nextLong();
            }
        }
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                array[i][j] += k;
            }
        }
        
        // cumsum: 累積和
        long[][] cumsum = new long[h+1][w+1];
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= w; j++) {
                cumsum[i][j] = array[i-1][j-1] + cumsum[i-1][j] + cumsum[i][j-1] - cumsum[i-1][j-1];
            }
        }
        // for (long[] c : cumsum) System.out.println(Arrays.toString(c));
        
        // 計算
        // 全探索(長方形の角の4点)
        // ans: costが制約を満たす長方形の最大面積
        int ans = 0;
        for (int U = 0; U < h; U++) {
            for (int L = 0; L < w; L++) {
                for (int D = U+1; D <= h; D++) {
                    for (int R = L+1; R <= w; R++) {
                        long cost = calc(cumsum, L, R, U, D);
                        
                        if (cost <= v) {
                            int area = (D - U) * (R - L);
                            ans = Math.max(ans, area);
                        }
                    }
                }
            }
        }
        
        // 出力
        System.out.println(ans);
    }
    
    public static long calc(long[][] cumsum, int L, int R, int U, int D) {
        // score: 右下 - 左下 - 右上 + 左上 (包除原理)
        long score = cumsum[D][R] - cumsum[D][L] - cumsum[U][R] + cumsum[U][L];
        return score;
    }
}
