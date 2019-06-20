import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        
        long answer = shakutori(nums, k);
        
        System.out.println(answer);
    }
    
    public static long shakutori(int[] array, long k) {
        // 条件を満たす連続部分列（区間）の数を返す
        
        long count = 0;
        int n = array.length;
        long sum = 0;
        int right = 0;
        
        for (int left = 0; left < n; left++) {
            // actionしても条件を満たさないならactionして1つ右へ
            while (right < n && sum + array[right] < k) {
                sum += array[right];
                right++;
            }
            
            // 初めて条件を満たすとこから最後列までを数え上げる
            count += (n - right);
            
            if (right == left) {
                right++;
            } else {
                sum -= array[left];
            }
        }
        
        return count;
    }
}
