package manager;

import android.content.Context;

import java.util.Map;

import model.Cache;

public class CachingManager {

    public static Context getApplicationContext()
    {
        return  Cache.getInstance().getContext();
    }




    public  static Map<String,String> getLocaleString(){
        Map<String,String> localeString = Cache.getInstance().getLocaleString();
        if(localeString.isEmpty()){
            localeString =   PersistenceManager.getLocalString();
        }

        return localeString;
    }

    public  static void settLocaleString(Map<String,String> localeString){
        PersistenceManager.setLocalString(localeString);
        Cache.getInstance().setLocaleString(PersistenceManager.getLocalString());
    }

    public static void setApplicationContext(Context context)
    {
        Cache.getInstance().setContext(context);

    }
}
