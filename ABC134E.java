import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        
        // process:
        // 次のルールで、n個の値を集合に追加する。
        // rule: 自分より小さい値がいれば、自分に最も近い値を自分に書き換える。
        //       いない場合は、誰も書き換えず自分を追加する。
        
        // ans: 集合の要素数
        // ex. {2, 1, 4, 5, 3} =>
        // {2} -> {1,2} -> {1,4} -> {1,5} -> {3,5} ans = 2;
        
        while (n-- > 0) {
            int num = sc.nextInt();
            
            if (map.lowerKey(num) == null) {
                int val = map.getOrDefault(num, 0);
                map.put(num, ++val);
            } else {
                int key = map.lowerKey(num);
                int val = map.get(key);
                if (val == 1) {
                    map.remove(key);
                } else {
                    map.put(key, --val);
                }
                val = map.getOrDefault(num, 0);
                map.put(num, ++val);
            }
        }
        
        // TreeMapなので、集合の要素数はvalの総和
        int ans = 0;
        for (Integer key : map.keySet()) {
            int val = map.get(key);
            ans += val;
        }
        System.out.println(ans);
    }
}
