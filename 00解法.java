グラフ/木: 
 - 木は必ず親が1人、グラフはその限りでない(合流が有る)
 - 二部グラフ: 2色に塗り分け、同じ色同士が手を繋がないグラフ
 - 最小全域木: 全ての頂点を繋ぐ木のうち、重みの総和が最小
DP[i][j]: i番目まで見てきたときの、状態jのbest/状態jに行くパターン数
 - i個目までを決めて、i+1個目以降を決めるときに、1～i個目までの選択は関係ない時に有効な解法
 - 確定するところから順に連鎖的に値を確定させていく
 - ナップサックDP: 集合{1,2,...,N}の最善の部分集合
 - bitDP: 部分集合をbitで定義域に持ち、ある集合から別の集合への遷移を考える
 - 桁DP: その桁の値だけで遷移が決まる、未満確定か否かを考慮する
         317: 0～3 -> 00～31 -> 000~317をdp[i][j][k]で分類しながら遷移させる
         ↑dp[1]=4通り、dp[2]=32通り、dp[3]=318通り
 - 木DP: 子の部分木から自分の部分木を求める(葉から順に確定させていく)
DFS/BFS: 分岐する時、行けるとこまで行く(DFS)/次の深さは前の深さが全部終わってから(BFS)
DFS: 再帰
BFS: 最短経路問題、迷路、距離1のグラフ
最短路:
 - 木＝ダイクストラ、BFS、DFS
 - 辺の長さが1のグラフ＝ダイクストラ、BFS
 - 辺の長さがバラバラのグラフ＝ダイクストラ
 - BFS(辺の長さが1) O(N+M)
 - ダイクストラ法(辺の長さバラバラ) O((N+M)logM)
 - ワーシャルフロイド法(全点の組み合わせ) O(N^3)
 - ベルマンフォード法(負辺がある) O(NM)
しゃくとり法: 条件を満たす区間(の長さ/の個数)、うねうね進む
累積和: 区間和をO(1)で求める手法
ダイクストラ法: S→各頂点までの最短経路(距離)を求める
 - 所要コスト(S→p)が最小の頂点pから、隣接する点qへ距離now+distを配っていく
 - PriorityQueueの先頭の頂点へは、そう行く他最短経路がないので所要コストが確定
 - youtu.be/X1AsMlJdiok
ワーシャルフロイド法: 全ての点i→jの最短距離を計算する
 - i→k→jという道がi→jという道より短ければi→jの距離を更新する
ベルマンフォード法: 2点間の距離だけ計算できる（各頂点は無理）
 - 2点間上にある辺についてN-1回ループを回して最短距離を確定させる
 - N回目も更新される頂点がある場合、S→Gのminは-INF
クラスカル法: 最小全域木の作り方
 - 最小の重みの辺を選択→その点につながる最小の辺を選択→その点につながる最小の辺を選択…
    - 但し、閉路を作る辺は選択しない
Union-Find: 同グループに属する要素数、グラフ
合同式: ex. 11 ≡ 1 (mod 5) ⇔ 11 % 5 = 1 % 5
 - mの倍数 = mで割った余りが0 = (mod m)の世界で値が0
 - 剰余計算は桁ごとに処理できる
組合せ: nCk
 - = 1,2,...,Nからk個選ぶ
 - = 1 <= a1 < a2 < ... < ak <= N
 - = 長さkの、狭義単調増加数列 (1 <= a[i] <= N)
重複組合せ: nHk = k+n-1Ck
 - = n人にk個のアメを配る
 - = k個のボールとn-1個の仕切り (comb(ボール+仕切り, ボール))
 - = ボールと仕切りの列をnext_permutationしてできるパターン数(00000011~11000000)
 - = 1,2,...,Nからk個選ぶ(同じもの選んでもOK)
 - = 1 <= a1 <= a2 <= ... <= ak <= N
 - = 長さkの、広義単調増加数列 (1 <= a[i] <= N)
形式的冪級数: 数え上げを(1+x^i)で考える
 - 状態 = xの肩、パターン数 = xの係数で管理する
 - りんご3個ぶどう2個みかん4個からn個選ぶパターン数
    - [x^n] (1+x^1+x^2+x^3)(1+x^1+x^2)(1+x^1+x^2+x^3+x^4)
    - ↑で全パターン((0,0,0)～(3,2,4))網羅できている
セグメント木: 一点更新、区間取得がO(logN)でできる
 - 遅延セグメント木: 区間更新(加算)、区間取得がO(logN)でできる
    - その区間のクエリが来るまで、dataに書き込むのを待つ(lazyに保管しておく)
BIT: 一点更新、区間取得がO(logN)でできる
包除原理: 数え上げを集合で分けて考える
 - A∪B = A + B - A∩B
    - 2でも3でも割れる数 = 2で割れる数 + 3で割れる数 - 2*3で割れる数
    - O(N)をO(1)に短縮できる
 - gcdが5の数列 = gcdが5の倍数の数列 - gcdが5*2の数列 - gcdが5*3の数列 - ...
一次不定方程式(拡張ユークリッドの互除法)
 - ax + by = c (ex. 3x + 7y = 2)
    = 席が7個ある。最初席2(ホントは席-2)にいる。何回「席を3つ移る」をしたら席0にいけるか？
    = 3*(-4) + 7*2 = 2　(-4回移動したら2周して席0につく)　(-2 > -5 > -8 > -11 > -14(≡0 (mod7)))
    = ax ≡ c (mod b)
 - ax + by = gcd(a,b) ならば、整数解が必ず存在する
中国剰余定理
 - 「xで割ってi余る、yで割ってj余る数は？」 ex. 3で割って2余る、5で割って3余る数 -> 8
 = ans ≡ i (mod x), ans ≡ j (mod y)なるans
フロー
 - 最大流: 太さを持った有向グラフがある。頂点s→頂点tに流せる水の最大値は？
誤差: 誤差が許されない→整数で扱う
 -　入力: 整数で受け取る
    - double, floatで入力を受け取った時点で誤差が既に出る
 - 切り捨て、切り上げ: 負の数と正の数で異なるので注意
 -　小数を含む四則演算は全てNG
 - 123 * 4.56 = ?
    - 4.56をStringに入れる -> "."を除く -> 456を作る -> 123*456/100(ans)
 - √a + √b < √c ?
    - 「a+b+2√a√b < c」 -> 「2√a√b < c-a-b」 -> 「4ab < (c-a-b)^2 かつ c-a-b > 0」(ans)
値のペア(Ai, Bi): = 区間(L,R) = 二次元座標(x,y) = グラフ(頂点p,qを結ぶ辺)
