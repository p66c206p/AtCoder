import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        
        // ans: 2点間距離の最大値(マンハッタン距離)
        
        // how:
        // 答えの候補は、「／」or「＼」
        // -> ／ = → + ↑ (x + y)
        // -> ＼ = → + ↓ (x - y)
        // -> 答えは「max(x+y) - min(x+y)」or「max(x-y) - min(x-y)」
        
        // 二次元座標を反時計回りに45度回転させると、
        // 縦軸が(x+y)軸、横軸が(x-y)軸になる
        // -> 答えは一番右の点から一番左の点までの距離or一番上の点から一番下の点までの距離
        
        int[] x_plus_y = new int[n];
        int[] x_minus_y = new int[n];
        for (int i = 0; i < n; i++) {
            x_plus_y[i] = x[i] + y[i];
            x_minus_y[i] = x[i] - y[i];
        }
        
        Arrays.sort(x_plus_y);
        Arrays.sort(x_minus_y);
        
        int left = x_minus_y[0];
        int right = x_minus_y[n-1];
        int up = x_plus_y[0];
        int down = x_plus_y[n-1];
        
        int ans = Math.max(right-left, down-up);
        System.out.println(ans);
    }
}
