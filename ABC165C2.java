import java.util.*; 

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        
        int[] a = new int[q];
        int[] b = new int[q];
        int[] c = new int[q];
        int[] d = new int[q];
        for (int i = 0; i < q; i++) {
            a[i] = sc.nextInt() - 1;
            b[i] = sc.nextInt() - 1;
            c[i] = sc.nextInt();
            d[i] = sc.nextInt();
        }
        
        // array: m個のボールとn個の仕切りを考える
        // (m, n) = (6, 4) => [0,0,0,0,0,0,1,1,1,1]
        int[] array = new int[m + n];
        for (int i = m; i < m + n; i++) {
            array[i] = 1;
        }
        
        // ボールと仕切りの配置を全探索する
        int ans = 0;
        do {
            // array2[i]: i個目の仕切りより左にあるボールの数
            // 0010100011 -> [2, 3, 6, 6]
            // = 0以上m以下の広義単調増加数列
            int[] array2 = new int[n];
            int ball = 0;
            int now = 0;
            for (int i = 0; i < m + n; i++) {
                if (array[i] == 1) {
                    array2[now] = ball;
                    now++;
                } else {
                    ball++;
                }
            }
            // System.out.println(Arrays.toString(array2));
            
            // 初項が1以上でない数列は無視
            if (array2[0] == 0) continue;
            
            int score = 0;
            for (int i = 0; i < q; i++) {
                if (array2[b[i]] - array2[a[i]] == c[i]) score += d[i];
            }
            ans = Math.max(ans, score);
            
        } while (nextPermutation(array));
        
        System.out.println(ans);
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
