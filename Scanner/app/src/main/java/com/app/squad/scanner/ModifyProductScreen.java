package com.app.squad.scanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyProductScreen extends AppCompatActivity implements View.OnClickListener{
    Button bUpdateProductInfo;
    EditText etProductName, etProductDescription, etProductPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_product_screen);

        etProductName = (EditText) findViewById(R.id.etProductName);
        etProductDescription = (EditText) findViewById(R.id.etProductDescription);
        etProductPrice = (EditText) findViewById(R.id.etProductPrice);
        bUpdateProductInfo = (Button) findViewById(R.id.bUpdateProductInfo);    // Not finished yet
        bUpdateProductInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String productName = etProductName.getText().toString();
        String productDescription = etProductDescription.getText().toString();
        String productPrice = etProductPrice.getText().toString();
        //new ModifyProductScreenActivity(this).execute(productName, productDescription, productPrice);
    }
}
