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

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static android.support.v4.app.ActivityCompat.startActivity;

public class Login extends AsyncTask<String, Void, String[]>  {
    private Context context;
    private TextView errAlert;

    String userName;
    String userPassword;
    String echo;
    String salt;
    String echoPass;
    String privlvl;
    String fullHash;


    public Login(Context context, TextView errAlert) {
        this.context = context;
        this.errAlert = errAlert;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String[] doInBackground(String... arg0) {

        try{
            this.userName = (String)arg0[0];
            this.userPassword = (String)arg0[1];
            String link="http://54.69.210.120/ReadSalt.php";  //This is the IP/Domain name of the server with the PHP
            String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8");
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

            // This splits the string into an array based on delimiter '!!!' (PHP handles that part)
            String[] result = echo.split("!!!");
            this.salt = result[0];
            this.echoPass = result[1];
            this.privlvl = result[2];
            return result;

        }
        catch(Exception e){
            return new String[0];
        }
    }

    @Override  // This method occurs after data from the PHP has been returned
    protected void onPostExecute(String[] result){

        // Start the SHA-256 hash on the user's input password and salt
        String hash = round2();

        // Check if the hash is equal to the stored hash in the database- if yes, proceed.  If no, spit error
        Boolean compare = round3(hash);

        if (compare) {
            if (privlvl.matches("1")) {  // this goes direct into the scanning page for a normal user
                Intent intent = new Intent(context, Scan.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            } else if (privlvl.matches("2")){
                // This should activate the manager's landing page


            } else if (privlvl.matches("3")){ // this is for the Admin's landing page
                Intent intent = new Intent(context, AdminLanding.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                errAlert.setText("There is an error with your user account.  Please contact an administrator");
            }

        }
        else{
            errAlert.setText("Invalid Username or Password");
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
        return String.format("%0" + (data.length*2) + "X", new BigInteger(1, data));
    }
}