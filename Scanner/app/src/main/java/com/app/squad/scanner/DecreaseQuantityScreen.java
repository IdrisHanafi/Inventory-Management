package com.app.squad.scanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONObject;

public class DecreaseQuantityScreen extends AppCompatActivity implements View.OnClickListener {

    EditText etProductName;
    EditText etDescription;
    EditText etUPCCode;
    EditText etCurrentQuantity;
    EditText etDecreaseQuantity;
    Integer quantitySold;
    Button bDecreaseQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrease_quantity_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_decrease_quantity_screen, menu);
        etProductName = (EditText) findViewById(R.id.etDecreaseProductName);
        etDescription = (EditText) findViewById(R.id.etDecreaseDescription);
        etUPCCode = (EditText) findViewById(R.id.etDecreaseUPCCode);
        etCurrentQuantity = (EditText) findViewById(R.id.etDCurrentQuantity);
        etDecreaseQuantity = (EditText) findViewById(R.id.etDecreaseQuantity);
        bDecreaseQuantity = (Button) findViewById(R.id.bDecreaseQuantity);
        bDecreaseQuantity.setOnClickListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_scan) {
            scanNow();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void scanNow(){
        /*
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setResultDisplayDuration(0);
        integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
        */
        GetProductActivity asyncTask = new GetProductActivity(this, new AsyncResponse() {

            @Override
            public void processFinish(Object output) {
                try {
                    JSONArray products = (JSONArray) output;
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        etProductName.setText(c.getString("productName"));
                        etDescription.setText(c.getString("description"));
                        etCurrentQuantity.setText(c.getString("quantity"));
                        quantitySold = Integer.parseInt(c.getString("quantitySold"));

                    }
                }
                catch(Exception e){
                }
            }
        });
        //String newUPCCode = etUPCCode.getText().toString();
        asyncTask.execute(etUPCCode.getText().toString());
    }

    /**
     * function handle scan result
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
//we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            etUPCCode.setText(scanContent);

        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onClick(View view) {
        String upcCode = etUPCCode.getText().toString();
        Integer currentQuantity = Integer.parseInt(etCurrentQuantity.getText().toString());
        Integer decreaseQuantity = Integer.parseInt(etDecreaseQuantity.getText().toString());

        if( etDecreaseQuantity.getText().toString().matches("") ) {
            new AlertDialog.Builder(this)
                    .setTitle("Sorry")
                    .setMessage("You Left a Required Field Blank")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if (currentQuantity < decreaseQuantity) {
            new AlertDialog.Builder(this)
                    .setTitle("Sorry")
                    .setMessage("Cannot decrease quantity than the current quantity!")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {

            //           Log.i("Everything", newFirstName + newLastName + newUserName + newSalt + hashWord + newPriv);
            currentQuantity -= decreaseQuantity;
            quantitySold += decreaseQuantity;
            new ModifyQuantityActivity(this).execute(upcCode, currentQuantity.toString(), quantitySold.toString());

        } // end if/else

    }

}
