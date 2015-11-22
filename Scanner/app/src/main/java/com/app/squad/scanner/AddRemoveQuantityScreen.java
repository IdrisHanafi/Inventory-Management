package com.app.squad.scanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRemoveQuantityScreen extends AppCompatActivity implements View.OnClickListener{
    Button bUpdateProductQuantity;
    EditText etAddQuantity, etRemoveQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_quantity_screen);

        etAddQuantity = (EditText) findViewById(R.id.etAddQuantity);
        etRemoveQuantity = (EditText) findViewById(R.id.etRemoveQuantity);
        bUpdateProductQuantity = (Button) findViewById(R.id.bUpdateProductQuantity);    // Not finished yet
        bUpdateProductQuantity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String addQuantity = etAddQuantity.getText().toString();
        String removeQuantity = etRemoveQuantity.getText().toString();
        //new AddRemoveQuantityActivity(this).execute(addQuantity, removeQuantity);
    }
}
