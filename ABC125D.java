import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] numbers = new long[n];
        
        int negativeNumberCount = 0;
        boolean zeroExists = false;
        long minNumber = 0;
        
        for (int i = 0; i < n; i++) {
            long num = sc.nextLong();
            
            if (i == 0) {
                minNumber = num;
            }
            if (num == 0) {
                zeroExists = true;
            } else if (num < 0) {
                negativeNumberCount++;
            }
            
            minNumber = Math.min(Math.abs(minNumber), Math.abs(num));
            numbers[i] = num;
        }
        
        if (minNumber > 0) {
            minNumber *= -1;
        }
        
        long answer = 0;
        for (long num : numbers) {
            answer += Math.abs(num);
        }
        
        if (!zeroExists && negativeNumberCount % 2 == 1) {
            answer += minNumber * 2;
        }
        
        System.out.println(answer);
    }
}
