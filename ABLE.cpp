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

vector<mint> modpow(252521);
vector<mint> modpow2(252521);

// dat
struct S{
    mint val;
    int size;
};

// lazy
// struct F{
//     mint b;
//     mint c;
// };
using F = ll;
const F ID = 0;

// 二項演算子(dat vs dat)
S op(S l, S r){
    return S{l.val*modpow[r.size] + r.val, l.size + r.size};
}

// 単位元(dat)
S e(){
    return S{0, 0};
}

// 二項演算子(lazy vs dat)
S mapping(F f, S s){
    if (f == ID) return s;
    return S{(f * modpow2[s.size-1]),  s.size};
}

// 二項演算子(lazy vs lazy)
// 引数の順番に注意(m1,m2の順に処理)
F composition(F m2, F m1){
    if (m2 == ID) return m1;
    return m2;
}

// 単位元(lazy)
F id(){
    return ID;
}

int main(void){
    int n, q;
    cin >> n >> q;
    
    modpow[0] = 1;
    for (int i = 0; i < 252521-1; i++) modpow[i+1] = modpow[i] * 10;
    modpow2[0] = 1;
    for (int i = 0; i < 252521-1; i++) modpow2[i+1] = modpow2[0] + modpow2[i] * 10;
    
    // 初期の配列を取得
    vector<S> v(n);
    rep(i, n) {
        // int num; cin >> num;
        v[i] = S{1, 1};
    }
    
    lazy_segtree<S, op, e, F, mapping, composition, id> seg(v);
    
    while (q-- > 0) {
        int l, r, d;
        cin >> l >> r >> d;
        l--;
        seg.apply(l, r, d);
        print(seg.prod(0, n).val.val());
    }
}
