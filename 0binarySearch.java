public static int lowerBound(int[] array, int target) {
    // 初めてのtarget以上のindexを返す
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

public static int upperBound(int[] array, int target) {
    // 初めてのtarget超過のindexを返す
    // {1, 3, 3, 7} target: 3 -> 3
    
    int left = 0;
    int right = array.length;
    
    while (left < right) {
        int center = (left + right) / 2;
        if (array[center] <= target) {
            left = center + 1;
        } else {
            right = center;
        }
    }
    
    return left;
}

// 条件を満たす最小の値(最大が知りたい場合if(ok)のブロックを反転する)
    long left = 0;
    long right = 1000000000000l;
    
    while (left < right) {
        long center = (left + right) / 2;
        
        boolean ok = true;
        
        // okがどうかの判定
        long count = 0;
        for (int i = 0; i < n; i++) {
            if (center < costs[n - 1 - i] * amounts[i]) {
                long tmp = center / amounts[i];
                count += costs[n - 1 - i] - tmp;
            }
            
            if (count > k) {
                ok = false;
            }
        }
        
        if (ok) {
            right = center;
        } else {
            left = center + 1;
        }
    }
    
    System.out.println(left);
