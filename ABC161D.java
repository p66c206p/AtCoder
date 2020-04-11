import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        
        if (k <= 9) {
            System.out.println(k);
            return;
        }
        
        Queue<Long> que = new ArrayDeque<Long>();
        for (int i = 1; i <= 9; i++) {
            que.add((long)i);
        }
        
        int count = 9;
        while (true) {
            long cur = que.poll();
            long last = cur % 10;
            
            if (last != 0) {
                que.add(cur * 10 + last - 1);
                count++;
                if (k == count) {
                    System.out.println(cur * 10 + last - 1);
                    return;
                }
            }
                que.add(cur * 10 + last);
                count++;
                if (k == count) {
                    System.out.println(cur * 10 + last);
                    return;
                }
            if (last != 9) {
                que.add(cur * 10 + last + 1);
                count++;
                if (k == count) {
                    System.out.println(cur * 10 + last + 1);
                    return;
                }
            }
        }
    }
}
