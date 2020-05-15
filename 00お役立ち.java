// ショートカット
http://bit.ly/2Tu7y0Q // Javaで競技プログラミングをするときによく使う標準ライブラリ
http://bit.ly/39xTxFd // グラフ理論の用語説明

// 計算量 (O(10^7)以下ならOK)
10^5 -> O(NlogN)
10^3 -> O(N^2)
10^2 -> O(N^3)

// Stringを1文字ずつ配列に入れる
String str = sc.next();
char c[] = str.toCharArray();
int k = c[i] - 'a'; // 'b' -> 1
char c = (char)(i + 'a'); // 3 -> 'd'
String str = sc.nextLine(); // 空白文字も含めて1行を取りたい時

// データ型の範囲
int 　 21億 2*10^9  2,000,000,000
long 922京 9*10^18 9,000,000,000,000,000,000
double 有効桁数16桁
int 5 / int 3 = 1(a余りbならaが答え)(切り捨て)

// ソート 昇順、降順
// 配列
Arrays.sort(foo);
Arrays.sort(foo, Collections.reverseOrder());   // 降順はラッパークラスのみ使用可能
// 2次元配列
Arrays.sort(foo, (a, b) -> Integer.compare(a[0], b[0]));    // foo[][0]で
Arrays.sort(foo, (a, b) -> Integer.compare(b[0], a[0]));    // foo[][0]で
Arrays.sort(foo, (a, b) -> Integer.compare(a[1], b[1]));    // foo[][1]で
Arrays.sort(foo, (a, b) -> Integer.compare(b[1], a[1]));    // foo[][1]で
Arrays.sort(foo, (a, b) -> a[0].compareTo(b[0]));   // String向け
 - 優先順位を持つ複数のソートが必要な場合、第三→第二→第一のように優先順位が低い順にソートするとOK
// List
list.sort(Comparator.naturalOrder());
list.sort(Comparator.reverseOrder());
// Set 
Setは.sortがないのでTreeSet（昇順）を用いる。
// Stream API
.sorted(Comparator.naturalOrder())
.sorted(Comparator.reverseOrder())

// 配列を並べて表示
System.out.println(Arrays.toString(array));

// ListとSetの使い分け（上の方が優先順位が高い）
要素の重複: ある(List)、ない(Set)
get(i)を使う: List
containsを使う: Setが圧倒的に速い

// 配列とリストの使い分け
挿入削除が速い → リスト
特定のindexのアクセスが速い → 配列
出し入れの操作がメイン → キュー、スタック
    
// Stringの辞書順/比較
[-11, -111, 0, 11, 111, 2, A, B, a, b, い, え]
str1.compareTo(str2) は、str1 < str2 なら負の数、str1 > str2 なら正の数

// join
System.out.println(String.join(" ",strArray));

// contains
ListよりSetの方がcontainsは圧倒的に速い。
Arrays.asList(array).contains(foo); // fooがラッパークラスである必要がある
list.contains("foo");
set.contains("foo");
map.containsKey("foo");

// List
List<String> strList = new ArrayList<String>();
strList.add("北海道");
strList.set(0, "東京");
strList.get(0); //get(index)なので、List<Integer>は使えない
strList.remove(0);
// Listの配列
List<Integer>[] sub = new List[n];
for (int i = 0; i < n; i++) {
    sub[i] = new ArrayList<Integer>();
}

// Set
Set<String> strSet = new HashSet<String>();
strSet.add("北海道");
strSet.remove("北海道");
set.contains("foo");
// TreeSet(二分探索)ができる
https://bit.ly/3c35u74

// Map
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
Map<String, Integer> map = new LinkedHashMap<String, Integer>();   // 順番を保持できる
map.put("北海道", 100);   // addではなくput
map.put("北海道", 200);   // setの代わり、Mapはキー名の重複を許さない
map.get("北海道");        // Mapはget(index)はないのでキーがIntegerでも良い
map.remove("北海道");
for (Integer key : map.keySet()) {
    int val = map.get(key);
}
int val = map.getOrDefault(key, 0);
map.put(key, ++val);
// if (!map.containsKey(key)) {
//     map.put(key, 1);
// } else {
//     int val = map.get(key);
//     map.put(key, val + 1);
// }

// Queue (Dequeはdouble-ended-queue＝両端から取り出せる)
Queue<Integer> que = new ArrayDeque<Integer>();
Queue<int[]> que = new ArrayDeque<int[]>();
que.add(new int[]{x, y});
int[] cur = que.poll(); // 出す
int[] cur = que.peek(); // 見るだけ
while (!que.isEmpty()) {}

// PriorityQueue(優先順位を持つキュー)
// (常にソートされてなくても良いならListの方がgood)
PriorityQueue<Integer> que = new PriorityQueue<>(); // データを昇順で行列を作る
PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder()); // 降順で
que.add(3);
num = que.poll();
 
// Deepコピー、値渡しのコピー
Boolean[] visited = previousVisited.clone();

// 2次元配列代入
int[][] keyboard = new int[3][10];
keyboard[0] = new int[]{1,1,1,1,1,1,1,1,1,1};

// 配列→ArrayList
List<String> strList = Arrays.asList(array);
List<String> strList = Arrays.asList("a", "b", "c");

// ArrayList→配列
String[] strArray = strList.toArray(new String[strList.size()]);

// 配列→Stream
Stream<String> strStream = Arrays.stream(str);

// Stream API
import java.util.stream.Collectors; // Collectorsを使うときは、paizaではこれを宣言する
// forEach
list.forEach(System.out::println);
map.forEach((k, v) -> System.out.println(k + " : " + v));
// 操作
List<Integer> foo = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> bar = foo.stream()    // あるいは、　IntStream.rangeClosed(1, 5)
    .filter(x -> x < 3)
    .collect(Collectors.toList());
