package com.app.squad.scanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class RegisterUserScreen extends AppCompatActivity implements View.OnClickListener {
    Button bRegister;
    EditText etFirstname, etLastname,etUsername,etPassword,etConfirmPassword;
    CheckBox chkManager, chkAdmin;
    String newPriv;

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
        chkAdmin = (CheckBox) findViewById(R.id.chkAdmin);

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

        String newSalt = createSalt();
        String hashWord = bin2hex(getHash(newSalt + newPassword));

        if (chkManager.isChecked()){
            newPriv = "2";
        } else if (chkAdmin.isChecked()) {
            newPriv = "3";
        } else {
            newPriv = "1";
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
        }
        else if (!newUserName.matches("[a-zA-Z0-9.]*") || !newFirstName.matches("[a-zA-Z0-9.?]*")|| !newLastName.matches("[a-zA-Z0-9.?]*") || !newPassword.matches("[a-zA-Z0-9.?]*") || !newConfirmPass.matches("[a-zA-Z0-9.?]*")){
            new AlertDialog.Builder(this)
                    .setTitle("Sorry")
                    .setMessage("You Entered an invalid character")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {

            switch (v.getId()) {
                case R.id.bRegister:
                    Log.i("Everything", newFirstName+ newLastName+ newUserName+ newSalt+  hashWord+ newPriv);
                    new CreateUserActivity(this).execute(newFirstName, newLastName, newUserName, newSalt,  hashWord, newPriv);
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

    // converts the password string to SHA-256 (output is in binary)
    public byte[] getHash(String password){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("Sha-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.reset();
        return digest.digest(password.getBytes());

    }

    // converts the binary from getHash() to hex
    static String bin2hex(byte[] data){
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
    }
}

