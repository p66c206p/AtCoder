import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long s = sc.nextLong();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // 半分全列挙:
        // 要素をn/2個の2グループに分けて、それぞれを全列挙し、
        // 組み合わせ方を高速に求める手法
        int n2 = n / 2;
        
        // group_aを全列挙
        long[] group_a = new long[1 << n2];
        for (int i = 0; i < (1 << n2); i++) {
            long sum = 0;
            for (int j = 0; j < n2; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += array[j];
                }
            }
            
            group_a[i] = sum;
        }
        
        // group_bを全列挙
        long[] group_b = new long[1 << (n - n2)];
        for (int i = 0; i < (1 << (n - n2)); i++) {
            long sum = 0;
            for (int j = 0; j < (n - n2); j++) {
                if ((i & (1 << j)) != 0) {
                    sum += array[j + n2];
                }
            }
            
            group_b[i] = sum;
        }
        
        // (後で二分探索するのでソート)
        Arrays.sort(group_a);
        Arrays.sort(group_b);
        // System.out.println(Arrays.toString(group_a));
        // System.out.println(Arrays.toString(group_b));
        
        // Aを1つずつ試して、条件を満たすBを高速に探す
        long ans = 0;
        for (long a : group_a) {
            // 条件: (a+b)がsを超えない最大のb
            long sum_remain = s - a;
            
            int min_ng = upperBound(group_b, sum_remain);
            int max_ok = min_ng - 1;
            if (max_ok < 0) continue;
            long max_b = group_b[max_ok];
            
            ans = Math.max(ans, a + max_b);
        }
        System.out.println(ans);
        
    }

    public static int upperBound(long[] array, long target) {
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
