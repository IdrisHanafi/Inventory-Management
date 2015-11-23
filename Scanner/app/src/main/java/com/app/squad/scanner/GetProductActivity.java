package com.app.squad.scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.support.v4.app.ActivityCompat.startActivity;

public class GetProductActivity  extends AsyncTask<String, Object, Object>  {

    private Context context;

    String upcCode;
    String echo;
    private ProgressDialog dialog;
    JSONArray productOutput;
    public AsyncResponse delegate = null;//Call back interface

    public GetProductActivity(Context context, AsyncResponse asyncResponse) {
        this.context = context;
        this.delegate = asyncResponse;
    }

    public GetProductActivity(Context context) {

        this.context = context;
    }

    protected void onPreExecute(){
        dialog = new ProgressDialog(context);
        dialog.setMessage("Please wait...");
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }


    @Override
    protected Boolean doInBackground(String... arg0) {

        try{
            this.upcCode = (String)arg0[0];
            String link="http://192.168.1.8/GetProduct.php";  //This is the IP/Domain name of the server with the PHP
            String data  = URLEncoder.encode("upcCode", "UTF-8") + "=" + URLEncoder.encode(upcCode, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            //  This reads the data coming from the PHP and puts it into a single string
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                sb.append(line.toString());
            }
            this.echo = sb.toString();
            String result = sb.toString();
            JSONObject json = new JSONObject(result);
            int success = json.getInt("success");
            if(success == 1) {
                productOutput = json.getJSONArray("product");
                return true;
            } else {
                return false;
            }

        }
        catch(Exception e){
            return false;
        }
    }

    @Override  // This method occurs after data from the PHP has been returned
    protected void onPostExecute(Object result){
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if((Boolean) result) {
            delegate.processFinish(productOutput);
        } else {
            new AlertDialog.Builder(context)
                    .setTitle("Uh Oh")
                    .setMessage("There is no record for this product!")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        super.onPostExecute(result);
    }


}