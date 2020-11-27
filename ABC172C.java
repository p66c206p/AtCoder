import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long k = sc.nextLong();
        
        // ans:
        // 2つの配列がある。和がkを超えないように前から要素を取り出す時、
        // 最大何個取り出せるか？
        
        // -> 「一方は全探索、もう一方は高速に調べる」
        
        int[] arraya = new int[n];
        for (int i = 0; i < n; i++) {
            arraya[i] = sc.nextInt();
        }
        int[] arrayb = new int[m];
        for (int i = 0; i < m; i++) {
            arrayb[i] = sc.nextInt();
        }
        
        long[] suma = new long[n+1];
        for (int i = 0; i < n; i++) {
            suma[i+1] = suma[i] + arraya[i];
        }
        long[] sumb = new long[m+1];
        for (int i = 0; i < m; i++) {
            sumb[i+1] = sumb[i] + arrayb[i];
        }
        
        // Aを1つずつ試して、条件を満たすBを高速に探す
        long ans = 0;
        for (int i = 0; i < suma.length; i++) {
            long sum_remain = k - suma[i];
            if (sum_remain < 0) continue;
            
            int min_ng = upperBound(sumb, sum_remain);
            int max_ok = min_ng - 1;
            
            ans = Math.max(ans, i + max_ok);
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
