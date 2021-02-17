#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int, int> P;

#define rep(i, n) for (int i = 0; i < (int)(n); i++)
#define print(x) cout << x << endl
#define all(v) (v).begin(),(v).end()
#define YN(ok) print((ok ? "YES" : "NO"))
#define yn(ok) print((ok ? "Yes" : "No"))
#define decimal(n) setprecision(12) << n

#define Arrays_toString(v) rep(o,(v).size()){cout<<v[o]<<", ";if(o==(v).size()-1){cout<<endl;}}
template<class T> void que_toString(queue<T> q){while(q.size()){cout<<q.front()<<", ";q.pop();if(q.empty()){cout<<endl;}}}

#include <atcoder/all>
using namespace atcoder;
using mint = modint998244353;
// using mint = modint1000000007;

// dat
struct S{
    mint val;
    int size;
};

// lazy
struct F{
    mint b;
    mint c;
};
// using F = int;

// 二項演算子(dat vs dat)
S op(S l, S r){
    return S{l.val + r.val, l.size + r.size};
}

// 単位元(dat)
S e(){
    return S{0, 0};
}

// 二項演算子(lazy vs dat)
S mapping(F f, S s){
    // 	if(f == 0) return s;
    return S{(s.val * f.b + s.size * f.c), s.size};
}


// 二項演算子(lazy vs lazy)
// 引数の順番に注意(m1,m2の順に処理)
F composition(F m2, F m1){
    return F{(m2.b * m1.b), (m2.b * m1.c + m2.c)};
}

// 単位元(lazy)
F id(){
    return F{1, 0};
}

int main(void){
    int n, q;
    cin >> n >> q;
    vector<S> v(n);
    rep(i, n) {
        int num; cin >> num;
        v[i] = S{num, 1};
    }

    lazy_segtree<S, op, e, F, mapping, composition, id> seg(v);
    
    while (q-- > 0) {
        int type; cin >> type;
        if (type == 0) {
            // 区間更新 (0-indexed)
            int l, r, b, c;
            cin >> l >> r >> b >> c;
            seg.apply(l, r, F{b, c});
        } else {
            // 区間取得 [l, r)
            int l, r;
            cin >> l >> r;
            mint res = seg.prod(l, r).val;
            print(res.val());
        }
    }
}
