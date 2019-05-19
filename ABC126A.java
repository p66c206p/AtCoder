import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int index = sc.nextInt();
        String str = sc.next();
        String sa = str.substring(0,index-1);
        String sb = str.substring(index-1,index).toLowerCase();
        String scc = str.substring(index);
        System.out.println(sa + sb + scc);
    }
}
