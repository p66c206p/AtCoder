class UnionFind {
    int[] par;  // 自身の親
    int[] size; // ※(自身の属するグループの要素数はsize[uf.root(i)])
    int connectedComponent;

    UnionFind(int n) {
        par = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
        
        size = new int[n];
        Arrays.fill(size, 1);
        connectedComponent = n;
    }

    int root(int x) {
        if (par[x] == x) return x;
        return par[x] = root(par[x]);
        // 1-2-4の場合、4の親=2を4の根=1に繋ぎ変えた上で親を返す
        // ↑根まで辿る深さを減らす為
        // return a = b;とは、aにbを代入し、aを返すという意味
    }

    void unite(int x, int y) {
        int rx = root(x);
        int ry = root(y);
        if (rx != ry) {
            par[ry] = rx;
            
            int resize = size[rx] + size[ry];
            size[rx] = resize;
            size[ry] = resize;
            connectedComponent--;
        }
    }

    boolean same(int x, int y) {
        int rx = root(x);
        int ry = root(y);
        return rx == ry;
    }
}
