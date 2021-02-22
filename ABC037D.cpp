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

ll MOD = 1e9+7;

int main(void){
    int h; cin >> h;
    int w; cin >> w;
    int n = h*w;
    vi array(n);
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            int num; cin >> num;
            array[i*w+j] = num;
        }
    }
    
    // ans:
    // board上に数がある。マスi < マスjの時にi -> jへ移動できる。
    // 好きなマスでスタート、ストップする時の移動経路の個数は？
    
    // how:
    // DAGのDPの要領で、fromがないマスから順に確定させていく。
    // -> ans = Σdp[i] (1 <= i <= H*W) 
    
    // to: 隣接リスト(有向)
    vvi to(n);
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            int p, q;
            if (j+1 < w) {
                p = i*w+j;
                q = i*w+(j+1);
                if (array[p] < array[q]) to[p].push_back(q);
                if (array[p] > array[q]) to[q].push_back(p);
            }
            if (i+1 < h) {
                p = i*w+j;
                q = (i+1)*w+j;
                if (array[p] < array[q]) to[p].push_back(q);
                if (array[p] > array[q]) to[q].push_back(p);
            }
        }
    }
    // for (vi toi: to) {
    //     Arrays_toString(toi);
    // }
    
    // from: (トポロジカルソート用)
    vector<set<int>> from(n);
    for (int i = 0; i < n; i++) {
        for (int q : to[i]) {
            from[q].insert(i);
        }
    }
    
    // dp[i]: マスiに来る移動経路のパターン数
    vector<ll> dp(n, 1);
    
    // que: 自分以前は全て処理された(確定した)頂点の集合
    queue<int> que;
    for (int i = 0; i < n; i++) {
        if (from[i].size() == 0) que.push(i);
    }
    
    // ダイクストラみたいに確定する頂点から処理していく
    while (!que.empty()) {
        int p = que.front(); que.pop();
        
        for (int q : to[p]) {
            dp[q] += dp[p];
            dp[q] %= MOD;
            
            // 確定できる頂点は処理対象に追加
            // (トポロジカルソート順に処理できる)
            from[q].erase(p);
            if (from[q].size() == 0) que.push(q);
        }
    }
    
    ll ans = 0;
    for (ll dpi : dp) {
        ans += dpi;
        ans %= MOD;
    }
    print(ans);
}
