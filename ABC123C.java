import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        int positionCount = 5;
        long N = sc.nextLong();
        long minLimit = sc.nextLong();
        while (positionCount-- > 1) {
            minLimit = Math.min(minLimit, sc.nextLong());
        }
        
        long answer = (long)Math.ceil((double)N / minLimit) + 4;
        System.out.println(answer);
    }
}
