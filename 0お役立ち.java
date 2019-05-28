// データ型の範囲
int 　 21億 2*10^9  2,000,000,000
long 922京 9*10^18 9,000,000,000,000,000,000
double 有効桁数16桁
int 5 / int 3 = 1(a余りbならaが答え)(切り捨て)

// 2次元配列代入
int[][] keyboard = new int[3][10];
keyboard[0] = new int[]{1,1,1,1,1,1,1,1,1,1};

// 配列を並べて表示
System.out.println(Arrays.toString(array));

// ソート 昇順、降順
// 配列
Arrays.sort(foo);
Arrays.sort(foo, Collections.reverseOrder());   // 降順はラッパークラスのみ使用可能
// 2次元配列
Arrays.sort(foo, (a, b) -> Integer.compare(a[0], b[0]));    // foo[x][]で
Arrays.sort(foo, (a, b) -> Integer.compare(b[0], a[0]));    // foo[x][]で
Arrays.sort(foo, (a, b) -> Integer.compare(a[1], b[1]));    // foo[][x]で
Arrays.sort(foo, (a, b) -> Integer.compare(b[1], a[1]));    // foo[][x]で
Arrays.sort(foo, (a, b) -> a[0].compareTo(b[0]));   // String向け
優先順位を持つ複数のソートが必要な場合、第三→第二→第一のように優先順位が低い順にソートするとOK
// ArrayList
Collections.sort(foo);
Collections.sort(foo, Collections.reverseOrder());
// Stream API
.sorted(Comparator.naturalOrder())
.sorted(Comparator.reverseOrder())
    
// Stringの比較
[-15, 0, 3, A, B, a, b, い, え]
str1.compareTo(str2) は、str1 < str2 なら負の数、str1 > str2 なら正の数

// join
System.out.println(String.join(" ",strArray));

// contains
list.contains("foo");
Arrays.asList(array).contains(foo); // 数値が含まれているか調べるにはラッパークラスである必要がある
map.containsKey("foo");

// ArrayList
List<String> strList = new ArrayList<String>();
strList.add("北海道");
strList.set(0, "東京");
strList.get(0);
strList.remove(0);

// Map
Map<String, Integer> shop = new HashMap<String, Integer>();
Map<String, Integer> shop = new LinkedHashMap<String, Integer>();   // 順番を保持できる
shop.put("北海道", 100);   // addではなくput
shop.put("北海道", 200);   // setの代わり、Mapはキー名の重複を許さない
shop.get("北海道");
shop.remove("北海道");
import java.util.Map.*; // Entryを使うときは、paizaではこれを宣言する
for (Entry<String, Integer> entry : shop.entrySet()) {
    System.out.println(entry.getKey() + " => " + entry.getValue());
}

// List型の配列
List<String>[] logsOfMember = new ArrayList[n];
Arrays.setAll(logsOfMember, new ArrayList<String>());   // fillは不可

// 配列→ArrayList
String[] str = {"a", "b", "c"};
List<String> strList = Arrays.asList(str);
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
