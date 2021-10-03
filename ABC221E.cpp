#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
typedef vector<ll> vll;
typedef vector<vi> vvi;
typedef pair<int, int> P;

#define rep(i, n) for (int i = 0; i < (int)(n); i++)
#define print(x) cout << x << endl
#define all(v) (v).begin(),(v).end()
#define YN(ok) print((ok ? "YES" : "NO"))
#define yn(ok) print((ok ? "Yes" : "No"))
#define decimal(n) setprecision(25) << n

#define Arrays_toString(v) rep(o,(v).size()){cout<<v[o]<<", ";if(o==(v).size()-1){cout<<endl;}}

#include <atcoder/all>
using namespace atcoder;
using mint = modint998244353;
// using mint = modint1000000007;

mint op(mint a, mint b) { return a+b; }
mint e() { return 0; }

vi coordinate_compress(vi A) {
    vi B = A;
    sort(all(B));
    B.erase(unique(all(B)), B.end());
    vi res(A.size());
    rep(i, A.size()) {
        res[i] = lower_bound(all(B), A[i]) - B.begin();
    }
    return res;
}

int main(void){
    int n; cin >> n;
    vi v(n);
    rep(i, n) cin >> v[i];
    v = coordinate_compress(v);
    // Arrays_toString(v);
    
    segtree<mint, op, e> seg(n);
    
    // 初期の配列を取得
    rep(i, n) seg.set(i, 0);
    
    mint pow2 = 1;
    rep(i, n) {
        pow2 *= 2;
    }
    
    mint res = 0;
    rep(i, n) {
        int p = v[i];
        
        // get
        mint tmp = seg.prod(0, p+1);
        res += tmp / pow2;
        
        pow2 /= 2;
        
        // add
        mint now = seg.prod(p, p+1);
        seg.set(p, now + pow2);
    }
    print(res.val());
}
