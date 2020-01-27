import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int range = sc.nextInt();
        int atk = sc.nextInt();
        
        range = 2 * range;
        
        // 座標順に整列
        int[][] array = new int[n][2];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int hp = sc.nextInt();
            array[i][0] = x;
            array[i][1] = hp;
        }
        Arrays.sort(array, (a, b) -> Integer.compare(a[0], b[0]));
        
        // [{x, y}, ...] 座標xまでyのダメージを食らうというキュー
        Queue<int[]> que = new ArrayDeque<int[]>();
        
        long totalBombs = 0;
        long nowDamage = 0;
        for (int i = 0; i < n; i++) {
            int x = array[i][0];
            int hp = array[i][1];
            
            // xに当たらない爆風のデータは削除する
            while (!que.isEmpty()) {
                int[] ary = que.peek();
                
                if (x > ary[0]) {
                    // 現在受けるダメージからも引く
                    nowDamage -= ary[1];
                    
                    que.poll();
                } else {
                    break;
                }
            }
            
            // 現在キューに溜まったダメージを受ける
            hp -= nowDamage;
            
            // 当座標のHPが残っている場合、爆弾を使う
            if (hp > 0) {
                // 残HPを0にするのに必要な爆弾数を数える
                int needBombs = hp / atk;
                if (hp % atk != 0) {
                    needBombs++;
                }
                
                totalBombs += needBombs;
                
                // 現在受けるダメージを更新する
                int damage = atk * needBombs;
                nowDamage += damage;
                
                // 今回の爆風の範囲とダメージをキューに入れる
                que.add(new int[]{x + range, damage});
            }
        }
        
        System.out.println(totalBombs);
    }
}
