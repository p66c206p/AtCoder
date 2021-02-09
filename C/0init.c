#include <bits/stdc++.h>
#include <algorithm>
using namespace std;
typedef long long ll;

#define rep(i, n) for (int i = 0; i < (int)(n); i++)
#define print(x) cout << x << endl
#define vi vector<int>
#define vvi vector<vi>
#define all(v) (v).begin(),(v).end()
#define YN(ok) print(ok ? "YES" : "NO")
#define yn(ok) print(ok ? "YES" : "NO")
#define decimal(n) setprecision(12) << n

int n, k;
string s;

int main(void){
    cin >> n >> k;
    
    double res = 0;
    rep(i, n+1) {
        if (i == 0) continue;
        
        double tmp = (double)1 / n;
        int now = i;
        while (now < k) {
            now *= 2;
            tmp /= 2;
        }
        
        res += tmp;
    }
    print(decimal(res));
}
