package com.app.squad.scanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AsyncTask<String, Void, String[]>  {
    private Context context;
    private TextView errAlert;

    String userName;
    String userPassword;
    String echo;
    String fName;
    String lName;
    String salt;
    String echoPass;
    String privlvl;
    String fullHash;
    String firstTime;

    public Login(Context context, TextView errAlert) {
        this.context = context;
        this.errAlert = errAlert;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String[] doInBackground(String... arg0) {

        try {
            this.userName = (String) arg0[0];
            this.userPassword = (String) arg0[1];
            String link = "http://54.69.210.120/ReadSalt.php";  //This is the IP/Domain name of the server with the PHP
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            //  This reads the data coming from the PHP and puts it into a single string
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line.toString());
            }
            this.echo = sb.toString();
            // This splits the string into an array based on delimiter '!!!' (PHP handles that part)
            String[] result = echo.split("!!!");
            if (result.length > 4) {
                this.fName = result[0];
                this.lName = result[1];
                this.salt = result[2];
                this.echoPass = result[3];
                this.privlvl = result[4];
                this.firstTime = result[5];
                return result;
            } else {
                // modifies error returned from PHP/ MySQL
                result[0] = "error";
                return result;
            }
        }
        catch(Exception e){
            return new String[0];
        }
    }

    @Override  // This method occurs after data from the PHP has been returned
    protected void onPostExecute(String[] result){
        String getUserInfo = "";
        if (result[0] == "error"){
            // username does not exist
            notification("Wrong User Name/Password", "Incorrect user name or password");
        }else {
            // Start the SHA-256 hash on the user's input password and salt
            String hash = round2();

            // Check if the hash is equal to the stored hash in the database- if yes, proceed.  If no, spit error
            Boolean compare = round3(hash);

            if (compare) {


                // Need to put in the (if firstTime = 1, change password) logic


                 if (privlvl.matches("1")) {  // this goes direct into the scanning page for a normal user
                    Intent intent = new Intent(context, NormalUserScreen.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     getUserInfo = result[0] + " " + result[1] + " Normal";
                     intent.putExtra("userInfo", getUserInfo);
                    context.startActivity(intent);

                } else if (privlvl.matches("2")){
                    // This should activate the manager's landing page
                    Intent intent = new Intent(context, ManagerScreen.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     getUserInfo = result[0] + " " + result[1] + " Manager";
                     intent.putExtra("userInfo", getUserInfo);
                    context.startActivity(intent);
                } else if (privlvl.matches("3")){ // this is for the Admin's landing page
                    Intent intent = new Intent(context, AdminLanding.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     getUserInfo = result[0] + " " + result[1] + " Administrator";
                     intent.putExtra("userInfo", getUserInfo);
                    context.startActivity(intent);
                } else {
                    // User privilege is not set
                    notification("Error", "There is an error with your account.  Please contact an administrator");
                }

            }
            else{
                // Wrong password notification
                notification("Wrong User Name/Password", "Incorrect user name or password");
            }
        }

    }


    // Round 2 takes the salt from he database and user's input password and hashes them via SHA-256
    public String round2(){
        this.fullHash = bin2hex(getHash(salt+userPassword));
        return fullHash;
    }

    // Round 3 compares the user's input hash with the hash in the DB to check for equality.
    public Boolean round3(String hash){
        if (echoPass.matches(hash)){
            return true;
        }
        else{
            return false;
        }
    }

    // converts the password string to SHA-256 (output is in binary)
    public byte[] getHash(String password){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("Sha-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.reset();
        return digest.digest(password.getBytes());

    }

    // converts the binary from getHash() to hex
    static String bin2hex(byte[] data){
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
    }

    // Method for creating pop up notifications.
    protected void notification(String title, String message){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}