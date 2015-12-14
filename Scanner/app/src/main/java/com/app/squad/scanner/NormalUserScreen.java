package com.app.squad.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NormalUserScreen extends AppCompatActivity implements View.OnClickListener{
    Button bChangePassword, bLogOut;
    String getUserInfo;
    TextView tName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_screen);

        Intent intent = getIntent();
        getUserInfo = intent.getExtras().getString("userInfo");
        String[] userResults = getUserInfo.split(" ");

        tName = (TextView) findViewById(R.id.tName);
        tName.setText(userResults[0] + " " + userResults[1]);

        bLogOut = (Button) findViewById(R.id.bLogOut);
        bLogOut.setOnClickListener(this);

        bChangePassword = (Button) findViewById(R.id.bChangePassword);
        bChangePassword.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_normal_user_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_increasequantity:
                startActivity(new Intent(this, IncreaseQuantityScreen.class));
                break;

            case R.id.action_decreasequantity:
                startActivity(new Intent(this, DecreaseQuantityScreen.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bLogOut:
                finish();
                break;

            case R.id.bChangePassword:
                startActivity(new Intent(this, ChangePasswordScreen.class));
                break;
        }
    }
}
