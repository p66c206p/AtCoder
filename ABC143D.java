import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        Arrays.sort(array);
        
        int answer = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = array[i] + array[j];
                int a = lowerBound(array, sum) - 1;
                answer += a - j;
            }
        }
        System.out.println(answer);
    }
    
    public static int lowerBound(int[] array, int target) {
        // 初めてのtarget以上のindexを返す
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
        // 初めてのtarget超過のindexを返す
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
