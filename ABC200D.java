import java.util.*;

public class Main {
    static int[] array;
    static int n;
    static int[] a;
    static String[] list;
    static boolean solved = false;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        n = Math.min(n, 8);
        
        // ans:
        // 数列aの部分集合S,Tであって、
        // Sの和 ≡ Tの和 (mod 200)になる組み合わせを出力
        
        // how:
        // 先頭8要素の全探索だけで256通り調べられる。
        // -> 256通りも調べれば鳩の巣原理的に同じ和のペアが絶対現れる。(mod 200なので)
        // -> 数列の長さをmin(n, 8)にしてbit全探索する。
        
        // array: 長さnのbit列
        array = new int[n];
        
        // 全探索する時のデータを取得
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        
        list = new String[200];
        Arrays.fill(list, "");
        
        // arrayのパターンを全探索
        // ex.[1, 1, 1], [1, 1, 2], ... , [m, m, m]
        dfs(0, 0);
        
        if (!solved) {
            System.out.println("No");
        }
    }
    
    public static void dfs(int index, int now) {
        // 終了条件
        if (index == n) {
            // できあがったarrayで計算
            int sum = 0;
            int bitCount = 0;
            for (int i = 0; i < n; i++) {
                if (array[i] == 1) {
                    sum += a[i];
                    sum %= 200;
                    bitCount++;
                }
            }
            if (bitCount == 0) return;
            
            if (list[sum] != "" && !solved) {
                System.out.println("Yes");
                
                // array_B
                System.out.println(list[sum]);
                
                // array_C
                List<Integer> tmp = new ArrayList<>();
                tmp.add(bitCount);
                for (int i = 0; i < n; i++) {
                    if (array[i] == 1) tmp.add(i+1);
                }
                
                String str = "";
                for (int i = 0; i < tmp.size(); i++) {
                    str += String.valueOf(tmp.get(i));
                    if (i != tmp.size()-1) str += " ";
                }
                
                System.out.println(str);
                solved = true;
            } else {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(bitCount);
                for (int i = 0; i < n; i++) {
                    if (array[i] == 1) tmp.add(i+1);
                }
                
                String str = "";
                for (int i = 0; i < tmp.size(); i++) {
                    str += String.valueOf(tmp.get(i));
                    if (i != tmp.size()-1) str += " ";
                }
                
                list[sum] = str;
            }
            return;
        }
        
        // 次項の取る値: 0 or 1
        for (int i = 0; i < 2; i++) {
            array[index] = i;
            dfs(index + 1, i);
        }
    }
}
