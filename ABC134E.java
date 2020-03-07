import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<Integer>();
        
        // i番目の値に対して、どの席よりも値が小さいなら、その値の席を新規に追加する。
        // そうでない場合、自分の値に最も近い席の値を自分の値に書き換える。
        // ex. {2, 1, 4, 5, 3} =>
        // 1: {2, 4, 5}
        // 2: {1, 3} => ans = 2;
        while (n-- > 0) {
            int value = sc.nextInt() * (-1);
            int index = upperBound(list, value);
            if (list.size() == index) {
                list.add(value);
            } else {
                list.set(index, value);
            }
        }
        
        // ans: 席の数 
        System.out.println(list.size());
    }
    
    public static int upperBound(List<Integer> list, int target) {
        // 初めてのtarget超過のindexを返す
        // {1, 3, 3, 7} target: 3 -> 3
        
        int left = 0;
        int right = list.size();
        
        while (left < right) {
            int center = (left + right) / 2;
            if (list.get(center) <= target) {
                left = center + 1;
            } else {
                right = center;
            }
        }
        
        return left;
    }
}
