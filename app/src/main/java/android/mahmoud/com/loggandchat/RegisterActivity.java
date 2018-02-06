package android.mahmoud.com.loggandchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;

    private Toolbar mToolbar;

    private EditText RegisterUserName;    //declare register name
    private EditText RegisterUserEmail;   //declare register email
    private EditText RegisterUserPassword;  //declare register password
    private Button CreateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();


        // to show toolbar in register activity
        mToolbar=(Toolbar)findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("sign up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // to show arrow at top of activity

        RegisterUserName=(EditText)findViewById(R.id.register_name);   // bind register name
        RegisterUserEmail=(EditText)findViewById(R.id.register_email); // bind register email
        RegisterUserPassword=(EditText)findViewById(R.id.register_password); // bind register password
        CreateAccountButton=(Button)findViewById(R.id.create_account_button);
        loadingBar=new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=RegisterUserName.getText().toString();    // get name text from user
                String email=RegisterUserEmail.getText().toString();  // get user text from user
                String password=RegisterUserPassword.getText().toString(); // get password from user

                RegisterAccount(name,email, password);

            }
        });
    }

    private void RegisterAccount(String name,String email,String password) {

        if(TextUtils.isEmpty(name)) //to ensure user enter his name
         {
            Toast.makeText(RegisterActivity.this,"please enter your name",Toast.LENGTH_LONG).show(); //display message to user if name not found
         }

        if(TextUtils.isEmpty(email)) //to ensure user enter his name
        {
            Toast.makeText(RegisterActivity.this,"please enter your E_mail",Toast.LENGTH_LONG).show(); //display message to user if email not found
        }

        if(TextUtils.isEmpty(password)) //to ensure user enter his name
        {
            Toast.makeText(RegisterActivity.this,"please enter your password",Toast.LENGTH_LONG).show(); //display message to user if password not found
        }
        else
        {
            loadingBar.setTitle("Creating new account");
            loadingBar.setMessage("please wait,while we creating account");
            loadingBar.show();
        /*
        that we enable it from fire base site so we write email and password for sign up
         */
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // if this process success switch to main activity
                    if(task.isSuccessful()){
                        Intent mainIntent=new Intent(RegisterActivity.this,MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Error Occured,Try Again",Toast.LENGTH_LONG).show();
                    }
                    loadingBar.dismiss();

                }
            });
        }
    }
}
