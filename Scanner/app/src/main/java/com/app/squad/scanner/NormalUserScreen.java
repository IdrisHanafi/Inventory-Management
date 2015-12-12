package com.app.squad.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class NormalUserScreen extends AppCompatActivity implements View.OnClickListener{
    Button bAddQuantity, bRemoveQuantity, bChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_screen);

        bAddQuantity = (Button) findViewById(R.id.bAddQuantity);    // Not finished yet
        bAddQuantity.setOnClickListener(this);

        bRemoveQuantity = (Button) findViewById(R.id.bRemoveQuantity);  // Not finished yet
        bRemoveQuantity.setOnClickListener(this);

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
            case R.id.bAddQuantity:
                startActivity(new Intent(this, IncreaseQuantityScreen.class));
                break;

            case R.id.bRemoveQuantity:
                startActivity(new Intent(this, DecreaseQuantityScreen.class));
                break;

            case R.id.bChangePassword:
                startActivity(new Intent(this, ChangePasswordScreen.class));
                break;
        }
    }
}
