import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here! 
        Scanner sc = new Scanner(System.in);
        
        // n (10^12以下なら高速)
        long n = sc.nextLong();
        
        // map: nの素因数の頻度 (60 => (2 => 2, 3 => 1, 5 => 1)
        // list: nの素因数のリスト (60 => [2, 2, 3, 5])
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        List<Long> list = new ArrayList<Long>();
        
        long m = n;
        for (long i = 2; (i * i) <= n; i++) {
            int cnt = 0;
            
            while ((m % i) == 0) {
                cnt++;
                m /= i;
                list.add(i);
            }
            
            if (cnt > 0) {
                map.put(i, cnt);
            }
        }
        if (m > 1) {
            map.put(m, 1);
            list.add(m);
        }
        
        // for (Long key : map.keySet()) {
        //     int val = map.get(key);
        //     System.out.println(key + " " + val);
        // }
        // for (Long f : list) {
        //     System.out.println(f);
        // }
    }
}
