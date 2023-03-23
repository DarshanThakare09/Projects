#include <stdio.h>
#include <stdlib.h>

// global declaration

const int no_of_play = 9;

// function Declarations

void boardprinter(char *turn);
void resultdisplayer(char *turn);

// main functions

int main()
{
    int x_player, o_player;
    char *turn;
    turn = (char *)calloc(no_of_play, sizeof(char));
    turn[0] = '1', turn[1] = '2', turn[2] = '3',
    turn[3] = '4', turn[4] = '5', turn[5] = '6',
    turn[6] = '7', turn[7] = '8', turn[8] = '9';

    boardprinter(turn);

    for (int i = 0; i < ((no_of_play / 2) + 1); i++)
    {

        // goto label

    atturn1:

        printf("X-Player Turn :  \b");
        scanf("%d", &x_player);
        if (turn[x_player - 1] == '1' || '2' || '3' || '4' || '5' || '6' || '7' || '8' || '9')
        {
            if (turn[x_player - 1] == 'O' || turn[x_player - 1] == 'X')
            {
                printf("OOP'S The Turn is Already Played\n");

                // using Goto statement to return in code

                goto atturn1;
            }
            else
            {
                turn[x_player - 1] = 'X';
            }
        }
        boardprinter(turn);
        resultdisplayer(turn);

        // checkes If the match draw

        if (i == 4)
        {
            printf("\n\n\t\t\t\t*********************************************\n");
            printf("\t\t\t\t**********                        ***********\n");
            printf("\t\t\t\t**********    WELL PLAYED GUY'S   ***********\n");
            printf("\t\t\t\t**********       IT'S A DRAW      ***********\n");
            printf("\t\t\t\t**********                        ***********\n");
            printf("\t\t\t\t*********************************************\n\n");

            // exit from progarm

            exit(0);
        }

        // goto label

    atturn2:

        printf("O-Player Turn :  \b");
        scanf("%d", &o_player);
        if (turn[o_player - 1] == '1' || '2' || '3' || '4' || '5' || '6' || '7' || '8' || '9')
        {
            if (turn[o_player - 1] == 'X' || turn[x_player - 1] == 'O')
            {
                printf("OOP'S The Turn is Already Played\n");

                // using Goto statement to return in code

                goto atturn2;
            }
            else
            {
                turn[o_player - 1] = 'O';
            }
        }
        boardprinter(turn);
        resultdisplayer(turn);
    }

    // returning heap memory

    free(turn);

    return 0;
}

// function Definations

void boardprinter(char *turn)
{
    printf("\t\t\t\t\t            |   |  \n");
    printf("\t\t\t\t\t          %c | %c | %c\n", turn[0], turn[1], turn[2]);
    printf("\t\t\t\t\t       _____|___|_____\n");
    printf("\t\t\t\t\t            |   |  \n");
    printf("\t\t\t\t\t          %c | %c | %c\n", turn[3], turn[4], turn[5]);
    printf("\t\t\t\t\t       _____|___|_____\n");
    printf("\t\t\t\t\t            |   |  \n");
    printf("\t\t\t\t\t          %c | %c | %c\n", turn[6], turn[7], turn[8]);
    printf("\t\t\t\t\t            |   |  \n");
}

void resultdisplayer(char *turn)
{
    if (turn[0] == 'X' && turn[1] == 'X' && turn[2] == 'X' || turn[3] == 'X' && turn[4] == 'X' && turn[5] == 'X' ||
        turn[6] == 'X' && turn[7] == 'X' && turn[8] == 'X' || turn[0] == 'X' && turn[3] == 'X' && turn[6] == 'X' ||
        turn[1] == 'X' && turn[4] == 'X' && turn[7] == 'X' || turn[2] == 'X' && turn[5] == 'X' && turn[8] == 'X' ||
        turn[0] == 'X' && turn[4] == 'X' && turn[8] == 'X' || turn[5] == 'X' && turn[4] == 'X' && turn[2] == 'X')
    {
        printf("\n\n\t\t\t\t*********************************************\n");
        printf("\t\t\t\t**********                        ***********\n");
        printf("\t\t\t\t**********   HURRRRYY CONGRAT'S   ***********\n");
        printf("\t\t\t\t**********  X -Player Wins Match  ***********\n");
        printf("\t\t\t\t**********                        ***********\n");
        printf("\t\t\t\t*********************************************\n\n");

        // exit from progarm

        exit(0);
    }
    if (turn[0] == 'O' && turn[1] == 'O' && turn[2] == 'O' || turn[3] == 'O' && turn[4] == 'O' && turn[5] == 'O' ||
        turn[6] == 'O' && turn[7] == 'O' && turn[8] == 'O' || turn[0] == 'O' && turn[3] == 'O' && turn[6] == 'O' ||
        turn[1] == 'O' && turn[4] == 'O' && turn[7] == 'O' || turn[2] == 'O' && turn[5] == 'O' && turn[8] == 'O' ||
        turn[0] == 'O' && turn[4] == 'O' && turn[8] == 'O' || turn[5] == 'O' && turn[4] == 'O' && turn[2] == 'O')
    {
        printf("\n\n\t\t\t\t*********************************************\n");
        printf("\t\t\t\t**********                        ***********\n");
        printf("\t\t\t\t**********   HURRRRYY CONGRAT'S   ***********\n");
        printf("\t\t\t\t**********  O -Player Wins Match  ***********\n");
        printf("\t\t\t\t**********                        ***********\n");
        printf("\t\t\t\t*********************************************\n\n");

        // exit from progarm

        exit(0);
    }
}