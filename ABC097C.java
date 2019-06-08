import java.util.*;

public class Main {
    static int k;
    static Set<String> set;
    
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        k = sc.nextInt();
        set = new TreeSet<String>();
        int n = str.length();
        
        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = String.valueOf(str.charAt(i));
        }
        
        solve("", array);
        
        int i = 1;
        for (String s : set) {
            if (i == k) {
                System.out.println(s);
                break;
            }
            i++;
        }

    }
    
    public static void solve(String str, String[] array) {
        Set<String> privateSet = new TreeSet<String>();
        
        for (int i = 0; i < array.length - (str.length() - 1); i++) {
            String str2 = "";
            for (int j = i; j < i + str.length(); j++) {
                str2 += array[j];
            }
            
            if (str.equals(str2) && i + str.length() < array.length) {
                privateSet.add(str2 + array[i + str.length()]);
            }
        }
        
        for (String s : privateSet) {
            set.add(s);
            if (set.size() >= k) return;
            solve(s, array);
        }
    }
}
