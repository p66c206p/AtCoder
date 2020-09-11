import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) { 
            array[i] = sc.nextInt();
        }
        Arrays.sort(array);
        // System.out.println(Arrays.toString(array));
        
        long negative = 0;
        long zero = 0;
        long positive = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] < 0) {
                negative++;
            } else if (array[i] == 0) {
                zero++;
            } else {
                positive++;
            }
        }
        
        long nega_pair_count = negative * positive;
        long posi_pair_count = positive * (positive-1) / 2 + negative * (negative-1) / 2;
        long zero_pair_count = (long)n*(n-1)/2 - nega_pair_count - posi_pair_count;
        // System.out.println(zero_pair_count);
        
        int type = 0;
        if (k <= nega_pair_count) {
            type = 0;
        } else if (k <= nega_pair_count + zero_pair_count) {
            type = 1;
        } else {
            type = 2;
        }
        // System.out.println(type);
        
        long ans = 0;
        if (type == 0) {
            ans = binarySearch(array, k);
        } else if (type == 1) {
            ans = 0;
        } else {
            k -= (nega_pair_count + zero_pair_count);
            // System.out.println(k);
            ans = binarySearch2(array, k, negative);
        }
        System.out.println(ans);
    }
    
    public static int lowerBound(long[] array, long target) {
        // target"以上の"初めての場所を返す
        // {1, 3, 3, 7} target: 3 -> 1
        
        int left = 0;
        int right = array.length;
        
        while (left < right) {
            int center = (left + right) / 2;
            if (array[center] < target) {
                left = center + 1;
            } else {
                right = center;
            }
        }
        
        return left;
    }

    public static long binarySearch(long[] array, long k) {
        // 条件を満たす最小の値(最大が知りたい場合if(ok)のブロックを反転する)
        long left = -1000000000000000000L;
        long right = 0;
        
        int n = array.length;
        
        while (left < right) {
            long center = (left + right-1) / 2;
            // System.out.println(center);
            // System.out.println(left + " " + center + " " + right);
            
            boolean ok = false;
            // okがどうかの判定
            long count = 0;
            for (int i = 0; i < n; i++) {
                long now = array[i];
                if (now >= 0) break;
                
                long target = (center+now+1) / now;
                long tmp = n - lowerBound(array, target);
                count += tmp;
                
                // System.out.println(now + " " + target + " " + tmp);
                
                if (count >= k) {
                    ok = true;
                }
            }
            
            if (ok) {
                right = center;
            } else {
                left = center + 1;
            }
        }
        
        return left;
    }

    public static long binarySearch2(long[] array, long k, long negative) {
        // 条件を満たす最小の値(最大が知りたい場合if(ok)のブロックを反転する)
        long left = 0;
        long right = 1000000000000000000L;
        
        int n = array.length;
        
        while (left < right) {
            long center = (left + right) / 2;
            // System.out.println(left + " " + center + " " + right);
            
            boolean ok = false;
            // okがどうかの判定
            long count = 0;
            for (int i = 0; i < n; i++) {
                long now = array[i];
                if (now < 0) {
                    long target = center / now;
                    long tmp = lowerBound(array, target);
                    if (tmp <= i) {
                        tmp = i+1;
                    }
                    if (negative-tmp > 0) {
                        count += negative-tmp;
                    }
                    
                    // System.out.println(now + " " + target + " " + tmp + " " + (negative-tmp) + " " + count);
                } else if (now > 0) {
                    long target = (center+now) / now;
                    long tmp = lowerBound(array, target) - 1;
                    if (tmp-i > 0) {
                        count += tmp-i;
                    }
                    
                    // System.out.println(now + " " + target + " " + tmp + " " + (tmp-i) + " " + count);
                } else {
                    
                }
                
                if (count >= k) {
                    ok = true;
                }
            }
            
            if (ok) {
                right = center;
            } else {
                left = center + 1;
            }
        }
        
        return left;
    }
}
