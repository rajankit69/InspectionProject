package model;

import android.content.Context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache
{
    private static  Cache cache;
    private boolean isPayInProgress;
    private Context context;
    private ConcurrentHashMap<String,String> localeString = new ConcurrentHashMap<String,String>();

    public Map<String, String> getLocaleString() {
        return localeString;
    }

    public void setLocaleString(Map<String, String> localeString) {
        this.localeString = new ConcurrentHashMap<String,String>(localeString);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public boolean isPayInProgress() {
        return isPayInProgress;
    }

    public void setPayInProgress(boolean payInProgress) {
        this.isPayInProgress = payInProgress;
    }

    private Cache()
    {}

    public static  Cache  getInstance()
    {
        if(cache == null)
        {
            cache = new Cache();
        }
        return cache;
    }
}
