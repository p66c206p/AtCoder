import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        
        // ans:
        // すべてのiについて、a[i] != b[(i+offset)&n]
        // となるoffsetがあれば出力せよ。
        
        // ex.
        // {1,1,1,2,2,3} {1,1,1,2,2,3}
        // -> offset = 3;
        
        // a, b併せての最頻値がnより大きい場合、
        // offsetがいくつでもNG
        int max = 0;
        int[] count = new int[200001];
        for (int i = 0; i < n; i++) {
            count[a[i]]++;
            count[b[i]]++;
        }
        for (int i = 0; i < 200001; i++) {
            max = Math.max(max, count[i]);
        }
        if (max > n) {
            System.out.println("No");
            return;
        }
        
        // offsetを枝刈り全探索
        for (int offset = 0; offset < n; offset++) {
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                boolean ok2 = true;
                if (a[i] == b[(i+offset)%n]) {
                    ok2 = false;
                    
                    // daburi: 
                    // ダブリが3個あるならoffset+=2して、
                    // 次のループで+3からスタートしてもらう
                    int daburi = 0;
                    int old = offset;
                    for (int j = 0; j < n; j++) {
                        if (a[i] == b[(i+offset+j)%n]) {
                            daburi++;
                        } else {
                            offset += daburi-1;
                            offset %= n;
                            
                            // 一周したらもうお手上げ
                            if (offset < old) {
                                System.out.println("No");
                                return;
                            }
                            break;
                        }
                    }
                } else {
                    
                }
                
                if (!ok2) {
                    ok = false;
                    break;
                }
            }
            
            if (ok) { 
                System.out.println("Yes");
                for (int i = 0; i < n; i++) {
                    System.out.print(b[(i+offset) % n]);
                    if (i != n-1) System.out.print(" ");
                    else System.out.println();
                }
                
                return;
            }
        }
    }
}
