#include <stdio.h>
int main(void){
    // Your code here!
    int a, b;
    scanf("%d%d", &a, &b);
    int score = 0;
    
    if (a > b) {
        score += a--;
    } else {
        score += b--;
    }
    
    if (a > b) {
        score += a--;
    } else {
        score += b--;
    }
    
    printf("%d\n", score);
    return 0;
}
