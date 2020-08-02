import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        
        // NG: k回実際切る
        // -> 逆に、「全て長さlにするのに必要な回数」を考える
        
        // 解を二分探索
        int left = 1;   // 1だったり0だったり(解の範囲によって)
        int right = 1001001009;
        
        while (left < right) {
            int center = (left + right) / 2;
            // System.out.println(left + " " + right);
            // System.out.println(center);
            
            // okがどうかの判定
            boolean ok = false;
            
            long count = 0;
            for (int i = 0; i < n; i++) {
                // tmpcount: 長さarray[i]の丸太を長さcenterにするためには何等分？
                int tmpcount = (center + array[i] -1) / center;
                tmpcount--;
                
                count += tmpcount;
            }
            
            if (count <= k) ok = true;
            
            if (ok) {
                right = center;
            } else {
                left = center + 1;
            }
        }
        
        System.out.println(left);
    }
}
