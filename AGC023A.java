import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // ans: 連続部分列の和が0になるi, jのパターン数
        long ans = 0;
        
        // 式変形
        // ・連続部分列の和が0になるi, j
        // -> s[j+1] - s[i] = 0
        // -> s[j+1] = s[i] のパターン数
        
        // s: 累積和
        long[] s = new long[n+1];
        for (int i = 0; i < n; i++) {
            s[i+1] = s[i] + array[i];
        }
        
        // i, jのパターン数を数え上げる
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        for (int i = 0; i <= n; i++) {
            long key = s[i];
            int cnt = map.getOrDefault(key, 0);
            
            ans += cnt;
            map.put(key, ++cnt);
        }
        System.out.println(ans);
    }
}
