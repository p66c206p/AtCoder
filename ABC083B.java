import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        int sum = 0;
        int numberCount = sc.nextInt();
        int more = sc.nextInt();
        int less = sc.nextInt();
        
        for (int i = 1; i <= numberCount; i++) {
            int num = getKetaNoWa(i);
            
            if (more <= num && num <= less) {
                sum += i;
            }
        }

        System.out.println(sum);
    }
    
    public static int getKetaNoWa(int number) {
        int sum = 0;
        
        for (int i = 5; i >= 0; i--) {
            int tmp = number / (int)Math.pow(10, i);
            sum += tmp;
            number -= tmp * (int)Math.pow(10, i);
        }
        
        return sum;
    }
}
