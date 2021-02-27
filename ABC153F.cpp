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
    int n, d, a; 
    cin >> n >> d >> a;
    vector<P> v(n);
    rep(i, n) cin >> v[i].first >> v[i].second;
    sort(all(v));
    
    // ans: 数直線上に敵がいる。全員倒すのに必要な操作回数の最小値は？
    // 操作: 区間長Kの敵全員のHPをA減らす。
    
    // how:
    // 前から必要な数だけ操作する。
    // 操作がいつまで持続するかをキューで持つ。
    
    // que: 現在使用中の魔法のキュー
    // sum: 現在一度に与えられるダメージ
    queue<P> que;
    ll sum = 0;
    ll res = 0;
    rep(i, n) {
        int x = v[i].first;
        int h = v[i].second;
        
        // check
        while (not que.empty()) {
            P p = que.front();
            int limit = p.first;
            int damage = p.second;
            if (limit < x) {
                que.pop();
                sum -= damage;
            } else {
                break;
            }
        }
        
        // attack
        h -= sum;
        if (h > 0) {
            int cnt = (h+a-1) / a;
            int damage = a*cnt;
            int limit = x + 2*d;
            
            P p(limit, damage);
            que.push(p);
            sum += damage;
            
            res += cnt;
        }
    }
    print(res);
}
