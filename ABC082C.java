import java.util.*;
import java.util.Map.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        while (n-- > 0) {
            int num = sc.nextInt();
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                int v = map.get(num);
                map.put(num, v + 1);
            }
        }
        
        int count = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int val =  entry.getValue();
            
            if (val > key) {
                count += val - key;
            } else if (val < key) {
                count += val;
            }
        }
        
        System.out.println(count);
    }
}
