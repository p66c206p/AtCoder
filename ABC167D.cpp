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
#define decimal(n) setprecision(25) << n

#define Arrays_toString(v) rep(o,(v).size()){cout<<v[o]<<", ";if(o==(v).size()-1){cout<<endl;}}

int main(void){
    int n; cin >> n;
    ll k; cin >> k;
    vi v(n);
    rep(i, n) {
        cin >> v[i];
        v[i]--;
    }
    
    // ans: 今点iにいる。操作をk回したらどこにいる？
    // 操作: 現頂点が次に指す頂点へ移動
    
    // how: ダブリングを用いる(繰り返し二乗法の要領)。
    // ex. 10回 = 2^1回 + 2^3回やればOK
    
    // ダブリング (形がoでもρでもどちらでもOK)
    // next[i][j]: jから2^i手先の場所
    vvi next(60, vi(n));
    rep(j, n) {
        int nj = v[j];
        next[0][j] = nj;
    }
    rep(i, 60-1)rep(j, n) {
        // ex. 8手先は現地点から4手先の4手先
        int nj = next[i][j];
        int nnj = next[i][nj];
        next[i+1][j] = nnj;
    }
    // rep(i, 60) Arrays_toString(next[i]);
    
    int now = 0;
    rep(d, 60) {
        if (k % 2 == 1) {
            now = next[d][now];
        }
        k /= 2;
    }
    print(now + 1);
}
