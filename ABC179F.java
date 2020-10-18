import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        
        long black = (long)(n-2) * (n-2);
        
        // query:
        // 1. board上で、↓ or →の矢印を壁 or 矢印にぶつかるまで引く。
        // 2. 引けた分だけオセロの黒いコマをひっくり返せる。
        
        // how:
        // どこまで引けるかは「自分の直前のkeyのvalまで」というmapを管理する。
        
        // process:
        // 1. 自分がどこまで引けるか見て、黒いコマを減らす
        // 2. 向こうは、0～ぶつかったとこまではその分行けるところが減る。
        //    (減らない場合はmapに入れなくてよい)
        
        // down, right: ↓矢印、→矢印の集合
        TreeMap<Integer, Integer> down = new TreeMap<Integer, Integer>();
        TreeMap<Integer, Integer> right = new TreeMap<Integer, Integer>();
        
        down.put(0, n-1);
        right.put(0, n-1);
        
        while(q-- > 0) {
            int type = sc.nextInt();
            int idx = sc.nextInt()-1;
            int key = 0;
            int val = 0;
            
            if (type == 1) {
                // 黒いコマをひっくり返す
                key = down.lowerKey(idx);
                val = down.get(key);
                black -= (val-1);
                
                // 引ける量が減る場合
                if (key == 0) {
                    val = right.get(0);
                    key = down.get(0);
                    
                    // 0→valをkeyまで下にずらす
                    if (!right.containsKey(key)) {
                        right.put(key, val);
                    }
                    // 0はidxまでしか行けなくなる
                    right.put(0, idx);
                    
                    // 当該線を引く
                    down.put(idx, key);
                }
            } else {
                key = right.lowerKey(idx);
                val = right.get(key);
                black -= (val-1);
                
                if (key == 0) {
                    val = down.get(0);
                    key = right.get(0);
                    
                    // 0→valをkeyまで右にずらす
                    if (!down.containsKey(key)) {
                        down.put(key, val);
                    }
                    // 0はidxまでしか行けなくなる
                    down.put(0, idx);
                    
                    // 当該線を引く
                    right.put(idx, key);
                }
            }
            
            // System.out.println("down: " + down.toString());
            // System.out.println("right: " + right.toString());
            // System.out.println(black);
        }
        
        System.out.println(black);
    }
}
