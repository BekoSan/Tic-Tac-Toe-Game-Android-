package com.bekosansoftwares.tictactoe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Class to setup a winner dialog
 */
public class WinnerDialog extends Dialog implements View.OnClickListener {

    /**
     * Button to confirm the message
     */
    public Button btnOk;

    /**
     * ImageView to display Image
     */
    public ImageView imgc;

    /**
     * Default Constructor
     *
     * @param context     The context used to show the dialog
     * @param dialogTitle The dialog title
     * @param img         The R.id for the image
     */
    public WinnerDialog(Context context, String dialogTitle, int img) {
        super(context);

        setTitle(dialogTitle);
        setContentView(R.layout.winner_dialog);


        imgc = (ImageView) findViewById(R.id.winnertype);
        btnOk = (Button) findViewById(R.id.acceptbtn);

        /**
         * Set click listener for btnOk
         */
        btnOk.setOnClickListener(this);

        switch (img) {
            case R.drawable.grin:
                imgc.setImageResource(img);
                break;
            case R.drawable.slow:
                imgc.setImageResource(img);
                break;
            case R.drawable.drow1:
                imgc.setImageResource(img);
                break;
        }

        show();

    }/*End of Constructor */


    @Override
    public void onClick(View view) {
        hide();
    }
}
