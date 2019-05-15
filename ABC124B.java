import java.util.*;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int mountainCount = sc.nextInt();
        int high = -1;
        int count = 0;
        
        while (mountainCount-- > 0) {
            int mountainsHigh = sc.nextInt();
            if (mountainsHigh >= high) {
                count++;
                high = mountainsHigh;
            }
        }

        System.out.println(count);
    }
}
