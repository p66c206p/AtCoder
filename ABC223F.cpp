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
// using mint = modint998244353;
// using mint = modint1000000007;

// dat
// struct S{
//     int val;
//     int size;
// };
using S = int;

// lazy
// struct F{
//     mint b;
//     mint c;
// };
using F = int;
const F ID = 0;

// 二項演算子(dat vs dat)
S op(S l, S r){
    return min(l, r);
}

// 単位元(dat)
S e(){
    return 1001001009;
}

// 二項演算子(lazy vs dat)
S mapping(F f, S s){
    if (f == ID) return s;
    return s + f;
}

// 二項演算子(lazy vs lazy)
// 引数の順番に注意(m1,m2の順に処理)
F composition(F m2, F m1){
    if (m2 == ID) return m1;
    return m1+m2;
}

// 単位元(lazy)
F id(){
    return ID;
}

int main(void){
    int n, q;
    cin >> n >> q;
    
    string s; cin >> s;
    
    // 初期の配列を取得
    vector<S> v(n+1);
    rep(i, n) {
        if (s[i] == '(') v[i+1] = v[i] + 1;
        else v[i+1] = v[i] - 1;
    }
    // Arrays_toString(v);
	
    lazy_segtree<S, op, e, F, mapping, composition, id> seg(v);
    
    while (q-- > 0) {
        int type; cin >> type;
        int l, r; cin >> l >> r;
        if (type == 1) {
            // 区間加算 (0-indexed)
            if (s[l-1] == '(' && s[r-1] == ')') seg.apply(l, r, -2);
            if (s[l-1] == ')' && s[r-1] == '(') seg.apply(l, r, 2);
            swap(s[l-1], s[r-1]);
        } else {
            // 区間取得 [l, r)
            if(seg.prod(l - 1, r + 1) == seg.get(l - 1) && seg.get(l - 1) == seg.get(r)) cout << "Yes" << endl;
            else cout << "No" << endl;
        }
    }
}
