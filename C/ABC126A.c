#include <stdio.h>
int main(void){
    // Your code here!
    int length, index;
    scanf("%d%d", &length, &index);
    
    // String型はない。char[]で受け取る。
    char s[length];
    scanf("%s", s); // 配列なので、&sでなくs
    
    // 小文字にする
    s[index - 1] += 32;
    
    printf("%s\n", s);
    return 0;
}
