import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！
        
        Scanner sc = new Scanner(System.in);
        double n = sc.nextInt();
        double k = sc.nextInt();
        int answer = (int)Math.ceil((n - 1) / (k - 1));
        
        System.out.println(answer);
    }
}
