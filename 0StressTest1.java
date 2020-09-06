import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        int trying = 100;
        while (trying-- > 0) {
            solve();
        }
        
        // 768
        // 128 42+392+288
        // 178 252+392+48
    }
    
    public static void solve() {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int n = 10;
        int m = Math.min(n, random.nextInt(1000));
        // a[i]: 係数
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = random.nextInt(100);
        }
        Arrays.sort(a);
        
        // process:
        // 人数a[i]の家族iを数直線の1～nに置く。
        // 求めたいのは、「全ての人のペアにおける距離の総和」のmax
        
        // how:
        // 左の総和/右の総和を持っておき、少ない方の側を選択、
        // 左の末尾、右の先頭に家族iを突っ込む。
        // (この操作は係数aが大きい順に行う)
        
        // ans: 全ての(i, j)ペアにおけるf(i, j)の総和
        // f(i, j) = a[i]*a[j] * (b[j]-b[i])
        
        int left_sum = 0;
        int right_sum = 0;
        
        int left_now = 1;
        int right_now = n;
        
        // map(b, a): 位置bに家族iのa人がいる
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i = m-1; i >= 0; i--) {
            if (left_sum < right_sum) {
                map.put(left_now, a[i]);
                left_sum += a[i];
                left_now++;
            } else {
                map.put(right_now, a[i]);
                right_sum += a[i];
                right_now--;
            }
        }
        // System.out.println(map.toString());
        
        // i<jなるペアだけみる
        long ans = 0;
        for (Integer b_i : map.keySet()) {
            int a_i = map.get(b_i);
            for (Integer b_j : map.keySet()) {
                if (b_i >= b_j) continue;
                
                int a_j = map.get(b_j);
                
                long now = (long)a_i*a_j * (b_j - b_i);
                ans += now;
            }
        }
        // System.out.println(ans);
        
        // 愚直解
        long ans2 = 0;
        int[] array = new int[n];
        for (int i = 0; i < m; i++) {
            array[i] = a[i];
        }
        Arrays.sort(array);
        int[] rec = new int[n];
        do {
            long tmp = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    tmp += (long)array[i] * array[j] * (j-i);
                }
            }
            
            if (tmp > ans2) {
                ans2 = Math.max(ans2, tmp);
                rec = array.clone();;
            }
        } while (nextPermutation(array));
        
        // NGパターンを出力
        if (ans != ans2) {
            System.out.println(ans);
            System.out.println(ans2);
            System.out.println(map.toString());
            System.out.println(Arrays.toString(rec));
        }
    }
    
    public static boolean nextPermutation(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i - 1] < array[i]) {
                int j = find(array, array[i - 1], i, array.length - 1);
                int temp = array[j];
                array[j] = array[i - 1];
                array[i - 1] = temp;
                Arrays.sort(array, i, array.length);
                return true;
            }
        }
        return false;
    }
	
    private static int find(int[] array, int dest, int f, int l) {
        if (f == l) return f;
        
        int m = (f + l + 1) / 2;
        return (array[m] <= dest) ? find(array, dest, f, m - 1) : find(array, dest, m, l);
    }
}
