package Database.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import Database.DBContent.DateTableObject;
import Database.DBContent.IndoorTableObject;
import Database.DBContent.OutdoorTableObject;
import Database.Dao.DateDao;
import Database.Dao.IndoorDao;
import Database.Dao.OutdoorDao;

/**
 * @author Anton Korenev
 * @version 1.0
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper{
    /** Tag for logcat */
    private static final String sTag = DatabaseHelper.class.getSimpleName();

    // in /data/data/SHomeControl/DATABASE_NAME.db
    private static final String DATABASE_NAME="shomecontrol";

    private static final int DATABASE_VERSION = 4;

    private DateDao mDateDao = null;
    private IndoorDao mRoomDao = null;
    private OutdoorDao mOutdoorDao = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource){
        try
        {
            TableUtils.createTable(connectionSource, DateTableObject.class);
            TableUtils.createTable(connectionSource, IndoorTableObject.class);
            TableUtils.createTable(connectionSource, OutdoorTableObject.class);
        }
        catch (SQLException e){
            Log.e(sTag, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer){
        try{
            //Так делают ленивые, гораздо предпочтительнее не удаляя БД аккуратно вносить изменения
            TableUtils.dropTable(connectionSource, DateTableObject.class, true);
            TableUtils.dropTable(connectionSource, IndoorTableObject.class, true);
            TableUtils.dropTable(connectionSource, OutdoorTableObject.class, true);
            onCreate(db, connectionSource);
        }
        catch (SQLException e){
            Log.e(sTag,"error upgrading db "+DATABASE_NAME+"from ver "+oldVer);
            throw new RuntimeException(e);
        }
    }

    //синглтон для DateDao
    public DateDao getDateDAO() throws SQLException {
        if(mDateDao == null){
            mDateDao = new DateDao(getConnectionSource(), DateTableObject.class);
        }
        return mDateDao;
    }

    //синглтон для RoomDao
    public IndoorDao getRoomDAO() throws SQLException {
        if(mRoomDao == null){
            mRoomDao = new IndoorDao(getConnectionSource(), IndoorTableObject.class);
        }
        return mRoomDao;
    }

    //синглтон для DateDao
    public OutdoorDao getOutdoorDAO() throws SQLException {
        if(mOutdoorDao == null){
            mOutdoorDao = new OutdoorDao(getConnectionSource(), OutdoorTableObject.class);
        }
        return mOutdoorDao;
    }

    //выполняется при закрытии приложения
    @Override
    public void close(){
        super.close();
        mOutdoorDao = null;
        mRoomDao = null;
        mDateDao = null;
    }
}
