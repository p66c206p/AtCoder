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
