#include <iostream>
int main(){ 
std::string st;  int f=1; 
std::cin>>st;
if(st[2]=='A'||st[2]=='E'||st[2]=='I'||st[2]=='O'||st[2]=='U'||st[2]=='Y') f=0; 
else  for(int i=0;st[i+1]!='\0';i++)
        if(i==2||i==1||i==5||i==6) continue; //to avoid "letter" and "-" 
        else if((st[i]+st[i+1])%2){f=0;break;}
f&&std::cout<<"valid"||std::cout<<"invalid";
return 0;
}