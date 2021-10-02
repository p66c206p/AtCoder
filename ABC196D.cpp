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

int h, w;
bool used[16][16];

// 012
// 345
// 678 の順に4パターン遷移
ll dfs(int i, int j, int a, int b) {
    // print(i << " " << j << " " << a << " " << b);
    // 手持ちがマイナスだと失敗
    if (a < 0 || b < 0) return 0;
    // 右端まで行ったら改行
    if (j == w) {
        i += 1;
        j = 0;
    }
    // 下端まで行ったら成功
    if (i == h) return 1;
    
    ll res = 0;
    
    //   do nothing
    if (used[i][j]) {
        res += dfs(i, j+1, a, b);
    }
    
    // * hanjo
    if (not used[i][j]) {
        used[i][j] = true;
        res += dfs(i, j+1, a, b-1);
        used[i][j] = false;
    }
    
    // * tate
    // *
    if (i+1 < h && not used[i][j] && not used[i+1][j]) {
        used[i][j] = true;
        used[i+1][j] = true;
        res += dfs(i, j+1, a-1, b);
        used[i][j] = false;
        used[i+1][j] = false;
    }
    
    // ** yoko
    if (j+1 < w && not used[i][j] && not used[i][j+1]) {
        used[i][j] = true;
        used[i][j+1] = true;
        res += dfs(i, j+1, a-1, b);
        used[i][j] = false;
        used[i][j+1] = false;
    }
    
    return res;
}

int main(void){
    cin >> h >> w;
    int a, b; cin >> a >> b;
    print(dfs(0, 0, a, b));
}
