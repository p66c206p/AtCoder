import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int k = sc.nextInt();
        
        // input:   3 5 4
        //          11100
        //          10001
        //          00111
        //
        // process: boardに切り込みを入れて
        //          どの部分も'1'がk個以下であるようにする
        //
        // output:  2 (縦1,横3の2箇所に切り込みを入れる)
        
        // board[i][j]: チョコレートの模様
        char[][] board = new char[h][w];
        for (int i = 0; i < h; i++) {
            String str = sc.next();
            for (int j = 0; j < w; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        
        // bit: 横線の引き方のbit全探索
        // min: cutの最小値
        int min = (h-1)*(w-1);
        for (int bit = 0; bit < 1<<(h-1); bit++) {
            // group[i]: i行が属するグループ番号
            // bitに基づいて各行をグループ分けする
            int[] group = new int[h];
            int g = 0;
            group[0] = g;
            for (int i = 0; i < h-1; i++) {
                // bitXが1なら次のグループにする
                if ((bit & (1<<i)) != 0) {
                    g++;
                }
                group[i+1] = g;
            }
            
            // cut: 横線の本数( + 縦線の本数)
            int cut = g;
            
            // count[i]: グループiの現在の'1'の個数
            // last: 最後に引いた縦線の位置
            // ok: 1列だけでk個を超すグループが存在しないならtrue
            int[] count = new int[g+1];
            int last = 0;
            boolean ok = true;
            
            // board -> cut
            // cut += 縦線の本数
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    if (board[j][i] == '1') {
                        count[group[j]]++;
                    }
                    
                    // '1'の個数がkを超えるグループがある場合、
                    // 今の列の前でカットし、その列は数え直し
                    if (count[group[j]] > k) {
                        if (last != i) {
                            cut++;
                            Arrays.fill(count, 0);
                            last = i;
                            i--;
                            break;
                        } else {
                            ok = false;
                        }
                    }
                }
            }
            
            if (ok) {
                min = Math.min(min, cut);
            }
        }
        System.out.println(min);
    }
}
