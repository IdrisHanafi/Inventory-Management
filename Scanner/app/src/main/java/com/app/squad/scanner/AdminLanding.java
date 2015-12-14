package com.app.squad.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminLanding extends AppCompatActivity implements View.OnClickListener{
    Button bChangePassword, bLogOut;
    String getUserInfo;
    TextView tName;
    String userName;
    String privilege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing);

        Intent intent = getIntent();
        getUserInfo = intent.getExtras().getString("userInfo");
        String[] userResults = getUserInfo.split(" ");

        tName = (TextView) findViewById(R.id.tName);
        tName.setText(userResults[0] + " " + userResults[1]);

        privilege = userResults[2];
        userName = userResults[3];

        bLogOut = (Button) findViewById(R.id.bLogOut);
        bLogOut.setOnClickListener(this);

        bChangePassword = (Button) findViewById(R.id.bChangePassword);
        bChangePassword.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            /*
            case R.id.action_createproduct:
                startActivity(new Intent(this, CreateProductScreen.class));
                break;

            case R.id.action_modifyproduct:
                startActivity(new Intent(this, ModifyProductScreen.class));
                break;

            case R.id.action_deleteproduct:
                startActivity(new Intent(this, DeleteProductScreen.class));
                break;

            case R.id.action_increasequantity:
                startActivity(new Intent(this, IncreaseQuantityScreen.class));
                break;

            case R.id.action_decreasequantity:
                startActivity(new Intent(this, DecreaseQuantityScreen.class));
                break;
            */

            case R.id.action_costanalysis:
                startActivity(new Intent(this, CostAnalysisScreen.class));
                break;

            case R.id.action_registeruser:
                startActivity(new Intent(this, RegisterUserScreen.class));
                break;

            case R.id.action_deleteuser:
                startActivity(new Intent(this, DeleteUserScreen.class));
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
                Intent intent = new Intent(this, ChangePasswordScreen.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("userName", userName);
                intent.putExtra("privilege", privilege);
                this.startActivity(intent);
                break;
        }
    }
}