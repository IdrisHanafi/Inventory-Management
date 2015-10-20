package com.app.squad.scanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Random;

public class RegisterUserScreen extends AppCompatActivity implements View.OnClickListener {
    Button bRegister;
    EditText etFirstname, etLastname,etUsername,etPassword,etConfirmPassword;
    String newSalt;
    CheckBox chkManager;
    Integer newPriv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_screen);

        etFirstname = (EditText) findViewById(R.id.etFirstname);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        chkManager = (CheckBox) findViewById(R.id.chkManager);

        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);

        UserLocalStorage userLocalStore = new UserLocalStorage(this);

    }

    @Override
    public void onClick(View v) {
        String newUserName = etUsername.getText().toString();
        String newLastName = etLastname.getText().toString();
        String newFirstName = etFirstname.getText().toString();
        String newPassword = etPassword.getText().toString();
        String newConfirmPass = etConfirmPassword.getText().toString();
        newSalt = createSalt();
        if (chkManager.isChecked()){
            newPriv = 2;
        } else{
            newPriv = 1;
        }

        if (newUserName.matches("") || newFirstName.matches("") || newLastName.matches("") || newPassword.matches("") || newConfirmPass.matches("")){
            new AlertDialog.Builder(this)
                    .setTitle("Sorry")
                    .setMessage("You Left a Required Field Blank")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if (!newPassword.matches(newConfirmPass)){
            new AlertDialog.Builder(this)
                    .setTitle("Sorry")
                    .setMessage("The Passwords You Entered Do Not Match")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {

            switch (v.getId()) {
                case R.id.bRegister:

                    break;

            } // end switch statement

        } // end if/else


    }

    private static final String ALLOWED_CHARACTERS ="0123456789!@#$%^&*ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static String createSalt(){
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(12);
        for(int i=0;i<12;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
}
