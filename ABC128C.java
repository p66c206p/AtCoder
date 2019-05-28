import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // switchCount
        int m = sc.nextInt();   // lightCount
        
        List<Integer>[] sl = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            sl[i] = new ArrayList<Integer>();
            
            int j = sc.nextInt();
            while (j-- > 0) {
                int switchIndex = sc.nextInt();
                sl[i].add(switchIndex);
            }
        }
        
        int[] guuki = new int[m];
        for (int i = 0; i < m; i++) {
            guuki[i] = sc.nextInt();
        }
        
        int combinationCount = (int)Math.pow(2, n);
        int clearedCount = 0;
        for (int i = 0; i < combinationCount; i++) {
            String binaryNumber = Integer.toBinaryString(i);
            while (binaryNumber.length() < n) {
                binaryNumber = "0" + binaryNumber;
            }
            
            for (int j = 0; j < m; j++) {
                List<Integer> switches = sl[j];
                int sum = 0;
                for (int index : switches) {
                    int num = Integer.valueOf(String.valueOf(binaryNumber.charAt(index - 1)));
                    sum += num;
                } 
                
                if (sum % 2 != guuki[j]) {
                    break;
                }
                
                if (j == m - 1) {
                    clearedCount++;
                }
            }
        }
        
        System.out.println(clearedCount);
    }
}
