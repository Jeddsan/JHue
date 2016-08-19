package jeddsan.net.huetest;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Julian on 18.08.2016.
 */
public class JRequests {


    public static String requestURL(URL url, String method, String body){
        String result = "";
        HttpURLConnection httpCon = null;
        try {
            httpCon = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert httpCon != null;
        httpCon.setDoOutput(true);
        try {
            httpCon.setRequestMethod(method);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(
                    httpCon.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert out != null;
            out.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpCon.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream is = null;
        result="";
        try {
            is = new BufferedInputStream(httpCon.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String inputLine = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                result+=inputLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static boolean saveData(String filename, String content, Context ctx){
        try {
            FileOutputStream fou = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fou);
            try {
                osw.write(content);
                osw.flush();
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String loadData(String filename,Context ctx){
        try {
            FileInputStream fis = ctx.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            char[] data = new char[255];
            String final_data = "";
            int size;
            try {
                while ((size = isr.read(data)) > 0) {
                    String read_data = String.copyValueOf(data, 0, size);
                    final_data += read_data;
                    data = new char[255];
                }
                return String.valueOf(final_data);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
