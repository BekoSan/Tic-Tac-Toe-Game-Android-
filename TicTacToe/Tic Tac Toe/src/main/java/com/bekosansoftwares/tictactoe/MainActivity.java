package com.bekosansoftwares.tictactoe;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener {
    static boolean x_turn = false, o_turn = true;
    public static int count_click = 1, x_es = 0, o_es = 0;
    int min = 0, max = 8, r;
    public TextView txtView;
    public Button btnok;
    //  public Button btnsrc;
    public static ImageButton bc, b2p;
    public Dialog d;

    public static Button buttons[] = new Button[9];
    public static int IDs[] = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8};
    public int gamemode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.tvTurn);

        // btnsrc = (Button) findViewById(R.id.btnScore);
        // btnsrc.setOnClickListener(this);

        d = new Dialog(this);
        d.setTitle("Choose Mode:");


        LinearLayout lay = new LinearLayout(this);
        lay.setLayoutParams(new LayoutParams(180, LayoutParams.MATCH_PARENT));
        lay.setGravity(100);
        bc = new ImageButton(this);
        b2p = new ImageButton(this);


        bc.setOnClickListener(this);
        b2p.setOnClickListener(this);


        //*****TO DO :
        d.setContentView(lay);

        bc.setImageResource(R.drawable.friend);
        b2p.setImageResource(R.drawable.pc1);


        lay.addView(bc, new LayoutParams(90, LayoutParams.MATCH_PARENT));
        lay.addView(b2p, new LayoutParams(90, LayoutParams.MATCH_PARENT));

        d.show();


        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = (Button) findViewById(IDs[i]);
            buttons[i].setOnClickListener(this);
        }
        if (x_turn) {
            txtView.setText("X Turn");
        } else {
            txtView.setText("O Turn");
        }
    }

    public class WinDialog extends Dialog { //Win Dialog
        Context c;

        ImageView imgwiner;
        Button okbtn;
        LinearLayout lay;

        public WinDialog(Context con, String Winer, int img) {
            super(con);
            this.c = con;
            d = this;
            super.setTitle(Winer);


            imgwiner = new ImageView(c);
            okbtn = new Button(c);
            btnok = okbtn;
            okbtn.setText("OK");
            switch (img) {
                case R.drawable.grin:
                    imgwiner.setImageResource(R.drawable.grin);
                    break;
                case R.drawable.slow:
                    imgwiner.setImageResource(R.drawable.slow);
                    break;
                case R.drawable.drow1:
                    imgwiner.setImageResource(R.drawable.drow1);
                    break;
            }


            lay = new LinearLayout(c);
            lay.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            lay.setOrientation(LinearLayout.VERTICAL);
            setContentView(lay);

            lay.addView(imgwiner, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            lay.addView(okbtn, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        }
    }
       /* d = new Dialog(this);
        d.setTitle("Choose Mode:");
        LinearLayout lay = new LinearLayout(this);
        lay.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        lay.setGravity(100);
         bc = new ImageButton(this);
         b2p = new ImageButton(this);

        d.setContentView(lay);

       bc.setImageResource(R.drawable.friend);
       b2p.setImageResource(R.drawable.pc);


       bc.setOnClickListener(this);
       b2p.setOnClickListener(this);
        lay.addView(bc, new LayoutParams(70,50));
        lay.addView(b2p, new LayoutParams(90,50));


        d.show();*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public static void set_x() {
        x_es += 1;
    }

    public static void set_o() {
        o_es += 1;
    }

    public static int count_x() {
        return x_es;
    }

    public static int count_o() {
        return o_es;
    }

    /**
     * On Click Function
     *
     * @param btn the view that had been clicked
     */
    @Override
    public void onClick(View btn) {


        //  if (btn == btnsrc) {  Intent intent = new Intent(this,GameScore.class); startActivity(intent); }
        if (btn == b2p) {
            this.d.hide();
            gamemode = 0;
        }
        if (btn == bc) {
            this.d.hide();
            gamemode = 1;
        }
        if (btn == btnok) {
            this.d.hide();
        }
        if (gamemode == 0) {


            GAME_With_Com:
            {
                for (int b = 0; b < MainActivity.buttons.length; b++) {
                    if (btn.getId() == MainActivity.IDs[b]) {
                        count_click++;
                        if (count_click > 1) {
                            MainActivity.buttons[b].setEnabled(false);
                        }
                        if (x_turn) {
                            txtView.setText("O Turn");
                            MainActivity.buttons[b].setText("X");
                            set_x();
                            x_turn = false;
                            Random rand = new Random();
                            r = rand.nextInt(max - min + 1) + min;
                            while (MainActivity.buttons[r].getText().equals("X") || MainActivity.buttons[r].getText().equals("O")) {
                                if (!MainActivity.buttons[0].getText().equals(" ")
                                        && !MainActivity.buttons[1].getText().equals(" ")
                                        && !MainActivity.buttons[2].getText().equals(" ")
                                        && !MainActivity.buttons[3].getText().equals(" ")
                                        && !MainActivity.buttons[4].getText().equals(" ")
                                        && !MainActivity.buttons[5].getText().equals(" ")
                                        && !MainActivity.buttons[6].getText().equals(" ")
                                        && !MainActivity.buttons[7].getText().equals(" ")
                                        && !MainActivity.buttons[8].getText().equals(" ")
                                        ) {
                                    MainActivity.buttons[b].setText("X");
                                    MainActivity.buttons[b].setEnabled(false);
                                    set_x();
                                    o_turn = true;
                                    break;
                                }
                                r = rand.nextInt(max - min + 1) + min;
                            }
                            MainActivity.buttons[r].setEnabled(false);
                            MainActivity.buttons[r].setText("O");
                            set_o();
                            x_turn = true;
                        } else {
                            txtView.setText("X Turn");
                            //o_turn = true;
                            MainActivity.buttons[b].setText("O");
                            set_o();
                            o_turn = false;
                            x_turn = true;

                            Random rand = new Random();
                            r = rand.nextInt(max - min + 1) + min;
                            while (MainActivity.buttons[r].getText().equals("X") || MainActivity.buttons[r].getText().equals("O")) {
                                if (!MainActivity.buttons[0].getText().equals(" ")
                                        && !MainActivity.buttons[1].getText().equals(" ")
                                        && !MainActivity.buttons[2].getText().equals(" ")
                                        && !MainActivity.buttons[3].getText().equals(" ")
                                        && !MainActivity.buttons[4].getText().equals(" ")
                                        && !MainActivity.buttons[5].getText().equals(" ")
                                        && !MainActivity.buttons[6].getText().equals(" ")
                                        && !MainActivity.buttons[7].getText().equals(" ")
                                        && !MainActivity.buttons[8].getText().equals(" ")
                                        ) {
                                    MainActivity.buttons[b].setText("O");
                                    MainActivity.buttons[b].setEnabled(false);
                                    set_o();
                                    x_turn = true;
                                    break;
                                }
                                r = rand.nextInt(max - min + 1) + min;
                            }
                            MainActivity.buttons[r].setEnabled(false);
                            MainActivity.buttons[r].setText("X");
                            set_x();
                            o_turn = true;
                        }//Else
                    }//End get source

                    ROWS_X:
                    {
                        //if X WINS (1,2,3) :
                        if (MainActivity.buttons[0].getText().equals("X")
                                && MainActivity.buttons[1].getText().equals("X")
                                && MainActivity.buttons[2].getText().equals("X")
                                ) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);


                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if X WINS (4,5,6) :
                        if (MainActivity.buttons[3].getText().equals("X")
                                && MainActivity.buttons[4].getText().equals("X")
                                && MainActivity.buttons[5].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if X WINS (7,8,9) :
                        if (MainActivity.buttons[6].getText().equals("X")
                                && MainActivity.buttons[7].getText().equals("X")
                                && MainActivity.buttons[8].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of ROWS X
                    COLS_X:
                    {
                        //if X WINS (1,4,7) :
                        if (MainActivity.buttons[0].getText().equals("X")
                                && MainActivity.buttons[3].getText().equals("X")
                                && MainActivity.buttons[6].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if X WINS (2,5,8) :
                        if (MainActivity.buttons[1].getText().equals("X")
                                && MainActivity.buttons[4].getText().equals("X")
                                && MainActivity.buttons[7].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if X WINS (3,6,9) :
                        if (MainActivity.buttons[2].getText().equals("X")
                                && MainActivity.buttons[5].getText().equals("X")
                                && MainActivity.buttons[8].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of COLS X
                    CROS_X:
                    {
                        //if X WINS (1,5,9) :
                        if (MainActivity.buttons[0].getText().equals("X")
                                && MainActivity.buttons[4].getText().equals("X")
                                && MainActivity.buttons[8].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if X WINS (3,5,7) :
                        if (MainActivity.buttons[2].getText().equals("X")
                                && MainActivity.buttons[4].getText().equals("X")
                                && MainActivity.buttons[6].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of CROS X
                    ROWS_O:
                    {
                        //if O WINS (1,2,3) :
                        if (MainActivity.buttons[0].getText().equals("O")
                                && MainActivity.buttons[1].getText().equals("O")
                                && MainActivity.buttons[2].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if O WINS (4,5,6) :
                        if (MainActivity.buttons[3].getText().equals("O")
                                && MainActivity.buttons[4].getText().equals("O")
                                && MainActivity.buttons[5].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if O WINS (7,8,9) :
                        if (MainActivity.buttons[6].getText().equals("O")
                                && MainActivity.buttons[7].getText().equals("O")
                                && MainActivity.buttons[8].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of ROWS O
                    COLS_O:
                    {
                        //if O WINS (1,4,7) :
                        if (MainActivity.buttons[0].getText().equals("O")
                                && MainActivity.buttons[3].getText().equals("O")
                                && MainActivity.buttons[6].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if O WINS (2,5,8) :
                        if (MainActivity.buttons[1].getText().equals("O")
                                && MainActivity.buttons[4].getText().equals("O")
                                && MainActivity.buttons[7].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if O WINS (3,6,9) :
                        if (MainActivity.buttons[2].getText().equals("O")
                                && MainActivity.buttons[5].getText().equals("O")
                                && MainActivity.buttons[8].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of COLS O
                    CROS_O:
                    {
                        //if O WINS (1,5,9) :
                        if (MainActivity.buttons[0].getText().equals("O")
                                && MainActivity.buttons[4].getText().equals("O")
                                && MainActivity.buttons[8].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if O WINS (3,5,7) :
                        if (MainActivity.buttons[2].getText().equals("O")
                                && MainActivity.buttons[4].getText().equals("O")
                                && MainActivity.buttons[6].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of CROS O
                }//End For Loop
                CHECK_NOT_EMPTY:
                {
                    if (!MainActivity.buttons[0].getText().equals(" ")
                            && !MainActivity.buttons[1].getText().equals(" ")
                            && !MainActivity.buttons[2].getText().equals(" ")
                            && !MainActivity.buttons[3].getText().equals(" ")
                            && !MainActivity.buttons[4].getText().equals(" ")
                            && !MainActivity.buttons[5].getText().equals(" ")
                            && !MainActivity.buttons[6].getText().equals(" ")
                            && !MainActivity.buttons[7].getText().equals(" ")
                            && !MainActivity.buttons[8].getText().equals(" ")
                            ) {
                        DROW:
                        {

                            if (((count_x() == count_o()) && (count_x() != 0 && count_o() != 0)) || count_x() > count_o() || count_o() > count_x()) {

                                new WinnerDialog(this, "DROW", R.drawable.drow1);

                                MainActivity.buttons[0].setText(" ");
                                MainActivity.buttons[1].setText(" ");
                                MainActivity.buttons[2].setText(" ");
                                MainActivity.buttons[3].setText(" ");
                                MainActivity.buttons[4].setText(" ");
                                MainActivity.buttons[5].setText(" ");
                                MainActivity.buttons[6].setText(" ");
                                MainActivity.buttons[7].setText(" ");
                                MainActivity.buttons[8].setText(" ");
                                MainActivity.buttons[0].setEnabled(true);
                                MainActivity.buttons[1].setEnabled(true);
                                MainActivity.buttons[2].setEnabled(true);
                                MainActivity.buttons[3].setEnabled(true);
                                MainActivity.buttons[4].setEnabled(true);
                                MainActivity.buttons[5].setEnabled(true);
                                MainActivity.buttons[6].setEnabled(true);
                                MainActivity.buttons[7].setEnabled(true);
                                MainActivity.buttons[8].setEnabled(true);
                            }

                        }//End DROW
                    }//End IF NOT EMPTY
                }//End NOT_EMPTY
            }//End of Game PC
        }//End GameMode
        else if (gamemode == 1) {
            GAME_With_Player:
            {


                for (int b = 0; b < MainActivity.buttons.length; b++) {
                    if (btn.getId() == MainActivity.IDs[b]) {
                        count_click++;
                        if (count_click > 1) {
                            MainActivity.buttons[b].setEnabled(false);
                        }
                        if (x_turn) {
                            txtView.setText("O Turn");
                            MainActivity.buttons[b].setText("X");
                            set_x();
                            x_turn = false;

                            if (!MainActivity.buttons[0].getText().equals(" ")
                                    && !MainActivity.buttons[1].getText().equals(" ")
                                    && !MainActivity.buttons[2].getText().equals(" ")
                                    && !MainActivity.buttons[3].getText().equals(" ")
                                    && !MainActivity.buttons[4].getText().equals(" ")
                                    && !MainActivity.buttons[5].getText().equals(" ")
                                    && !MainActivity.buttons[6].getText().equals(" ")
                                    && !MainActivity.buttons[7].getText().equals(" ")
                                    && !MainActivity.buttons[8].getText().equals(" ")
                                    ) {
                                MainActivity.buttons[b].setText("X");
                                MainActivity.buttons[b].setEnabled(false);
                                set_x();
                                o_turn = true;
                            }


                        } else {
                            //o_turn = true;
                            txtView.setText("X Turn");
                            MainActivity.buttons[b].setText("O");
                            set_o();
                            o_turn = false;
                            x_turn = true;


                            if (!MainActivity.buttons[0].getText().equals(" ")
                                    && !MainActivity.buttons[1].getText().equals(" ")
                                    && !MainActivity.buttons[2].getText().equals(" ")
                                    && !MainActivity.buttons[3].getText().equals(" ")
                                    && !MainActivity.buttons[4].getText().equals(" ")
                                    && !MainActivity.buttons[5].getText().equals(" ")
                                    && !MainActivity.buttons[6].getText().equals(" ")
                                    && !MainActivity.buttons[7].getText().equals(" ")
                                    && !MainActivity.buttons[8].getText().equals(" ")
                                    ) {
                                MainActivity.buttons[b].setText("O");
                                MainActivity.buttons[b].setEnabled(false);
                                set_o();
                                x_turn = true;

                            }


                        }//Else
                    }//End get source

                    ROWS_X:
                    {
                        //if X WINS (1,2,3) :
                        if (MainActivity.buttons[0].getText().equals("X")
                                && MainActivity.buttons[1].getText().equals("X")
                                && MainActivity.buttons[2].getText().equals("X")
                                ) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);


                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if X WINS (4,5,6) :
                        if (MainActivity.buttons[3].getText().equals("X")
                                && MainActivity.buttons[4].getText().equals("X")
                                && MainActivity.buttons[5].getText().equals("X")) {

                            WinDialog win = new WinDialog(this, "X WINS", R.drawable.grin);
                            win.show();
                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if X WINS (7,8,9) :
                        if (MainActivity.buttons[6].getText().equals("X")
                                && MainActivity.buttons[7].getText().equals("X")
                                && MainActivity.buttons[8].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of ROWS X
                    COLS_X:
                    {
                        //if X WINS (1,4,7) :
                        if (MainActivity.buttons[0].getText().equals("X")
                                && MainActivity.buttons[3].getText().equals("X")
                                && MainActivity.buttons[6].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if X WINS (2,5,8) :
                        if (MainActivity.buttons[1].getText().equals("X")
                                && MainActivity.buttons[4].getText().equals("X")
                                && MainActivity.buttons[7].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if X WINS (3,6,9) :
                        if (MainActivity.buttons[2].getText().equals("X")
                                && MainActivity.buttons[5].getText().equals("X")
                                && MainActivity.buttons[8].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of COLS X
                    CROS_X:
                    {
                        //if X WINS (1,5,9) :
                        if (MainActivity.buttons[0].getText().equals("X")
                                && MainActivity.buttons[4].getText().equals("X")
                                && MainActivity.buttons[8].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if X WINS (3,5,7) :
                        if (MainActivity.buttons[2].getText().equals("X")
                                && MainActivity.buttons[4].getText().equals("X")
                                && MainActivity.buttons[6].getText().equals("X")) {

                            new WinnerDialog(this, "X WINS", R.drawable.grin);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of CROS X
                    ROWS_O:
                    {
                        //if O WINS (1,2,3) :
                        if (MainActivity.buttons[0].getText().equals("O")
                                && MainActivity.buttons[1].getText().equals("O")
                                && MainActivity.buttons[2].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if O WINS (4,5,6) :
                        if (MainActivity.buttons[3].getText().equals("O")
                                && MainActivity.buttons[4].getText().equals("O")
                                && MainActivity.buttons[5].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if O WINS (7,8,9) :
                        if (MainActivity.buttons[6].getText().equals("O")
                                && MainActivity.buttons[7].getText().equals("O")
                                && MainActivity.buttons[8].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of ROWS O
                    COLS_O:
                    {
                        //if O WINS (1,4,7) :
                        if (MainActivity.buttons[0].getText().equals("O")
                                && MainActivity.buttons[3].getText().equals("O")
                                && MainActivity.buttons[6].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if O WINS (2,5,8) :
                        if (MainActivity.buttons[1].getText().equals("O")
                                && MainActivity.buttons[4].getText().equals("O")
                                && MainActivity.buttons[7].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if O WINS (3,6,9) :
                        if (MainActivity.buttons[2].getText().equals("O")
                                && MainActivity.buttons[5].getText().equals("O")
                                && MainActivity.buttons[8].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of COLS O
                    CROS_O:
                    {
                        //if O WINS (1,5,9) :
                        if (MainActivity.buttons[0].getText().equals("O")
                                && MainActivity.buttons[4].getText().equals("O")
                                && MainActivity.buttons[8].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                        //if O WINS (3,5,7) :
                        if (MainActivity.buttons[2].getText().equals("O")
                                && MainActivity.buttons[4].getText().equals("O")
                                && MainActivity.buttons[6].getText().equals("O")) {

                            new WinnerDialog(this, "O WINS", R.drawable.slow);

                            MainActivity.buttons[0].setText(" ");
                            MainActivity.buttons[1].setText(" ");
                            MainActivity.buttons[2].setText(" ");
                            MainActivity.buttons[3].setText(" ");
                            MainActivity.buttons[4].setText(" ");
                            MainActivity.buttons[5].setText(" ");
                            MainActivity.buttons[6].setText(" ");
                            MainActivity.buttons[7].setText(" ");
                            MainActivity.buttons[8].setText(" ");
                            MainActivity.buttons[0].setEnabled(true);
                            MainActivity.buttons[1].setEnabled(true);
                            MainActivity.buttons[2].setEnabled(true);
                            MainActivity.buttons[3].setEnabled(true);
                            MainActivity.buttons[4].setEnabled(true);
                            MainActivity.buttons[5].setEnabled(true);
                            MainActivity.buttons[6].setEnabled(true);
                            MainActivity.buttons[7].setEnabled(true);
                            MainActivity.buttons[8].setEnabled(true);
                        }
                    }//End of CROS O
                }//End For Loop
                CHECK_NOT_EMPTY:
                {
                    if (!MainActivity.buttons[0].getText().equals(" ")
                            && !MainActivity.buttons[1].getText().equals(" ")
                            && !MainActivity.buttons[2].getText().equals(" ")
                            && !MainActivity.buttons[3].getText().equals(" ")
                            && !MainActivity.buttons[4].getText().equals(" ")
                            && !MainActivity.buttons[5].getText().equals(" ")
                            && !MainActivity.buttons[6].getText().equals(" ")
                            && !MainActivity.buttons[7].getText().equals(" ")
                            && !MainActivity.buttons[8].getText().equals(" ")
                            ) {
                        DROW:
                        {

                            if (((count_x() == count_o()) && (count_x() != 0 && count_o() != 0)) || count_x() > count_o() || count_o() > count_x()) {

                                new WinnerDialog(this, "DROW", R.drawable.drow1);

                                MainActivity.buttons[0].setText(" ");
                                MainActivity.buttons[1].setText(" ");
                                MainActivity.buttons[2].setText(" ");
                                MainActivity.buttons[3].setText(" ");
                                MainActivity.buttons[4].setText(" ");
                                MainActivity.buttons[5].setText(" ");
                                MainActivity.buttons[6].setText(" ");
                                MainActivity.buttons[7].setText(" ");
                                MainActivity.buttons[8].setText(" ");
                                MainActivity.buttons[0].setEnabled(true);
                                MainActivity.buttons[1].setEnabled(true);
                                MainActivity.buttons[2].setEnabled(true);
                                MainActivity.buttons[3].setEnabled(true);
                                MainActivity.buttons[4].setEnabled(true);
                                MainActivity.buttons[5].setEnabled(true);
                                MainActivity.buttons[6].setEnabled(true);
                                MainActivity.buttons[7].setEnabled(true);
                                MainActivity.buttons[8].setEnabled(true);
                            }

                        }//End DROW
                    }//End IF NOT EMPTY
                }//End NOT_EMPTY
            }//End of Game Friend

        }//End of Else GameMode

    }//End onClick


}

