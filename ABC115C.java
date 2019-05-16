import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] treesHighs = new long[n];
        for (int i = 0; i < n; i++) {
            treesHighs[i] = sc.nextLong();
        }
        
        Arrays.sort(treesHighs);
        
        long minDifference = treesHighs[k - 1] - treesHighs[0];
        for (int i = 1; i <= n - k; i ++) {
            long difference = treesHighs[i + k - 1] - treesHighs[i];
            minDifference = Math.min(minDifference, difference);
        }
        
        System.out.println(minDifference);
    }
}
