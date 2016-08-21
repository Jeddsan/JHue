package jeddsan.net.huetest;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.widget.Toast;

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
public class JHue {

    private URL url = null;
    private String result="";
    private String ip="";
    private String username="";
    private float[] hsv = new float[3];

    public JHue(String username, String ip){
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getAllSchedules(){
        try {
            url = new URL("http://"+ip+"/api/"+username+"/schedules");
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getAllGroups(){
        try {
            url = new URL("http://"+ip+"/api/"+username+"/groups");
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getAllScenes(){
        try {
            url = new URL("http://"+ip+"/api/"+username+"/scenes");
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getAllSensors(){
        try {
            url = new URL("http://"+ip+"/api/"+username+"/sensors");
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getAllRules(){
        try {
            url = new URL("http://"+ip+"/api/"+username+"/rules");
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getAllTimezones(){
        try {
            url = new URL("http://"+ip+"/api/"+username+"/info/timezones");
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getAllResourceLinks(){
        try {
            url = new URL("http://"+ip+"/api/"+username+"/resourcelinks");
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
        result = JRequests.requestURL(url,"PUT","{\"on\": "+state+"}");
    }

    public int getLightIdFromName(String name){
        int i=0;
        JSONObject obj = null;
        result = getAllLights();
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        while(true) {
            i++;
            try {
                JSONObject id = obj.getJSONObject(i+"");
                String hue_light_name =  id.getString("name");
                if(hue_light_name.equals(name)){
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                i=0;
                break;
            } catch (NullPointerException e) {
                e.printStackTrace();
                i=0;
                break;
            }
        }
        return i;
    }
    public int getGroupIdFromName(String name){
        int i=0;
        JSONObject obj = null;
        result = getAllGroups();
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        while(true) {
            i++;
            try {
                JSONObject id = obj.getJSONObject(i+"");
                String hue_group_name =  id.getString("name");
                if(hue_group_name.equals(name)){
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                i=0;
                break;
            } catch (NullPointerException e) {
                e.printStackTrace();
                i=0;
                break;
            }
        }
        return i;
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
        result = JRequests.requestURL(url,"PUT","{\"on\": true,\"bri\": "+dim_number+"}");
    }

    public void setLightColor(int id,int hue_color){
        if(hue_color>=65535||hue_color<=0){
            try {
                throw new Exception("Number is not a valid hue value for Hue.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            url = new URL("http://"+ip+"/api/"+username+"/lights/"+id+"/state");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        result = JRequests.requestURL(url,"PUT","{\"on\": true,\"hue\": "+hue_color+"}");
    }
    public void setLightColor(int id,String rgb){
        //Parsing RGB to HSV
        hsv = getHSVfromRGB(rgb);

        String str_color_hue = String.valueOf(Math.ceil(hsv[0]/360*65536));
        str_color_hue = str_color_hue.substring(0,str_color_hue.length()-2);
        int int_color_hue = Integer.parseInt(str_color_hue);

        /*String str_color_sat = String.valueOf(Math.ceil(hsv[1]*256));
        str_color_sat = str_color_sat.substring(0,str_color_sat.length()-2);
        int int_color_sat = Integer.parseInt(str_color_sat);

        String str_color_bri = String.valueOf(Math.ceil(hsv[2]*256));
        str_color_bri = str_color_bri.substring(0,str_color_bri.length()-2);
        int int_color_bri = Integer.parseInt(str_color_bri);*/

        try {
            url = new URL("http://"+ip+"/api/"+username+"/lights/"+id+"/state");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        result = JRequests.requestURL(url,"PUT","{\"on\": true,\"hue\": "+ int_color_hue +",\"bri\": "+ 256 +",\"sat\": "+ 111 +"}");
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

            result = JRequests.requestURL(url,"POST","{\"devicetype\": \"" + devicetype + "\"}");
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

    public float[] getHSVfromRGB(String rgb){
        rgb = rgb.replace("#","").trim();
        int r = Integer.parseInt(rgb.substring(0,1),16);
        int g = Integer.parseInt(rgb.substring(2,3),16);
        int b = Integer.parseInt(rgb.substring(4,5),16);

        Color.RGBToHSV(r,g,b,hsv);

        return hsv;
    }

    public static String getBridgeAddress(){
        String currentIP = JRequests.getLocalIPAddress();
        String [] addresses = JRequests.getLocalNetworkDevices(currentIP, 10, 80);
        int i = 0;
        String result;
        JSONObject obj = null;
        URL url = null;
        while(true){
            if(addresses[i]==null){
                break;
            }else{
                try {
                    url = new URL("http://" + addresses[i] + "/api/");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                result = JRequests.requestURL(url,"POST","{}");
                result = result.substring(1, result.length()-1);
                result = result.toString();

                try {
                    obj = new JSONObject(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    String error=obj.getString("error");
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
            i++;
        }
        return addresses[i];
    }

    public static Boolean checkBridgeAddress(String ip){
        int i = 0;
        String result;
        JSONObject obj = null;
        URL url = null;
        if(ip==null){
            return false;
        }else{
            try {
                url = new URL("http://" + ip + "/api/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return false;
            }

            result = JRequests.requestURL(url,"POST","{}");
            result = result.substring(1, result.length()-1);
            result = result.toString();

            try {
                obj = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
            try {
                String error=obj.getString("error");
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return false;
            }

        }
    }
}
