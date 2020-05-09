import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // ans: 連続部分列の和がmod kで要素数になるi, jのパターン数
        long ans = 0;
        
        // 式変形
        // ・連続部分列の和がmod kで要素数になるi, j
        // -> (s[j+1] - s[i]) % k = j+1 - i
        // -> s[j+1]%k - s[i]%k = j+1 - i
        // -> (左辺はmod kの世界、0～k-1の値しか取らない)
        // -> (移項はmod kにならないと無理)
        // -> s[j+1]%k - (j+1)%k = s[i]%k - i%k (j+1-iはk未満)
        // -> s2[j+1] = s2[i] (j+1-iはk未満) のパターン数
        
        // s: 累積和 (mod k)
        long[] s = new long[n+1];
        for (int i = 0; i < n; i++) {
            s[i+1] = (s[i] + array[i]) % k;
        }
        // System.out.println(Arrays.toString(s));
        
        // s2: 累積和 - 要素番号 (mod k)
        long[] s2 = new long[n+1];
        for (int i = 0; i <= n; i++) {
            s2[i] = (s[i] - i) % k;
            if (s2[i] < 0) s2[i] += k;
        }
        // System.out.println(Arrays.toString(s2));
        
        // i, jのパターン数を数え上げる
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        for (int i = 0; i <= n; i++) {
            // 「条件:j+1-iはk未満」
            // -> 区間の長さk-1分までしか見れない
            // -> k個前の要素は随時捨てる
            if (i - k >= 0) {
                long outkey = s2[i - k];
                int val = map.getOrDefault(outkey, 0);
                map.put(outkey, --val);
            }
            
            // i-k番目を捨ててから要素iを見る
            long key = s2[i];
            int cnt = map.getOrDefault(key, 0);
            
            ans += cnt;
            map.put(key, ++cnt);
        }
        System.out.println(ans);
    }
}
