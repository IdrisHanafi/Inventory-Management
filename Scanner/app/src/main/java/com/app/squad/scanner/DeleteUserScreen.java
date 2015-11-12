package com.app.squad.scanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteUserScreen extends AppCompatActivity implements View.OnClickListener{
    Button bDeleteUser;
    EditText etDeleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user_screen);

        etDeleteUser = (EditText) findViewById(R.id.etDeleteUser);
        bDeleteUser = (Button) findViewById(R.id.bDeleteUser);
        bDeleteUser.setOnClickListener(this);




    }
    @Override
    public void onClick(View v) {

        String userName = etDeleteUser.getText().toString();
        switch (v.getId()) {
            case R.id.bDeleteUser:
                new DeleteUserActivity(this).execute(userName);
                break;

        } // end switch statement

    }
}
