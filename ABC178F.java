import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        // Arrays.sort(a);
        // Arrays.sort(b);
        
        boolean ok = true;
        int max = 0;
        int[] count = new int[200001];
        for (int i = 0; i < n; i++) {
            count[a[i]]++;
            count[b[i]]++;
        }
        for (int i = 0; i < 200001; i++) {
            max = Math.max(max, count[i]);
        }
        if (max > n) ok = false;
        
        if (!ok) {
            System.out.println("No");
            return;
        }
        
        // offset(0からn-1まで)を全て試す
        int[] ans = new int[n];
        int offset = 0;
        while (true) {
            for (int i = 0; i < n; i++) {
                ans[(i+offset)%n] = b[i];
            }
            
            // ng_length:
            // ex. 2のとき、offset+1も+2もNGなので+3まで飛んでもらう
            boolean ok2 = true;
            int ng_length = 0;
            for (int i = 0; i < n; i++) {
                if (ans[i] == a[i]) {
                    ok2 = false;
                    
                    int now = a[i];
                    for (int j = i+1; j < n; j++) {
                        if (a[j] == now) {
                            ng_length++;
                        } else {
                            break;
                        }
                    }
                    break;
                }
            }
            
            if (ok2) {
                break;
            } else {
                offset += 1 + ng_length;
            }
        }
        
        System.out.println("Yes");
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i]);
            if (i != n-1) {
                System.out.print(" ");
            } else {
                System.out.println();
            }
        }
    }
}
