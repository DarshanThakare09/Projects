package com.dnine.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive;
    //0 - x
    //1 - o
    //2 - null
    int activeplayer = 0;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    // winpositions
    int[][] winpositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};
    public void playertap(View view){
        ImageView img = (ImageView) view;
        int tappedimage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gamestate[tappedimage] == 2) {
            gamestate[tappedimage] = activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer == 0) {
                img.setImageResource(R.drawable.tic);
                activeplayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.toe);
                activeplayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
//        if(gamestate[tappedimage] == 1){gameActive=true;}
//        if(gamestate[tappedimage] == 0){gameActive=true;}
//        else{
//            gameActive = false;
//        }
        //check if any player has won
        for(int[] winposition:winpositions){
            if(gamestate[winposition[0]] == gamestate[winposition[1]] && gamestate[winposition[1]] == gamestate[winposition[2]] && gamestate[winposition[0]] != 2){
                String winnerStr;
                gameActive = false;
                if(gamestate[winposition[0]] == 0){
                    winnerStr = "X-Win";
                }
                else{
                    winnerStr = "O-Win";
                }
                // update status bar
                TextView status = findViewById(R.id.status);
                status.setText("Tap on Restart");
                TextView won = findViewById(R.id.won);
                won.setText(winnerStr);
                TextView reset = findViewById(R.id.reset);
                reset.setText("Restart");
            }
        }
    }
    public void gameReset(View view){
        gameActive = true;
        activeplayer= 0;
        for (int i = 0; i<gamestate.length;i++){
            gamestate[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
        TextView won = findViewById(R.id.won);
        won.setText("  ");
        TextView reset = findViewById(R.id.reset);
        reset.setText("Tap To Reset");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}