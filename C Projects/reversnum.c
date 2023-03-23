#include<stdio.h>
void revers(int* num);

int main(){
    int num;
    printf("Enter Num To revers :\n");
    scanf("%d",&num);
    revers(&num);
    printf("%d",num);

    return 0;
}

void revers(int* num){
      int reversednum;
      while(*num!=0){
      reversednum = reversednum*10 + (*num)%10;
      *num = *num/10;
      } 
      *num = reversednum;
}