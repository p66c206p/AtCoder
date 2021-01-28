import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();
        
        Map<Integer, Integer> map1 = f(s);
        Map<Integer, Integer> map2 = f(t);
        
        if (map1.equals(map2)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
    
    public static Map<Integer, Integer> f(String str) {
        char[] c = str.toCharArray();
        
        Map<Character, Integer> map = new HashMap<>();
        for (char key : c) {
            int val = map.getOrDefault(key, 0);
            map.put(key, ++val);
        }
        
        Map<Integer, Integer> val_map = new HashMap<>();
        for (Integer key : map.values()) {
            int val = val_map.getOrDefault(key, 0);
            val_map.put(key, ++val);
        }
        
        return val_map;
    }
}
