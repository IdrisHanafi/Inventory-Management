package com.app.squad.scanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DeleteProductScreen extends AppCompatActivity implements View.OnClickListener{
    Button bDeleteProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product_screen);

        bDeleteProduct = (Button) findViewById(R.id.bDeleteProduct);
        bDeleteProduct.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        //new DeleteProductActivity(this).execute(productName);
    }
}
