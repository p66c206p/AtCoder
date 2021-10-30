#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
typedef vector<ll> vll;
typedef vector<vi> vvi;
// typedef pair<int, int> P;

#define rep(i, n) for (int i = 0; i < (int)(n); i++)
#define print(x) cout << x << endl
#define all(v) (v).begin(),(v).end()
#define YN(ok) print((ok ? "YES" : "NO"))
#define yn(ok) print((ok ? "Yes" : "No"))
#define decimal(n) setprecision(25) << n

#define Arrays_toString(v) rep(o,(v).size()){cout<<v[o]<<", ";if(o==(v).size()-1){cout<<endl;}}

// 分数 a/b
struct fraction{
    ll a, b;
    
    bool operator<(const fraction &other) const{
        return a*other.b < b*other.a;
    }
    bool operator<=(const fraction &other) const{
        return a*other.b <= b*other.a;
    }
};

typedef pair<fraction, fraction> P;

int main(void){
    int n; cin >> n;
    vi x(n), y(n);
    rep(i, n) cin >> x[i] >> y[i];
    
    // 区間の配列(値は分数)
    vector<P> v(n);
    rep(i, n) {
        fraction l{x[i]-1, y[i]};
        fraction r{x[i], y[i]-1};
        P p(r, l);
        v[i] = p;
    }
    sort(all(v));
    
    // 区間スケジューリング問題
    int cnt = 0;
    fraction now{-1, 1};
    for (auto [r, l] : v) {
        if (now <= l) {
            cnt++;
            now = r;
        }
    }
    print(cnt);
}
