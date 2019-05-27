import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int r = Math.min(n, k);
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }
        
        int opCount = 1;
        long max = 0;
        while (opCount <= r) {
            for (int i = 0; i <= opCount; i++) {
                long score = calcScore(values, k, i, opCount - i);
                max = Math.max(max, score);
            }
            opCount++;
        }
        
        System.out.println(max);
    }
    
    public static long calcScore(int[] values, int k, int aCount, int bCount) {
        List<Integer> list = new ArrayList<Integer>(); 
        
        for (int i = 0; i < aCount; i++) {
            list.add(values[i]);
        }
        for (int i = values.length - 1; i > values.length - 1 - bCount; i--) {
            list.add(values[i]);
        }
        
        Collections.sort(list);
        
        int canRemoveCount = k - aCount - bCount;
        while (list.get(0) < 0) {
            if (canRemoveCount > 0) {
                list.remove(0);
                canRemoveCount--;
                if (list.isEmpty()) break;
            } else {
                break;
            }
        }
        
        long score = 0;
        for (Integer v : list) {
            score += (long)v;
        }
        
        return score;
    }
}
