    // 座標圧縮
    // ex. 8, 100, 33, 12, 6, 1211
    // ->  1, 4, 3, 2, 0, 5
    public static int[] coordinateCompress(int[] array) {
        int n = array.length;
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++) {
            a[i][0] = array[i];
            a[i][1] = i;
        }
        Arrays.sort(a, (ax, bx) -> Integer.compare(ax[0], bx[0]));
        
        int now_val = a[0][0];
        int now_idx = 0;
        for (int i = 0; i < n; i++) {
            int val = a[i][0];
            if (val != now_val) {
                now_idx++;
                now_val = a[i][0];
            }
            a[i][2] = now_idx;
        }
        Arrays.sort(a, (ax, bx) -> Integer.compare(ax[1], bx[1]));
        
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = a[i][2];
        }
        
        return res;
    }
