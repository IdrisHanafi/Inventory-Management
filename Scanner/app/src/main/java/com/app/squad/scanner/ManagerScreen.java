package com.app.squad.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ManagerScreen extends AppCompatActivity implements View.OnClickListener {
    Button bCreateProduct, bDeleteProduct, bAddQuantity, bRemoveQuantity, bModifyProduct, bProductReport, bCostAnalysis, bChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_screen);

        bCreateProduct = (Button) findViewById(R.id.bCreateProduct);
        bCreateProduct.setOnClickListener(this);

        bDeleteProduct = (Button) findViewById(R.id.bDeleteProduct);
        bDeleteProduct.setOnClickListener(this);

        bAddQuantity = (Button) findViewById(R.id.bAddQuantity);
        bAddQuantity.setOnClickListener(this);

        bRemoveQuantity = (Button) findViewById(R.id.bRemoveQuantity);
        bRemoveQuantity.setOnClickListener(this);

        bModifyProduct = (Button) findViewById(R.id.bModifyProduct);
        bModifyProduct.setOnClickListener(this);

        bProductReport = (Button) findViewById(R.id.bProductReport);    // Not finished yet
        bProductReport.setOnClickListener(this);

        bCostAnalysis = (Button) findViewById(R.id.bCostAnalysis);
        bCostAnalysis.setOnClickListener(this);

        bChangePassword = (Button) findViewById(R.id.bChangePassword);
        bChangePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bCreateProduct:
                startActivity(new Intent(this, CreateProductScreen.class));
                break;

            case R.id.bDeleteProduct:
                startActivity(new Intent(this, DeleteProductScreen.class));
                break;

            case R.id.bAddQuantity:
                startActivity(new Intent(this, AddRemoveQuantityScreen.class));
                break;

            case R.id.bModifyProduct:
                startActivity(new Intent(this, ModifyProductScreen.class));
                break;

            case R.id.bRemoveQuantity:
                startActivity(new Intent(this, AddRemoveQuantityScreen.class));
                break;

            case R.id.bCostAnalysis:
                startActivity(new Intent(this, CostAnalysisScreen.class));
                break;

            case R.id.bChangePassword:
                startActivity(new Intent(this, ChangePasswordScreen.class));
                break;
        }
    }
}
