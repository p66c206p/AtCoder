import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.io.PrintWriter;

public class Main {
    static int SIZE = 500001;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // ans[i]: 各クエリ[l, r]で、区間内の値の種類数
        // -> ans = r - l + 1 - 重複する個数
        // (重複する個数は矢印を張れば数えられる)
        
        // 重複の数え方:
        // -> 同じ値同士に矢印"←"を張る(下のlast_app参照)
        // -> 区間内に矢印の先も元も入っているものを数えればOK
        
        // この図がすべて:
        // -> https://youtu.be/h0MGG8rxrYc?t=8175
        
        // l, r: クエリの区間(0-indexed)
        int[] l = new int[q];
        int[] r = new int[q];
        for (int i = 0; i < q; i++) {
            l[i] = sc.nextInt() - 1;
            r[i] = sc.nextInt() - 1;
        }
        
        // queries[l]: 要素が{lに向かう矢印の元, 出力する順番}のリスト
        List<int[]>[] queries = new List[n];
        for (int i = 0; i < n; i++) {
            queries[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < q; i++) {
            queries[l[i]].add(new int[]{r[i], i});
        }
        
        // rights[l]: 「lに向かう矢印の元」の集合
        // ex. {1, 4, 1, 4, 2, 1, 3, 5, 6}
        // ->  rights[0] = {2, 5}
        List<Integer>[] rights = new List[n];
        for (int i = 0; i < n; i++) {
            rights[i] = new ArrayList<Integer>();
        }
        // last_app[i]: a[i]の値が、lastに出たidx
        // ex. { 1, 4, 1, 4, 2, 1, 3, 5, 6}
        // ->  {-1,-1, 0, 1,-1, 2,-1,-1,-1}
        int[] last_app = new int[SIZE];
        Arrays.fill(last_app, -1);
        for (int i = 0; i < n; i++) {
            int num = array[i];
            if (last_app[num] != -1) {
                rights[last_app[num]].add(i);
            }
            last_app[num] = i;
        }
        
        BIT bit = new BIT(SIZE);
        
        // idxを右からなめていく
        int[] ans = new int[q];
        for (int i = n - 1; i >= 0; i--) {
            // iに向けられた矢印を追加する
            for (Integer right : rights[i]) {
                bit.add(right, 1);
            }
            
            // 現在BITに入ってる矢印の中で、
            // 矢印の元がright以下のものが区間内の重複分。
            for (int[] query : queries[i]) {
                int left = i;
                int right = query[0];
                int q_idx = query[1];
                ans[q_idx] = right - left + 1 - bit.sum(right);
            }
        }
      
        PrintWriter out = new PrintWriter(System.out);
        for (Integer a : ans) {
            out.println(a);
        }
        out.flush();
        // for (Integer a : ans) {      // System.out.printlnだとTLEした
        //     System.out.println(a);
        // }
    }
}

class BIT {
    int n;
    int[] d;

    // 単位元で埋めて新規作成
    public BIT(int n) {
        this.n = n;
        this.d = new int[n + 1];
    }
    
    public void add(int i, int x) {
        for (i++; i <= n; i += i&-i) {
            d[i] += x;
        }
    }
    
    public int sum(int i) {
        int x = 0;
        for (i++; i > 0; i -= i&-i) {
            x += d[i];
        }
        return x;
    }
    
    public int sum(int l, int r) {
        return sum(r-1) - sum(l-1);
    }
}
