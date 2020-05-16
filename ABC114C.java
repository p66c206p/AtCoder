import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        
        // que: 七五三数(候補) (全ての桁が7 or 5 or 3)
        Queue<long[]> que = new ArrayDeque<long[]>();
        
        // 3, 5, 7からBFSスタート
        // (後ろの3項は、1なら3,5,7が既に登場)
        que.add(new long[]{3, 1, 0, 0});
        que.add(new long[]{5, 0, 1, 0});
        que.add(new long[]{7, 0, 0, 1});
        
        // count: n以下の七五三数の個数
        // -> 全ての桁が7 or 5 or 3かつ、7,5,3全て登場する数
        // ex. 753, 3577735, 533333337
        int count = 0;
        while (!que.isEmpty()) {
            long[] cur = que.poll();
            long num = cur[0];
            long x = cur[1];
            long y = cur[2];
            long z = cur[3];
            
            // 終了条件: n以下を全部見た
            if (num > n) continue;
            
            // 3, 5, 7全て登場するならカウント 
            if (x * y * z == 1) count++;
            
            // 次の桁のパターンをキューに入れる x10+3, x10+5, x10+7
            que.add(new long[]{num * 10 + 3, x | 1, y, z});
            que.add(new long[]{num * 10 + 5, x, y | 1, z});
            que.add(new long[]{num * 10 + 7, x, y, z | 1});
        }
        System.out.println(count);
    }
}
