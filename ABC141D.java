import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        int num = 0;
        for (int i = 0; i < n; i++) {
            num = sc.nextInt();
            queue.add(num);
        }
        
        while (m-- > 0) {
            num = queue.poll();
            num /= 2;
            queue.add(num);
        }
        
        long sum = 0;
        for (Integer ele : queue) {
            sum += ele;
        }
        
        System.out.println(sum);
    }
}
