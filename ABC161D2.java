import java.util.*;

public class Main {
    static List<Long> list;
    
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt() - 1;
        
        list = new ArrayList<Long>();
        
        // ルンルン数: (次の桁に-1～+1で行ける)
        // ex. 334, 2123, 3234566667 (0はNG)
        
        // 10桁以下のルンルン数を全探索
        for (int i = 1; i <= 9; i++) {
            dfs(i);
        }
        
        // 昇順にソートし、k番目を出力
        list.sort(Comparator.naturalOrder());
        System.out.println(list.get(k));
    }
    
    public static void dfs(long cur) {
        // 終了条件: 最大のルンルン数は10桁なので11桁以上は不要
        if (cur > 10000000000L) {
            return;
        }
        
        // リストにルンルン数を追加
        list.add(cur);
        
        // 次の桁はlast-1, last+0, last+1のパターン
        long x10 = cur * 10;
        long last = cur % 10;
        if (last != 0) {
            dfs(x10 + last - 1);
        }
        // if (0 <= last <= 9)
            dfs(x10 + last);
        if (last != 9) {
            dfs(x10 + last + 1);
        }
    }
}
