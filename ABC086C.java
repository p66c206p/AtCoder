import java.util.*;


public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！

        Scanner sc = new Scanner(System.in);
        boolean can = true;
        int positionCount = sc.nextInt();
        int time_p = 0;
        int x_p = 0;
        int y_p = 0;
        while (positionCount-- > 0) {
            int time_q = sc.nextInt();
            int x_q = sc.nextInt();
            int y_q = sc.nextInt();
            
            if (!canGo(time_p, x_p, y_p, time_q, x_q, y_q)) {
                can = false;
            }
            
            time_p = time_q;
            x_p = x_q;
            y_p = y_q;
        }
        
        if (can) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
    
    public static boolean canGo(int time_p, int x_p, int y_p, int time_q, int x_q, int y_q) {
        int timeDifference = time_q - time_p;
        int length_x = Math.abs(x_q - x_p);
        int length_y = Math.abs(y_q - y_p);
        int moveLength = length_x + length_y;
        
        if (timeDifference < moveLength) return false;
        if (timeDifference == moveLength) return true;
        
        while (timeDifference != moveLength) {
            timeDifference -= 2;
            if (timeDifference < moveLength) return false;
            if (timeDifference == moveLength) return true;
        }
        
        return true;
    }
}
