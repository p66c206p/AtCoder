import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int x = sc.nextInt();
        int m = sc.nextInt();
        
        // ans:
        // array = {2,4,<16,256,471,620>}について、
        // 「i=0からスタートし、">"まで来たら"<"に戻る」ルールで、
        // 操作「今いるarray[i]の値を加算し、i++する」をn回したときの合計
        
        // array: a[i+1] = (a[i] * a[i]) % m
        
        // how: ρの形で考えて3パターンでそれぞれ集計する。
        
        Set<Integer> set = new HashSet<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        int now = x;
        set.add(now;
        list.add(now);
        int last = 0;
        for (long i = 0; i < n; i++) {
            now = (int)((long)now * now % m);
            if (set.contains(now)) {
                last = now;
                break;
            } else {
                set.add(now);
                list.add(now);
            }
        }
        // System.out.println(list.toString());
        
        int loop_idx = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == last) {
                loop_idx = i;
            }
        }
        
        long ans = 0;
        
        // [start, loop始点)
        for (int i = 0; i < loop_idx; i++) {
            ans += list.get(i);
        }
        n -= loop_idx;
        
        // [loop始点, loop終点] * 何周できるか
        long loop_sum = 0;
        for (int i = loop_idx; i < list.size(); i++) {
            loop_sum += list.get(i);
        }
        long len = list.size() - loop_idx;
        long block = n / len;
        ans += loop_sum * block;
        n %= len;
        
        // [loop始点, 残りの移動回数]
        for (int i = loop_idx; i < loop_idx + n; i++) {
            ans += list.get(i);
        }
        
        System.out.println(ans);
    }
}
