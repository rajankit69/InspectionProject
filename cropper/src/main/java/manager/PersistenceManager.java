package manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PersistenceManager
{
    public  static final int NOT_REGISTERED = 1;
    public static final int REGISTERED = 2;
    public long PrefferedstoreId;
    public  int PrefferedstoreDeliveryAreaId;
    public  String PrefferedstoreAreaName;
    private ConcurrentHashMap<String,String> localeString = new ConcurrentHashMap<String,String>();
    public Map<String, String> getLocaleString() {
        return localeString;
    }

    public void setLocaleString(Map<String, String> localeString) {
        this.localeString = new ConcurrentHashMap<String,String>(localeString);
    }
    public static Map<String, String> getLocalString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();
        String json = null;
        Map<String, String> map = new HashMap<String,String>();
        try {
            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(CachingManager.getApplicationContext());
            json =  preferences.getString("localString", null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(json != null) {
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            map = gson.fromJson(json, type);
        }
        return map;

    }

    public static void setLocalString(Map<String, String> localeString) {
        Map<String,String> map = getLocalString();
        for(Map.Entry<String,String> entry :localeString.entrySet()){
            map.put(entry.getKey(),entry.getValue());
        }

        Gson gson = new GsonBuilder().disableHtmlEscaping()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();


        String jsonData = gson.toJson(map);
        try {
            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(CachingManager.getApplicationContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("localString",jsonData);
            editor.apply();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

