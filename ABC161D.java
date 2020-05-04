import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        
        // que: ルンルン数(次の桁に-1～+1で行ける)
        // ex. 334, 2123, 3234566667
        Queue<Long> que = new ArrayDeque<Long>();
        
        // 1～9をキューに入れる
        for (int i = 1; i <= 9; i++) {
            que.add((long)i);
        }
        
        // count: キューから出した回数
        int count = 0;
        while (true) {
            long cur = que.poll();
            long x10 = cur * 10;
            long last = cur % 10;
            count++;
            
            // 終了条件: k番目の数が取り出された
            if (count == k) {
                System.out.println(cur);
                return;
            }
            
            // -1, 0, +1のパターンをキューに入れる
            if (last != 0) {
                que.add(x10 + last - 1);
            }
            // if (1 <= last <= 8)
                que.add(x10 + last);
            if (last != 9) {
                que.add(x10 + last + 1);
            }
        }
    }
}
