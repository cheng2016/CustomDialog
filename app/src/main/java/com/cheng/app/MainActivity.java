package com.cheng.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cheng.app.dialog.ChooseDialog;
import com.cheng.app.dialog.MyAlertDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.alert_dialog_btn).setOnClickListener(this);
        findViewById(R.id.eit_dialog_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alert_dialog_btn:
                MyAlertDialog myAlertDialog = new MyAlertDialog(this);
                myAlertDialog.show();
                break;
            case R.id.eit_dialog_btn:
                new ChooseDialog(this).show();
                break;
        }
    }

    interface OnClickListener {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         *
         * @param dialog the dialog that received the click
         * @param which the button that was clicked (ex.
         *              {@link DialogInterface#BUTTON_POSITIVE}) or the position
         *              of the item clicked
         */
        void onClick(DialogInterface dialog, int which);
    }
}
