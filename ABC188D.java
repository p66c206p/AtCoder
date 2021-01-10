import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long cost = sc.nextInt();
        
        long[] start = new long[n];
        long[] end = new long[n];
        long[] c = new long[n];
        for(int i = 0; i < n; i++) {
            start[i] = sc.nextInt()-1;
            end[i] = sc.nextInt()-1;
            c[i] = sc.nextInt();
        }
        
        // imos法: 累積和の区間版
        // sum[i]: 時刻iで放送中の番組数
        TreeMap<Long, Long> map = new TreeMap<Long, Long>();
        // int[] sum = new int[n+1];
        for (int i = 0; i < n; i++) {
            // 時刻start[i]で放送番組が1増える
            // 時刻end[i]+1で放送番組が1減る
            // sum[start[i]]++;
            // sum[end[i] + 1]--;
            
            long val = map.getOrDefault(start[i], 0L);
            map.put(start[i], val + c[i]);
            val = map.getOrDefault(end[i]+1, 0L);
            map.put(end[i]+1, val - c[i]);
        }
        // System.out.println(map.toString());
        // for (int i = 0; i < n; i++) {
        //     sum[i + 1] += sum[i];
        // }
        
        long sum = 0;
        long now = 0;
        long last = 0;
        for (Long key : map.keySet()) {
            long val = map.get(key);
            long len = key-last;
            
            if (now > cost) {
                sum += cost * len;
            } else {
                sum += now * len;
            }
            
            now += val;
            last = key;
        }
        System.out.println(sum);
    }
}
