import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        
        Queue<long[]> que = new ArrayDeque<long[]>();
        que.add(new long[]{3, 1, 0, 0});
        que.add(new long[]{5, 0, 1, 0});
        que.add(new long[]{7, 0, 0, 1});
        
        // count: 3, 5, 7の3種類だけで構成されるn以下の数で、3種類全て含んでいるものの個数
        int count = 0;
        while (!que.isEmpty()) {
            long[] cur = que.poll();
            long tmp = cur[0];
            long x = cur[1];
            long y = cur[2];
            long z = cur[3];
            
            if (tmp > num) continue;
            
            // 3, 5, 7を全てを含む数はカウント 
            if (x * y * z == 1) count++;
            
            que.add(new long[]{tmp * 10 + 3, x | 1, y, z});
            que.add(new long[]{tmp * 10 + 5, x, y | 1, z});
            que.add(new long[]{tmp * 10 + 7, x, y, z | 1});
        }
        System.out.println(count);
    }
}
