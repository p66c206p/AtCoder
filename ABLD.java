import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

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
        
        // ans: 条件を満たす部分列の長さ(連続とは限らない)
        
        // 条件: どの隣り合う要素も差の絶対値がK以下
        // ex. 5 <-> 2～8 へ遷移できる (k = 3)
        // -> 絶対値K以下の右にある項に移動するときの最大の長さ
        
        // ex. 1,3,5,7,2,2,2,2,2,2,7,5,3,1 (k=2)
        // -> ans = 1,3,2,2,2,2,2,2,3,1
        
        // how:
        // dp[i] = (x番目の項まで見たときの)数字iで終わる最大の長さとする。
        // -> dp[i-k]～dp[i+k]の中で一番bestの人から遷移すればOK
        
        // 全てのi(1～3*10^5)について、2kの範囲からiに遷移させるのは到底無理
        // -> 普通のDPは無理。
        // -> 実際に遷移させなくても、「new_dp[i] = max(dp[i-k]～dp[i+k])+1」ができればOK
        // -> セグ木を使う。 (seg[i] = dp[i])
        // -> [i-k, i+k]のmaxを取得。これはlogNで可能
        
        SegmentTree<Integer> seg = new SegmentTree<>(300001, Integer::max, Integer.MIN_VALUE);
        
        // 初期の配列を取得
        for (int i = 0; i < 300001; i++) {
            seg.update(i, 0);
        }
        
        for (int i = 0; i < n; i++) {
            int num = array[i];
            
            int res = seg.query(num-k, num+k+1);
            seg.update(num, res+1);
        }
        
        int ans = seg.query(0, 300001);
        System.out.println(ans);
    }
}

class SegmentTree<T> {
    // dat: 0-indexed
    // 00000000
    // 11112222
    // 33445566
    // 789ABCDE ... a[0]-a[n]
    
    final T[] dat;              // データを格納する配列
    final int n;                // datの一番下のレベルの要素数
    final BinaryOperator<T> f;  // 二項演算子
    final T e;                  // 単位元

    // 単位元で埋めて新規作成
    public SegmentTree(int len, BinaryOperator<T> f, T e) {
        int n = 1;
        while (n < len) n <<= 1;
        this.n = n;
        this.f = f;
        this.e = e;
        this.dat = (T[]) new Object[n << 1];
        Arrays.fill(dat, e);
    }
    
    // 一点更新(a[i] = x) O(logN)
    public void update(int i, T x) {
        i += n - 1;
        dat[i] = x;
        while (i > 0) {
            i = (i - 1) / 2;  // i: parent of vl/vr
            T vl = dat[i * 2 + 1];
            T vr = dat[i * 2 + 2];
            dat[i] = f.apply(vl, vr);
        }
    }
    
    // 区間取得[a, b) O(logN)
    public T query(int a, int b) { return query_sub(a, b, 0, 0, n); }
    public T query_sub(int a, int b, int k, int l, int r) {
        if (r <= a || b <= l) {             // outside completely
            return e;
        } else if (a <= l && r <= b) {      // inside completely
            return dat[k];
        } else {
            T vl = query_sub(a, b, k * 2 + 1, l, (l + r) / 2);
            T vr = query_sub(a, b, k * 2 + 2, (l + r) / 2, r);
            return f.apply(vl, vr);
        }
    }
    
    // 使用例
    // Range Sum Query
    // SegmentTree<Integer> seg = new SegmentTree<>(n, Integer::sum, 0);
    // SegmentTree<Long> seg = new SegmentTree<>(n, Long::sum, 0L);
    
    // Range Minimum Query
    // SegmentTree<Integer> seg = new SegmentTree<>(n, Integer::min, Integer.MAX_VALUE);
    
    // Range Maximum Query
    // SegmentTree<Long> seg = new SegmentTree<>(n, Long::max, Long.MIN_VALUE);
}
