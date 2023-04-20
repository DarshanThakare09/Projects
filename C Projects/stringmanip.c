#include <stdio.h>
#include <string.h>
#define endl \n
int main()
{
    char first_name[20], last_name[20], full_name[20];
    printf("Enter First name & Last name : \n");
    scanf("%s %s",&first_name,&last_name);
    int length_1, length_2, cmp_result;
    printf("First name :\t%s\t Last name :\t%s\n\n", first_name, last_name);
    length_1 = strlen(first_name),length_2 = strlen(last_name);
    printf("length of first name  is %d & last name is %d\n\n", length_1, length_2);
    strcpy(full_name, first_name);
    printf("Copy first name in full name: %s\n\n", full_name);
    strcat(full_name, last_name);
    printf("Concating full name and last name : %s\n\n", full_name);
    printf("comparing both string first name nad last name :  ");
    cmp_result = strcmp(first_name, last_name);
    cmp_result == 0?printf("Both String are same"):printf("Both String are not same");
    return 0;
}