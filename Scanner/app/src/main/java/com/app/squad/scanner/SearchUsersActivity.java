package com.app.squad.scanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SearchUsersActivity extends AsyncTask<String, Void, String[]>{
    private Context context;
    private TextView listNames;
    private String userName;
    public String echo, name1, name2, name3, name4;

    public SearchUsersActivity(Context context, TextView listNames) {
        this.context = context;
        this.listNames = listNames;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String[] doInBackground(String... arg0) {

        try {
            this.userName = (String) arg0[0];
            String link = "http://54.69.210.120/SearchUsers.php";  //This is the IP/Domain name of the server with the PHP
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
            Log.i("RESULTS", echo);

            // This splits the string into an array based on delimiter '!!!' (PHP handles that part)
            String[] result = echo.split("!!!");
            if (result[0].contains("I/")){
                result[0] = "error";
                return result;
            }

            else if (result.length > 0) {
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
        if (result[0] == "error"){
            // username does not exist
            listNames.setText("No results");
            notification("Sorry", "No users match that query");
        }else{
            listNames.setText("");
            for(int i = 0; i < result.length; i++) {
                listNames.append(result[i] + "\n");

            }
        }

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
