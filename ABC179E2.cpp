#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int, ll> P;

#define rep(i, n) for (int i = 0; i < (int)(n); i++)
#define print(x) cout << x << endl
#define all(v) (v).begin(),(v).end()
#define YN(ok) print((ok ? "YES" : "NO"))
#define yn(ok) print((ok ? "Yes" : "No"))
#define decimal(n) setprecision(25) << n

#define Arrays_toString(v) rep(o,(v).size()){cout<<v[o]<<", ";if(o==(v).size()-1){cout<<endl;}}

int main(void){
    ll k; cin >> k;
    k--;
    int x, m; cin >> x >> m;
    
    vi v(m, -1);
    int now = x;
    while (true) {
        if (v[now] != -1) break;
        
        int next = (int)(((ll)now*now) % m);
        v[now] = next;
        now = next;
    }
    // Arrays_toString(v);
    
    // ans: 今点iにいる。操作をk回した時の合計得点は？
    // 操作:
    // 現頂点が次に指す頂点へ移動、頂点iに移動した時i点ゲット
    
    // how: ダブリングを用いる(繰り返し二乗法の要領)。
    // ex. 10回 = 2^1回 + 2^3回やればOK
    
    // ダブリング (形がoでもρでもどちらでもOK)
    // next[i][j]:
    // first: jから2^i手先の場所
    // second: jから2^i回移動してきた時の合計得点
    vector<vector<P>> next(60, vector<P>(m));
    rep(j, m) {
        int to = v[j];
        ll sum = v[j];
        P nj(to, sum);
        next[0][j] = nj;
    }
    rep(i, 60-1)rep(j, m) {
        // ex. 8手先は現地点から4手先の4手先
        int to = next[i][j].first;
        if (to < 0 || m <= to) continue;
        ll sum = next[i][j].second;
        P nj(to, sum);
        
        to = next[i][nj.first].first;
        sum += next[i][nj.first].second;
        P nnj(to, sum);
        
        next[i+1][j] = nnj;
    }
    // rep(i, 60) Arrays_toString(next[i]);
    
    ll res = x;
    now = x;
    rep(d, 60) {
        if (k % 2 == 1) {
            res += next[d][now].second;
            now = next[d][now].first;
        }
        k /= 2;
        // print(res << " " << now);
    }
    print(res);
}
