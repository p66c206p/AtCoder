import java.util.*;

public class Main {
    static List<long[]>[] to;
    static long[] dist;
    static int n;
    static long MOD = 1000000007;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        // to: 隣接リスト(無向)
        to = new List[n];
        for (int i = 0; i < n; i++) {
            to[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            long p = sc.nextInt() - 1;
            long q = sc.nextInt() - 1;
            long dis = sc.nextLong();
            to[(int)p].add(new long[]{q, dis});
            to[(int)q].add(new long[]{p, dis});
        }
        
        // ans: 木の頂点の全ペアのdistの総和
        // ただし、distは最短パス上のxor。
        
        // how:
        // dist(p,q)は、dist(p,0) ^ dist(0,q)となる。
        // (2回通ると打ち消し合うので)
        // -> 頂点0から各頂点までのxorを求める。
        // -> 「全(p,q)のペアのxorの総和を出力」問題になる。
        
        // how2:
        // xorは桁ごとに処理できる。(2^0, 2^1, 2^2...)
        // -> 各桁の値は0か1。
        // -> 答えに寄与するのは0 xor 1だけ。
        // -> sum(2^何桁目)*(0の個数)*(1の個数)が答え。
        
        // dist[i]: 0からiへの最短パス上のxor
        dist = new long[n];
        dfs(0, 0, -1);
        
        // res: distの全ペアのxorの総和
        long res = 0;
        long pow2 = 1;
        for (int i = 0; i < 60; i++) {
            long zero = 0;
            long one = 0;
            for (int j = 0; j < n; j++) {
                if (dist[j] % 2 == 0) zero++;
                else one++;
                dist[j] /= 2;
            }
            
            long tmp = (zero*one)%MOD;
            res += (tmp*pow2)%MOD;
            res %= MOD;
            pow2 = (pow2*2)%MOD;
        }
        
        System.out.println(res);
    }
    
    // DFS: 頂点kからの距離を求める
    public static void dfs(int p, long d, int par) {
        dist[p] = d;
        
        for (long[] qData : to[p]) {
            int q = (int)qData[0];
            long PtoQ = qData[1];
            if (q == par) continue;
            
            dfs(q, d ^ PtoQ, p);
        }
    }
}
