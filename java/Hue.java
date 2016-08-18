package jeddsan.net.huetest;

import android.annotation.TargetApi;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Julian on 18.08.2016.
 */
public class Hue {

    private URL url = null;
    private String result="";
    private String ip="";
    private String username="";

    public Hue(String username, String ip){
        this.username = username;
        this.ip = ip;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getAllLights(){
        try {
            url = new URL("http://"+ip+"/api/"+username+"/lights");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            assert url != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    result+=line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setLightState(int id,boolean state){
        try {
            url = new URL("http://"+ip+"/api/"+username+"/lights/"+id+"/state");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        result = Requests.requestURL(url,"PUT","{\"on\": "+state+"}");
    }

    /**
     * Set the brightness value for a specific lamp
     * @param id lamp id
     * @param dim_number brightness value from 1 to 254
     */
    public void setLightDim(int id,int dim_number){
        if(dim_number>=255||dim_number<=0){
            try {
                throw new Exception("Number is not a valid brigntess value for Hue.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            url = new URL("http://"+ip+"/api/"+username+"/lights/"+id+"/state");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        result = Requests.requestURL(url,"PUT","{\"on\": true,\"bri\": "+dim_number+"}");
    }

    public static String setAuthMode(String devicetype, String ip){
        URL url = null;
        String result = "";
        String username = "";
        JSONObject obj = null;
        while(true) {
            try {
                url = new URL("http://" + ip + "/api/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            result = Requests.requestURL(url,"POST","{\"devicetype\": \"" + devicetype + "\"}");
            result = result.substring(1, result.length()-1);

            try {
                obj = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                String error=obj.getString("error");
            } catch (JSONException e) {
                try {
                    JSONObject success = obj.getJSONObject("success");
                    username = success.getString("username");
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
            //Authentication completed
            if(!username.equals("")){
                break;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return username;
    }
}
