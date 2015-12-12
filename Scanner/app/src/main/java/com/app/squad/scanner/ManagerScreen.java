package com.app.squad.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManagerScreen extends AppCompatActivity implements View.OnClickListener {
    Button bCreateProduct, bDeleteProduct, bAddQuantity, bRemoveQuantity, bModifyProduct, bProductReport, bCostAnalysis, bChangePassword, bLogOut;
    String getUserInfo;
    TextView tName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_screen);

        Intent intent = getIntent();
        getUserInfo = intent.getExtras().getString("userInfo");
        String[] userResults = getUserInfo.split(" ");

        tName = (TextView) findViewById(R.id.tName);
        tName.setText(userResults[0] + " " + userResults[1]);

        // Inflate the menu; this adds items to the action bar if it is present.
        /**bCreateProduct = (Button) findViewById(R.id.bCreateProduct);
        bCreateProduct.setOnClickListener(this);

        bModifyProduct = (Button) findViewById(R.id.bModifyProduct);
        bModifyProduct.setOnClickListener(this);

        bDeleteProduct = (Button) findViewById(R.id.bDeleteProduct);
        bDeleteProduct.setOnClickListener(this);

        bAddQuantity = (Button) findViewById(R.id.bAddQuantity);
        bAddQuantity.setOnClickListener(this);

        bRemoveQuantity = (Button) findViewById(R.id.bRemoveQuantity);
        bRemoveQuantity.setOnClickListener(this);

        bProductReport = (Button) findViewById(R.id.bProductReport);    // Not finished yet
        bProductReport.setOnClickListener(this);

        bCostAnalysis = (Button) findViewById(R.id.bCostAnalysis);
        bCostAnalysis.setOnClickListener(this);*/

        bLogOut = (Button) findViewById(R.id.bLogOut);
        bLogOut.setOnClickListener(this);

        bChangePassword = (Button) findViewById(R.id.bChangePassword);
        bChangePassword.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manager_screen, menu);
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
