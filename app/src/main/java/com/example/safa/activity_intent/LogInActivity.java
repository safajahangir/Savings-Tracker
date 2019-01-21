package com.example.safa.activity_intent;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Loginbutton;
    private EditText Loguser,LogPassword;
    private TextView Accountid,ForgotID;
    private AlertDialog.Builder alertDialogBuilder;

    private EditText input,input2;


    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

        Loginbutton=(Button) findViewById(R.id.LogInId);
        Loguser=(EditText) findViewById(R.id.LogInUsernameEdittext);
        LogPassword=(EditText) findViewById(R.id.loginpasswordEditText);
        Accountid=(TextView) findViewById(R.id.newAccountid);
        ForgotID=(TextView) findViewById(R.id.Forgotid);

        Loginbutton.setOnClickListener(this);
        Accountid.setOnClickListener(this);
        ForgotID.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        final String username=Loguser.getText().toString();
        String password=LogPassword.getText().toString();


        if(v.getId()==R.id.LogInId){

            if(username.equals(""))
            {
                Loguser.setError("The filed can't be empty");
            }
            if(LogPassword.equals(""))
            {
                LogPassword.setError("This field can't be empty");
            }

            Boolean Result=databaseHelper.findpassword(username,password);
            if(Result==true)
            {
                Toast.makeText(getApplicationContext(),"Sign up is successful! ",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Wrong username and password ",Toast.LENGTH_SHORT).show();


            }

        }

        else if(v.getId()==R.id.newAccountid)
        {
            Intent intent= new Intent(LogInActivity.this,Sign_Up_page.class);
            startActivity(intent);
        }

        //Toast.makeText(getApplicationContext(),"Sign up is successful! ",Toast.LENGTH_SHORT).show();


        else if(v.getId()==R.id.Forgotid)
        {
            if(username.equals(""))
            {
                Toast.makeText(getApplicationContext(),"put username first !",Toast.LENGTH_SHORT).show();
            }
            else {
                //Toast.makeText(getApplicationContext(),"Clicked !",Toast.LENGTH_SHORT).show();
                alertDialogBuilder = new AlertDialog.Builder(this);

                ///title
                alertDialogBuilder.setTitle("Password Recovery ");

                /// message
                alertDialogBuilder.setMessage("What is your father's name?");

                ///icon
                alertDialogBuilder.setIcon(R.drawable.question);

                input = new EditText(this);
                alertDialogBuilder.setView(input);

                alertDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String father = input.getText().toString();


                        Boolean Result=databaseHelper.findfathername(username,father);
                        if(Result==true)
                        {
                            //Toast.makeText(getApplicationContext(),"Password recovery ",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                       alertDialogBuilder=new AlertDialog.Builder(LogInActivity.this);
                            alertDialogBuilder.setTitle("Password Recovery");
                            alertDialogBuilder.setMessage("New Password");
                            final EditText recovery=new EditText(LogInActivity.this);
                            alertDialogBuilder.setView(recovery);

                            alertDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String newpassword=recovery.getText().toString();

                                    if(newpassword.equals(""))
                                    {
                                        recovery.setError("The field can't be empty");
                                    }
                                    else {


                                        Boolean result = databaseHelper.newPassword(username, newpassword);
                                        if (result == true)
                                            Toast.makeText(getApplicationContext(), "Password recovery " + newpassword, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });



                            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });





                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialogBuilder.show();



                        }





                        else
                        {
                            Toast.makeText(getApplicationContext(),"Wrong username ! ",Toast.LENGTH_SHORT).show();


                        }


                     //   Toast.makeText(getApplicationContext(), " " + txt, Toast.LENGTH_SHORT).show();

                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialogBuilder.show();
            }

        }
    }
}


/*
package com.example.safa.alertdialog;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private TextView ForgetTExt;
    private AlertDialog.Builder alertDialogBuilder;

    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ForgetTExt = (TextView) findViewById(R.id.forgotid);

        ForgetTExt.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {


        //Toast.makeText(getApplicationContext(),"Clicked !",Toast.LENGTH_SHORT).show();
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        ///title
        alertDialogBuilder.setTitle("Password Recovery ");

        /// message
        alertDialogBuilder.setMessage("What is your father's name?");

        ///icon
        alertDialogBuilder.setIcon(R.drawable.faq);

        input=new EditText(this);
        alertDialogBuilder.setView(input);

        alertDialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txt=input.getText().toString();
                Toast.makeText(getApplicationContext()," "+txt,Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialogBuilder.show();

    }
}
*/