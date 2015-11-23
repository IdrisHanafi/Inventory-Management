package com.app.squad.scanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ManagerScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_screen);
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

        //noinspection SimplifiableIfStatement
        switch(id) {
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
                startActivity(new Intent(this, CreateProductScreen.class));
                break;
            case R.id.action_decreasequantity:
                startActivity(new Intent(this, CreateProductScreen.class));
                break;
            case R.id.action_listproduct:
                break;
            case R.id.action_costanalysis:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
