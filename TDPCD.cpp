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
template<class T> void que_toString(queue<T> q){while(q.size()){cout<<q.front()<<", ";q.pop();if(q.empty()){cout<<endl;}}}

double dp[101][70][40][30];

int main(void){
    int n; cin >> n;
    ll d; cin >> d;
    
    ll tmp = d;
    int two = 0;
    while (tmp % 2 == 0) {
        tmp /= 2;
        two++;
    }
    int three = 0;
    while (tmp % 3 == 0) {
        tmp /= 3;
        three++;
    }
    int five = 0;
    while (tmp % 5 == 0) {
        tmp /= 5;
        five++;
    }
    if (tmp != 1) {
        print(0);
        return 0;
    }
    
    // globalなので0で初期化済み
    dp[0][0][0][0] = 1;
    
    rep(i,n){
        rep(j,two+1)rep(k,three+1)rep(l,five+1) {
            // 1
            dp[i+1][j][k][l] += dp[i][j][k][l] / 6;
            
            // 2
            // (minによって条件を満たすやつを1箇所に集約させる)
            dp[i+1][min(j+1,two)][k][l] += dp[i][j][k][l] / 6;
            
            // 3
            dp[i+1][j][min(k+1,three)][l] += dp[i][j][k][l] / 6;
            
            // 4
            dp[i+1][min(j+2,two)][k][l] += dp[i][j][k][l] / 6;
            
            // 5
            dp[i+1][j][k][min(l+1,five)] += dp[i][j][k][l] / 6;
            
            // 6
            dp[i+1][min(j+1,two)][min(k+1,three)][l] += dp[i][j][k][l] / 6;
        }
    }
    print(decimal(dp[n][two][three][five]));
}
