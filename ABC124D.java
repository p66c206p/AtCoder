import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String s = sc.next();
        
        int[] sArray = new int[n + 20000];
        int j = 0;
        int nowNum = 1;
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            int num = Integer.valueOf(String.valueOf(s.charAt(i)));
            if (num == nowNum) {
                count++;
                continue;
            } else {
                sArray[j] = count;
                j++;
                count = 1;
                if (nowNum == 0) {
                    nowNum = 1;
                } else {
                    nowNum = 0;
                }
            }
        }
        sArray[j] = count;
        if (j % 2 == 1) {
            j++;
            sArray[j] = 0;
        }
        
        if (k > (j + 1) / 2) {
            System.out.println(n);
            return;
        }
        
        int[] tArray = new int[sArray.length + 20000];
        for (int i = 0; i < sArray.length; i += 2) {
            int cnt = 0;
            for (int l = i; l <= i + k * 2; l++) {
                cnt += sArray[l];
            }
            
            tArray[i] = cnt;
            if (j == i + k * 2) break;
        }
        
        Arrays.sort(tArray);
        System.out.println(tArray[tArray.length - 1]);
    }
}
