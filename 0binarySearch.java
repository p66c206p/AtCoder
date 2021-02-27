public static int lowerBound(int[] array, int target) {
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

public static int upperBound(int[] array, int target) {
    // target"より大きい"初めての場所を返す
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

    // めぐる式二分探索
    // xxxxxooooooのxとoの境界(ok)を返す
    // <- ooooooxxxxならok < ngにする
    long ng = 0;
    long ok = 1000000000000l;
    
    while (Math.abs(ok - ng) > 1) {
        long mid = (ok + ng) / 2;
        
        // okがどうかの判定
        boolean isOK = true;
        long count = 0;
        for (int i = 0; i < n; i++) {
            if (center < costs[n - 1 - i] * amounts[i]) {
                long tmp = center / amounts[i];
                count += costs[n - 1 - i] - tmp;
            }
            
            if (count > k) {
                isOK = false;
            }
        }
        
        if (isOK) {
            ok = mid;
        } else {
            ng = mid;
        }
    }
    
    // 条件を満たす最小
    System.out.println(ok);


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
