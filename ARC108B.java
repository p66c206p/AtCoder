import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        
        // ans: 文字列から"fox"を可能な限り取り除いた後の文字列の長さ
        // ex. xfoffoxoxx -> x
        
        // how: 文字を左からスタックに溜めて、末尾がfoxになったら取り除く。
        
        Deque<String> stack = new ArrayDeque<String>();
        for (int i = 0; i < n; i++) {
            String now = String.valueOf(str.charAt(i));
            stack.push(now);
            
            if (stack.size() >= 3) {
                String s3 = stack.pop();
                String s2 = stack.pop();
                String s1 = stack.pop();
                String tmp = s1 + s2 + s3;
                
                boolean ok = tmp.equals("fox");
                if (!ok) {
                    stack.push(s1);
                    stack.push(s2);
                    stack.push(s3);
                }
            }
            
            // System.out.println(stack.toString());
        }
        System.out.println(stack.size());
    }
}
