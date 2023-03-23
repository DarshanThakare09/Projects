#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Function declarations

void startgame();
int turngenrater();
void roundresult(int u, int c);
void endresult(char name[]);

// points gain by player
static int userpoint, comppoint;

int main()
{
    // startgame function call
    startgame();
    // display final result
    printf("\nYour Points: %d", userpoint);
    printf("\nComp Points: %d", comppoint);
    // Goto exit Statement
    return 0;
}

// Function declarations

// handle all function and user input and output

void startgame()
{
    char name[10];
    printf("Welcome To Stone Paper Sesior\n\nPress 1 for :Stone\nPress 2 for :Paper\nPress 3 for :Secior\n\nEnter Your Name Player:");
    scanf("%s", &name);
    for (int i = 1; i <= 3; i++)
    {
        int userturn, compturn = turngenrater();
        printf("\nRound No. %d \n", i);
        printf("Enter Your Turn: ");
        scanf("%d", &userturn);
        printf("Your play:%d\tComp play:%d\n", userturn, compturn);
        roundresult(userturn, compturn);
    }
    endresult(name);
}

// generate random comp input

int turngenrater()
{
    srand(time(NULL));
    return rand() % 3 + 1;
}

// dispaly end roundresult

void roundresult(int u, int c)
{
    if (u == c)
    {
        // its draw
    }
    else if (u == 1)
    {
        c == 3 ? userpoint++ : comppoint++;
    }
    else if (u == 2)
    {
        c == 1 ? userpoint++ : comppoint++;
    }
    else if (u == 3)
    {
        c == 1 ? userpoint++ : comppoint++;
    }
    else
    {
        printf("Undefined Input Exiting From Game!!!!!!!");
        exit(0);
    }
}

// dispaly end endresult

void endresult(char name[])
{
    if (userpoint > comppoint)
    {
        printf("\t\t\nHurrrrrr.......\n%s Wins The Match...", name);
    }
    else if (comppoint > userpoint)
    {
        printf("\t\t\nBad Luck %s Computer Win The Match!!!", name);
    }
    else
    {
        printf("\t\t\nWelll Played %s \n Its A Draw", name);
    }
}