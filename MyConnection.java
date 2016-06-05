package tcs.com.drawapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by 1256104 on 6/3/2016.
 */
public class MyConnection {

    public String getData(String urlString){
    URL url = null;
    HttpURLConnection connection = null;
    StringBuilder sb = null;

        try{
            url = new URL(urlString);
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setReadTimeout(30*1000);
            int resposeCode = connection.getResponseCode();
            if(resposeCode == HttpURLConnection.HTTP_OK){
                String line;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                sb = new StringBuilder();
                while((line = bufferedReader.readLine()) !=null){
                    sb.append(line);
                }
            }else {
                String line;
                BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(connection.getErrorStream())));
                sb= new StringBuilder();
                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }
            }
        }catch (ProtocolException e){
            Log.d(MyConnection.class.toString(),e.toString(),e);
            return e.toString();
        }catch (MalformedURLException e){
            Log.d(MyConnection.class.toString(),e.toString(),e);
            return e.toString();
        }catch (IOException e){
            Log.d(MyConnection.class.toString(),e.toString(),e);
            return e.toString();
        }

    String result = (sb !=null) ? sb.toString(): " no result " ;
    return result;
    }

    public Bitmap getImageBitmap(String urlString) {

        URL url = null;
        Bitmap bmp = null;
        HttpURLConnection connection = null;
        StringBuilder sb = null;
        try {
            url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setReadTimeout(30 * 1000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                bmp = BitmapFactory.decodeStream(connection.getInputStream());
            } else {
                String line;

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        connection.getErrorStream()));
                sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                Log.d(MyConnection.class.toString(), sb.toString());
            }
        } catch (ProtocolException e) {
            Log.e(MyConnection.class.toString(), e.toString(), e);
        } catch (MalformedURLException e) {
            Log.e(MyConnection.class.toString(), e.toString(), e);
        } catch (IOException e) {
            Log.e(MyConnection.class.toString(), e.toString(), e);
        }
        return bmp;
    }
}
