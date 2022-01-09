package utilities;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import manager.CachingManager;

public class Utility {

    public static String getLocalString(Context context, int stringId) {
        String key = context.getResources().getResourceEntryName(stringId);
        String value = null;
        if(CachingManager.getLocaleString()!=null)
        {
            value = CachingManager.getLocaleString().get(key) ;
        }
        if(value == null){
            value = context.getResources().getString(stringId);
        }
        return value ;
    }


}
