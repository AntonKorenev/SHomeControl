package Database.Helpers;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * @author Anton Korenev
 * @version 1.0
 */
public class HelperFactory {
    private static DatabaseHelper sDatabaseHelper;

    public static DatabaseHelper get() {
        return sDatabaseHelper;
    }

    public static void set(Context context){
        sDatabaseHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
    }

    public static void release(){
        OpenHelperManager.releaseHelper();
        sDatabaseHelper = null;
    }
}
