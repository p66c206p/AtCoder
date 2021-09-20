import java.util.*;

public class Main {
    static int INF = 1001001009;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // ans: 最長増加部分列(広義) O(NlogN)
        
        // process: 配列について次の1.2.の操作をN個分左から行う O(N)
        // 1. 次の場所を二分探索で探す (O(logN))
        //  - 左からi番目の値(val)以上の初めての場所
        // 2. その場所をvalに書き換える
        // ex. 
        // {1,4,7}である時、3が来たら{1,3,7}とする
        // 何故か？ <- i番目の値はできるだけ小さい方がi+1番目以降で取れる値が増えるから。
        
        int[] lis = new int[n];
        Arrays.fill(lis, INF);
        
        for (int i = 0; i < n; i++) {
            int val = array[i];
            
            int index = upperBound(lis, val);
            lis[index] = val;
            // System.out.println(Arrays.toString(lis));
        }
        
        int length = lowerBound(lis, INF);
        System.out.println(length);
    }
    
    public static int lowerBound(int[] array, int target) {
        // target"以上の"初めての場所を返す
        // {1, 3, 3, 7} target: 3 -> 1
        
        int left = 0;
        int right = array.length;
        
        while (left < right) {
            int center = (left + right) / 2;
            if (array[center] < target) {
                left = center + 1;
            } else {
                right = center;
            }
        }
        
        return left;
    }
    
    public static int upperBound(int[] array, int target) {
        // target"より大きい"初めての場所を返す
        // {1, 3, 3, 7} target: 3 -> 3
        
        int left = 0;
        int right = array.length;
        
        while (left < right) {
            int center = (left + right) / 2;
            if (array[center] <= target) {
                left = center + 1;
            } else {
                right = center;
            }
        }
        
        return left;
    }
}
