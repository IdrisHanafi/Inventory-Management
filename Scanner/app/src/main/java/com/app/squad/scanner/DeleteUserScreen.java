package com.app.squad.scanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class DeleteUserScreen extends AppCompatActivity implements View.OnClickListener{
    Button bDeleteUser, bSearchUser;
    EditText etDeleteUser;
    TextView listNames, searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user_screen);

        etDeleteUser = (EditText) findViewById(R.id.etDeleteUser);
        listNames = (TextView) findViewById(R.id.listNames);
        searchResults = (TextView) findViewById(R.id.searchResults);
        bDeleteUser = (Button) findViewById(R.id.bDeleteUser);
        bSearchUser = (Button) findViewById(R.id.bSearchUser);
        bDeleteUser.setOnClickListener(this);
        bSearchUser.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        String userName = etDeleteUser.getText().toString();


        switch (v.getId()) {
            case R.id.bDeleteUser:
                new DeleteUserActivity(this).execute(userName);
                break;

            case R.id.bSearchUser:
                new SearchUsersActivity(this, listNames).execute(userName);
                this.searchResults.setText("Search Results:");
                break;
        }
    }
}
