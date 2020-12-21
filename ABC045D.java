import java.util.*;

public class Main {
    static long pow = 1000000000L;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();
        int[][] blocks = new int[n][2];
        Set<Long> blocks_set = new HashSet<Long>();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt()-1;
            int y = sc.nextInt()-1;
            blocks[i][0] = x;
            blocks[i][1] = y;
            
            blocks_set.add((long)x*pow + y);
        }
        
        // ans:
        // 10^9*10^9のboardがある。
        // 8方向+自分の9マス上に障害物がいくつあるか？
        // (障害物の数 = 10^5)
        
        // how:
        // 「8方向+自分に障害物があるマスの候補」の数はそんなにない。
        // (10^5 * 9くらい)
        // -> 各候補について「8方向+自分」に障害物がいくつあるか調べる。
        // -> 10^5 * 9*logN -> 10NlogN -> これは間に合いそう。
        
        // kouho: (1,1)～(h-2, w-2)のマスで、障害物が近くにあるマス
        Set<Long> kouho = new HashSet<Long>();
        for (int i = 0; i < n; i++) {
            int x = blocks[i][0];
            int y = blocks[i][1];
            
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    long nowx = x + dx;
                    long nowy = y + dy;
                    
                    if (1 <= nowx && nowx < h-1) {
                    if (1 <= nowy && nowy < w-1) {
                        kouho.add(nowx*pow + nowy);
                    }    
                    }
                }
            }
        }
        // System.out.println(kouho.toString());
        // System.out.println(kouho.size());
        
        // ans[i]: 「8方向+自分の9マスに障害物がi個ある」マスの数
        long[] ans = new long[10];
        ans[0] = (long)(h-2)*(w-2) - kouho.size();
        for (long tmp : kouho) {
            long x = tmp / pow;
            long y = tmp % pow;
            
            int count = 0;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    long nowx = x + dx;
                    long nowy = y + dy;
                    
                    if (blocks_set.contains(nowx*pow + nowy)) {
                        count++;
                    }
                }
            }
            
            ans[count]++;
        }
        
        // System.out.println(Arrays.toString(ans));
        for (long an : ans) {
            System.out.println(an);
        }
    }
}
