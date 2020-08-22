import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int m = sc.nextInt();
        
        // ans: r行すべてとc列すべてにある爆弾の個数の最大値
        int ans = 0;
        
        // countr, countc: 各行各列に存在する爆弾の個数
        // setp: 爆弾の存在する点の集合
        // maxr, maxc: countr, countcの最大値
        int[] countr = new int[h];
        int[] countc = new int[w];
        Set<String> setp = new HashSet<String>();
        int maxr = 0;
        int maxc = 0;
        for (int i = 0; i < m; i++) {
            int row = sc.nextInt()-1;
            int col = sc.nextInt()-1;
            countr[row]++;
            countc[col]++;
            setp.add(row + " " + col);
            maxr = Math.max(maxr, countr[row]);
            maxc = Math.max(maxc, countc[col]);
        }
        
        // setr, setc: maxr, maxcである行、列の集合
        Set<Integer> setr = new HashSet<Integer>();
        for (int i = 0; i < h; i++) {
            if (maxr == countr[i]) setr.add(i);
        }
        Set<Integer> setc = new HashSet<Integer>();
        for (int i = 0; i < w; i++) {
            if (maxc == countc[i]) setc.add(i);
        }
        
        // maxrかつmaxcの(r, c)について全探索
        // (r, c)がsetpになければ++する
        boolean ok = false;
        for (Integer r : setr) {
            for (Integer c : setc) {
                String p = r + " " + c;
                if (!setp.contains(p)) {
                    ok = true;
                    break;
                }
            }
        }
        
        // (r, c)に爆弾があれば-1してmaxrとmaxcの重複を数えない必要がある
        // ないなら-1不要
        ans = maxr + maxc - 1;
        if (ok) ans++;
        System.out.println(ans);
    }
}
