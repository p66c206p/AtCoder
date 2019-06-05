#include <stdio.h>
int main(void){
    // Your code here!
    int age, price;
    scanf("%d%d", &age, &price);
    
    if (age >= 13) {
        printf("%d", price);
    } else if (age <= 5) {
        printf("0");
    } else {
        printf("%d", price / 2);
    }
    return 0; 
}
