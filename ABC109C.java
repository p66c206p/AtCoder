import java.util.*;
import java.util.Map.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int currentPosition = sc.nextInt();
        
        //Setには.floorはないのでTreeSetにしないと使えない
        TreeSet<Integer> numSet = new TreeSet<Integer>();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            numSet.add(num);
        }
        
        int p = -1;
        if (numSet.size() > 1) {
            int a = 2000000000;
            int b = 2000000000;
            
            try {
                a = numSet.floor(currentPosition);
            } catch (Exception e) {
            }
            try {
                b = numSet.ceiling(currentPosition);
            } catch (Exception e) {
            }
            
            if (Math.abs(currentPosition - a) < Math.abs(currentPosition - b)) {
                p = a;
            } else {
                p = b;
            }            
        } else {
            for (Integer num : numSet) {
                p = num;
            }
        }
        
        int d = Math.abs(currentPosition - p);
        int answer = 1;
        for (int i = 1; i < d; i++) {
            boolean end = true;
            if (d % i != 0) continue;
            int length = d / i;
            
            for (Integer num : numSet) {
                if ((num - currentPosition) % length != 0) {
                    end = false;
                    break;
                }
            }
            
            if (end) {
                answer = length;
                break;
            }
        }
        
        System.out.println(answer);
    }
}
