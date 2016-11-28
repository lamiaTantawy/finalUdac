package app.com.example.android.project_popularmoves;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lamia on 17/10/2016.
 */

public class InternetServices {
    protected HttpURLConnection openConnection(Uri uri,String RequestMethod) //method GET or POST
    {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(uri.toString());
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod(RequestMethod);
            urlConnection.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return urlConnection;
    }
    protected String ReadInputStream(HttpURLConnection urlConnection)
    {
        String outputString = null;
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            if(inputStream == null)
                return null;
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine())!=null)
            {
                stringBuffer.append(line+'\n');
            }
            if(stringBuffer.length()==0)
                return null;
            outputString = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlConnection.disconnect();
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputString;
    }
}
