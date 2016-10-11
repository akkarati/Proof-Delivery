package com.hitachi_tstv.yodpanom.yaowaluk.proofdelivery;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by akkarati on 2016-10-11.
 */

public class MyAlert {
    //Explict
    private Context context;

    public MyAlert(Context context){
        this.context =context;
    }
    //Dialog for Error
    public void myErrorDialog(int intIcon,
                              String strTitle,
                              String strMessage){
        AlertDialog.Builder builder = new AlertDialog().Builder(context);
        builder.setCancelable(false);
        builder.setIcon(intIcon);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(View view){
                userString = userEdi
            }
        })
    }
}
