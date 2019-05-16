import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        int numberCount = sc.nextInt();
        int count = 0;
        while (numberCount-- > 0) {
            long number = sc.nextLong();
            while (number % 2 == 0) {
                number /= 2;
                count++;
            }
        }
        System.out.println(count);
    }
}
