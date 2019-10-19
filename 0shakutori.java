public static long shakutori(int[] array, long k) {
    // 条件を満たす区間の数を返す
    
    long count = 0;
    int n = array.length;
    int right = 0;
    long sum = 0;
    
    for (int left = 0; left < n; left++) {
        // actionしても条件を満たすならactionして1つ右へ
        while (right < n && sum + array[right] < k) {
            sum += array[right];
            right++;
        }
        
        // 条件を満たすとこまでを数え上げる
        count += right - left;
        
        if (right == left) {
            right++;
        } else {
            sum -= array[left];
        }
    }
    
    return count;
}

public static long shakutori(int[] array, long k) {
    // 条件を満たす区間の数を返す
    // この場合の条件: 任意の区間の和がk未満になる
    
    long count = 0;
    int n = array.length;
    int right = 0;
    
    long sum = 0;
    boolean canGoNext = right < n && sum + array[right] < k;
    
    for (int left = 0; left < n; left++) {
        while (canGoNext) {
            sum += array[right];
            right++;
            
            canGoNext = right < n && sum + array[right] < k;
        }
        
        int countGot = right - left;
        if (countGot) {
            count += countGot;
            sum -= array[left];
        } else {
            right = left + 1;
        }
    }
    
    return count;
}
