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

import org.json.JSONObject;

import static android.support.v4.app.ActivityCompat.startActivity;

public class ModifyProductActivity  extends AsyncTask<String, Void, Boolean>  {

    private Context context;

    String description;
    String upcCode;
    String wholesalePrice;
    String retailPrice;
    String location;
    String echo;
    private ProgressDialog dialog;

    public ModifyProductActivity(Context context) {

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
            this.description = (String)arg0[1];
            this.wholesalePrice = (String)arg0[2];
            this.retailPrice = (String)arg0[3];
            this.location = (String)arg0[4];
            String link="http://54.69.210.120/ModifyProduct.php";  //This is the IP/Domain name of the server with the PHP
            String data  = URLEncoder.encode("upcCode", "UTF-8") + "=" + URLEncoder.encode(upcCode, "UTF-8");
            data  += "&" +URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8");
            data  += "&" +URLEncoder.encode("wholesalePrice", "UTF-8") + "=" + URLEncoder.encode(wholesalePrice, "UTF-8");
            data  += "&" +URLEncoder.encode("retailPrice", "UTF-8") + "=" + URLEncoder.encode(retailPrice, "UTF-8");
            data  += "&" +URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8");
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
    protected void onPostExecute(Boolean result){
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if(result) {
            new AlertDialog.Builder(context)
                    .setTitle("All Set")
                    .setMessage("Product Successfully Modified!")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            /*Intent intent = new Intent(context, ManagerScreen.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);*/
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            new AlertDialog.Builder(context)
                    .setTitle("Uh Oh")
                    .setMessage("There was an Error, Product cannot be modified!")
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