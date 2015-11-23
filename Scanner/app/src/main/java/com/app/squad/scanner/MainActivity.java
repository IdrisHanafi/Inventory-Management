package com.app.squad.scanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//This is to test the private branch

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button bLogin;
    UserLocalStorage userLocalStore;
    EditText etUsername, etPassword;
    private TextView errAlert;  // delete this


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);

        userLocalStore = new UserLocalStorage(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // On click event handling
    public void onClick(View v) {

        // Grabs username and password
        errAlert = (TextView) findViewById(R.id.errAlert);
        String inputName = etUsername.getText().toString();
        String inputPass = etPassword.getText().toString();

        // Checks for empty username or password before processing
        if (inputName.matches("") || inputPass.matches("")) {
            new AlertDialog.Builder(this)
                    .setTitle("Sorry")
                    .setMessage("You Forgot to Enter your Username or Password")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        /*
        else if (connection == false){
            new AlertDialog.Builder(this)
                    .setTitle("Sorry")
                    .setMessage("You Forgot to Enter your Username or Password")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }  */
        else {

            switch (v.getId()) {
                case R.id.bLogin:
                    new Login(this, errAlert).execute(inputName, inputPass);
                    //startActivity(new Intent(this, Scan.class));
                    break;

            } // end switch statement

        } // end if/else


    }


}