public static long shakutori(int[] array, long k) {
    // 条件を満たす部分列の数を返す
    
    long count = 0;
    int n = array.length;
    long sum = 0;
    int right = 0;
    
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
