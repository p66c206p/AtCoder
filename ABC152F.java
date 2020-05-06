import java.util.*;

public class Main {
    static int n;
    static List<int[]>[] to;
    static List<Integer> path;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        // ans: m個のパスが、いずれもどこかの辺が黒く塗られているパターン数
        long ans = 0;
        
        // 辺の全探索だとO(2^50)
        // -> 包除原理で分けて考える
        
        // 条件を満たさない = どの辺も白く塗る
        // -> 各「条件を満たさない」を全て満たさないならどの条件も満たすことになる
        // -> ans = 集合AにもBにもCにも...属さない集合
        // -> = 全体 - (各集合の和集合)
        // -> = + (f()) - (f(A)+f(B)+...) + (f(A,B)+f(A,C)+...) - (f(A,B,C)+f(A,B,D)+...) + ... - ...
        // -> = f()の中が偶数なら足し、奇数なら引くを全てのパターンする
        
        // to: 隣接リスト(無向)
        // 辺のidも記録する
        to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < n - 1; i++) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            int id = i;
            to[p].add(new int[]{q, id});
            to[q].add(new int[]{p, id});
        }
        
        // bitset[i]: i番目の制約
        // ex. 1100101 -> 辺id0,2,5,6を白く塗る
        int m = sc.nextInt();
        long[] bitset = new long[m];
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt() - 1;
            int end = sc.nextInt() - 1;
            
            path = new ArrayList<Integer>();
            dfs(start, end, -1);
            for (Integer id : path) {
                // (id)bit目を立てる
                bitset[i] |= 1L<<id;
            }
        }
        
        // 制約のパターン(上の集合の組み合わせ)をbit全探索
        // [00000]～[11111]
        for (int bit = 0; bit < 1<<(m); bit++) {
            // mask: どの辺を白く塗るか
            long mask = 0;
            
            // bitの各桁をなめる
            for (int d = 0; d < m; d++) {
                // 桁dの値が1なら制約[d]をmaskに採用
                if ((bit & (1<<d)) != 0) {
                    mask |= bitset[d];
                }
            }
            
            // count1ofmask: 白く塗る辺の数
            // count1ofbit: 制約の数
            int count1ofmask = Long.bitCount(mask);
            int count1ofbit = Integer.bitCount(bit);
            
            if (count1ofbit % 2 == 0) {
                ans += (long)Math.pow(2, (n-1) - count1ofmask);
            } else {
                ans -= (long)Math.pow(2, (n-1) - count1ofmask);
            }
            // System.out.println(count1ofmask + " " + ans);
        }
        System.out.println(ans);
    }
    
    // 最短経路を得る 
    public static boolean dfs(int p, int end, int par) {
        // 終了条件
        if (p == end) {
            return true;
        }
        
        for (int[] qData : to[p]) {
            int q = qData[0];
            int id = qData[1];
            if (q == par) continue;
            
            if (dfs(q, end, p)) {
                path.add(id);
                return true;
            }
        }
        
        return false;
    }
}
