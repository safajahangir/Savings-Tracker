package com.example.safa.activity_intent;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_Up_page extends AppCompatActivity implements View.OnClickListener {

    private Button signupbutton;
    private TextView doneTextView;
    private EditText userSignup,passwordSignUp,phoneSignUp,nameSignUp,fatherSignup;
    DatabaseHelper databaseHelper;

    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up_page);
        nameSignUp=(EditText) findViewById(R.id.nameEditText);
        fatherSignup=(EditText) findViewById(R.id.fathernameEditText);
        phoneSignUp=(EditText) findViewById(R.id.phonenuberEditText);
        userSignup=(EditText) findViewById(R.id.usernameEditText);
        passwordSignUp=(EditText) findViewById(R.id.passwordEditText);

        signupbutton =(Button) findViewById(R.id.SignUpID);

        signupbutton.setOnClickListener(this);

        userDetails=new UserDetails();

        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase= databaseHelper.getWritableDatabase();

    }

    @Override
    public void onClick(View v) {

        String name=nameSignUp.getText().toString();
        String father=fatherSignup.getText().toString();
        String phone=phoneSignUp.getText().toString();
        String user=userSignup.getText().toString();
        String password=passwordSignUp.getText().toString();

        userDetails.setName(name);
        userDetails.setFatherName(father);
        userDetails.setPhoneNumber(phone);
        userDetails.setUsername(user);
        userDetails.setPassword(password);

        long rowvalue=databaseHelper.insertData(userDetails);
        if(rowvalue>0)
        {
            Toast.makeText(getApplicationContext(),"Row inserted : "+rowvalue,Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"unsucessful to insert row",Toast.LENGTH_SHORT).show();

        }

    }
}
