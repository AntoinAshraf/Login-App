package com.example.loginapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BackGroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    String type = "";
    String algorithm = "";
    HttpURLConnection httpURLConnection = null;
    OutputStream outputStream = null;
    BufferedWriter bufferedWriter = null;
    InputStream inputStream = null;
    BufferedReader bufferedReader = null;

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    BackGroundWorker (Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String Request_url = "";
        algorithm = "SHA-256";
        if(type.equals("login")){
            Request_url = "http://192.168.1.7/AndroidAppDatabase/login.php";

            return onLoginExecute(params[2], params[1], Request_url);

        }else if(type.equals("register")){
            Request_url = "http://192.168.1.7/AndroidAppDatabase/register.php";

            return onRegisterExecute(params[1], params[2], params[3], params[4], params[5], params[7], params[6], Request_url, params[8]);

        }

        return null;
    }

    private String onLoginExecute(String password, String username, String Request_url){
        try{
            String encrepted_password = generateHash(password, algorithm);

            URL url = new URL(Request_url);

            openConnection(url);

            String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8");
            post_data += "&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(encrepted_password, "UTF-8");

            String result = getResponceFromConnection(post_data);

            closeConnection();

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String onRegisterExecute(String Fname, String Lname, String PhoneNumber, String Address, String age, String Email, String SelectedUserType, String Request_url, String password){
        try{
            String encrepted_password = generateHash(password, algorithm);

            URL url = new URL(Request_url);

            String addPermission = "";
            String sellPermission = "";
            if(SelectedUserType.equals("Manager")){
                addPermission = "1";
                sellPermission = "1";
            } else{
                addPermission = "0";
                sellPermission = "0";
            }

            openConnection(url);

            String post_data = URLEncoder.encode("f_name", "UTF-8")+"="+URLEncoder.encode(Fname, "UTF-8");
            post_data += "&"+URLEncoder.encode("l_name", "UTF-8")+"="+URLEncoder.encode(Lname, "UTF-8");
            post_data += "&"+URLEncoder.encode("phoneNum", "UTF-8")+"="+URLEncoder.encode(PhoneNumber, "UTF-8");
            post_data += "&"+URLEncoder.encode("address", "UTF-8")+"="+URLEncoder.encode(Address, "UTF-8");
            post_data += "&"+URLEncoder.encode("age", "UTF-8")+"="+URLEncoder.encode(age, "UTF-8");
            post_data += "&"+URLEncoder.encode("type_number", "UTF-8")+"="+URLEncoder.encode(SelectedUserType, "UTF-8");
            post_data += "&"+URLEncoder.encode("add_perm", "UTF-8")+"="+URLEncoder.encode(addPermission, "UTF-8");
            post_data += "&"+URLEncoder.encode("sell_perm", "UTF-8")+"="+URLEncoder.encode(sellPermission, "UTF-8");
            post_data += "&"+URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(Email, "UTF-8");
            post_data += "&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(encrepted_password, "UTF-8");

            String result = getResponceFromConnection(post_data);

            closeConnection();

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void openConnection(URL url) throws IOException {
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);

        outputStream = httpURLConnection.getOutputStream();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
    }

    private String getResponceFromConnection(String post_data) throws IOException {
        bufferedWriter.write(post_data);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();

        inputStream = httpURLConnection.getInputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

        String result = "";
        String line = "";
        while((line = bufferedReader.readLine()) != null){
            result += line;
        }
        return result;
    }

    private void closeConnection() throws IOException {
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        if(type.equals("login")){
            String[] results = result.split("&");
            if(results[0].equals("success")) {

                Toast.makeText(context, " Welcome " + results[5], Toast.LENGTH_SHORT).show();
                Intent launch = new Intent(context, InfoPageActivity.class);

                launch.putExtra("PersonData", results);

                context.startActivity(launch);

            } else if(result.equals("failed")){
                Toast.makeText(context, " Failed, Check username and password", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
        } else if(type.equals("register")){
            if(result.equals("Success")) {
                Toast.makeText(context, "Registerd Successfuly\nCheck your mail to verify your account", Toast.LENGTH_LONG).show();
                Intent launch = new Intent(context, MainActivity.class);
                context.startActivity(launch);
            } else if(result.equals("User Exists")){
                Toast.makeText(context, "Email is already Exists.\nTry to Signin", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(context, "something went Wrong\nPlease Try To register again later", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private static String generateHash(String data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();

        byte[] hash = digest.digest(data.getBytes());
        return bytesToStringHex(hash);
    }

    public static String bytesToStringHex(byte[] bytes){
        char[] hexChars = new char[bytes.length * 2];
        for(int j = 0; j < bytes.length; j++){
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
