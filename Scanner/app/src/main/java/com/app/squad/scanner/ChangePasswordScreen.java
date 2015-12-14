package com.app.squad.scanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class ChangePasswordScreen extends AppCompatActivity implements View.OnClickListener{
    Button bChangePassword, bSearchUser;
    EditText etChangeUser, etPassword, etConfirmPassword;
    String newSalt;
    String newPassword;
    String userName;
    TextView listNames, searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_screen);

        etChangeUser = (EditText) findViewById(R.id.etChangeUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        listNames = (TextView) findViewById(R.id.listNames);
        searchResults = (TextView) findViewById(R.id.searchResults);
        bChangePassword = (Button) findViewById(R.id.bChangePassword);
        bChangePassword.setOnClickListener(this);
        bSearchUser = (Button) findViewById(R.id.bSearchUser);
        bSearchUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String hashWord = bin2hex(getHash(newSalt + newPassword));
        this.userName = etChangeUser.getText().toString();
        this.newPassword = etPassword.getText().toString();
        String newConfirm = etConfirmPassword.getText().toString();
        newSalt = createSalt();

        if (v.getId() == (R.id.bSearchUser) && !userName.matches("")) {
            new SearchUsersActivity(this, listNames).execute(userName);
            this.searchResults.setText("Search Results:");

        }else if (userName.matches("") || newPassword.matches("") || newConfirm.matches("")) {
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
        }
        else if (!newPassword.matches(newConfirm)){
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
        else if (!newPassword.matches("[a-zA-Z0-9.]*") || !newConfirm.matches("[a-zA-Z0-9.?]*")){
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
                case R.id.bChangePassword:
                    new ChangePasswordActivity(this).execute(userName, newSalt, hashWord);
                    break;
                case R.id.bSearchUser:
                    new SearchUsersActivity(this, listNames).execute(userName);
                    this.searchResults.setText("Search Results:");
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