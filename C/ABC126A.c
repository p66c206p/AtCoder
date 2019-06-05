#include <stdio.h>
int main(void){
    // Your code here!
    int length, index;
    scanf("%d%d", &length, &index);
    
    // String型はない。char[]で受け取る。
    // char[]は終結文字を格納するので要素数はn+1（か大きい数）にする
    char s[length + 1];
    scanf("%s", s); // 配列なので、&sでなくs
    
    // 小文字にする
    s[index - 1] += 32;
    
    printf("%s\n", s);
    return 0;
}
