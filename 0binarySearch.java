public static int binarySearch(int[] array, int target) {
    int index = -1;
    int left = 0;
    int right = array.length;

    while (left < right) {
        int center = (left + right) / 2;
        if (array[center] == target) {
            index = center;
            break;
        } else if (array[center] > target) {
            right = center;
            
        } else if (array[center] < target) {
            left = center + 1;
            
        }
    }

    return index;
}
