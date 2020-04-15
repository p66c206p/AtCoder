    public static List<Integer> divisorList(int n) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                
                int j = n / i;
                if (j != i) {
                    res.add(j);
                }
            }
        }
        res.sort(Comparator.naturalOrder());
        
        return res;
    }
