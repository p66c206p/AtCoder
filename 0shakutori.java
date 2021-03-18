    public static long shakutori(int[] array, int k) {
        // 条件を満たす区間の個数を返す
        // この場合の条件: 任意の区間の和がk未満になる
        
        long count = 0;
        int n = array.length;
        int right = 0;
        
        int sum = 0;
        
        for (int left = 0; left < n; left++) {
            while (right < n && sum + array[right] < k) {   // while (canGoNext)
                sum += array[right];
                right++;
            }
            
            int countGot = right - left;
            if (countGot > 0) {
                count += countGot;
                sum -= array[left];
            } else {
                right = left + 1;
            }
        }
        
        return count;
    }

    public static int shakutori(int[] array, int k) {
        // 条件を満たす区間のうち最大の長さを返す
        // この場合の条件: 区間の積がk以下になる
        
        int maxLength = 0;
        int n = array.length;
        int right = 0;
        
        long seki = 1;
        
        for (int left = 0; left < n; left++) {
            while (right < n && seki * array[right] <= k) {     // while (canGoNext)
                seki *= array[right];
                right++;
            }
            
            int countGot = right - left;
            if (countGot > 0) {
                maxLength = Math.max(maxLength, countGot);
                seki /= array[left];
            } else {
                right = left + 1;
            }
        }
    
        return maxLength;
    }
