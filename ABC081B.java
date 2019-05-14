import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        int count = 0;
        int numberCount = sc.nextInt();
        long[] numbers = new long[numberCount];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = sc.nextLong();
        }
        
        while (true) {
            boolean end = false;
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] % 2 == 1) {
                    end = true;
                    break;
                }
                
                numbers[i] = numbers[i] / 2;
            }
            
            if (end) break;
            count++;
        }
        
        System.out.println(count);
    }
}
