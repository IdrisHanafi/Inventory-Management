package com.app.squad.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AdminLanding extends AppCompatActivity implements View.OnClickListener{
    Button bAddUser, bDeleteUser, bProductReport, bCostAnalysis, bChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing);

        bAddUser = (Button) findViewById(R.id.bAddUser);
        bAddUser.setOnClickListener(this);

        bDeleteUser = (Button) findViewById(R.id.bDeleteUser);
        bDeleteUser.setOnClickListener(this);

        bProductReport = (Button) findViewById(R.id.bProductReport);    // Not finished yet
        bProductReport.setOnClickListener(this);

        bCostAnalysis = (Button) findViewById(R.id.bCostAnalysis);
        bCostAnalysis.setOnClickListener(this);

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
            case R.id.bChangePassword:
                startActivity(new Intent(this, ChangePasswordScreen.class));
                break;
        }
    }
}
