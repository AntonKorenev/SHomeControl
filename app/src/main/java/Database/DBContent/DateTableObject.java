package Database.DBContent;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;

import Database.Dao.IndoorDao;
import Database.Helpers.HelperFactory;
import Logs.LogDate;

/**
 * @author Anton Korenev
 * @version 1.0
 */
@DatabaseTable(tableName = "date")
public class DateTableObject {
    //Column names
    public static final String ID_COLUMN_NAME = "id";
    public static final String DAY_COLUMN_NAME = "day";
    public static final String MONTH_COLUMN_NAME = "month";
    public static final String YEAR_COLUMN_NAME = "column";
    public static final String TIME_COLUMN_NAME = "year";

    /** Id in database */
    @DatabaseField(generatedId = true,columnName = ID_COLUMN_NAME)
    private int mId;

    /** day number, when was created */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = DAY_COLUMN_NAME)
    int mDay;

    /** month number, when was created */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = MONTH_COLUMN_NAME)
    int mMonth;

    /** year number, when was created */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = YEAR_COLUMN_NAME)
    int mYear;

    /** time, when was created */
    @DatabaseField(canBeNull = false, dataType = DataType.FLOAT, columnName = TIME_COLUMN_NAME)
    float mTime;

    /** foreign key to Indoor objects(rooms) */
    @ForeignCollectionField(columnName = "indoors")
    private ForeignCollection<IndoorTableObject> mIndoorTableObjects;

    /** foreign key to Outdoor object */
    @DatabaseField(foreign = true)
    private OutdoorTableObject mOutdoorTableObject;

    public DateTableObject() {
        try {
            HelperFactory.get().getDateDAO().getEmptyForeignCollection("indoors");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DateTableObject(LogDate ld) {

    }

    public DateTableObject(int day, int month, int year, float time) {
        mDay = day;
        mMonth = month;
        mYear = year;
        mTime = time;
        try {
            HelperFactory.get().getDateDAO().getEmptyForeignCollection("indoors");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addIndoors(IndoorTableObject... indoors) throws SQLException {
        for(IndoorTableObject indoor: indoors){
            indoor.setDate(this);
            HelperFactory.get().getRoomDAO().create(indoor);
            //mIndoorTableObjects.add(indoor);
        }
    }

    public void removeIndoors(IndoorTableObject... indoors) throws SQLException {
        IndoorDao iDao = HelperFactory.get().getRoomDAO();
        for(IndoorTableObject indoor: indoors){
            mIndoorTableObjects.remove(indoor);
            iDao.delete(indoor);
        }
    }

    /////////Getters
    public int getDay() {
        return mDay;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getYear() {
        return mYear;
    }

    public float getTime() {
        return mTime;
    }

    public ForeignCollection<IndoorTableObject> getIndoorTableObjects() {
        return mIndoorTableObjects;
    }

    public OutdoorTableObject getOutdoorTableObject() {
        return mOutdoorTableObject;
    }


    ///////////Setters
    public void setDay(int day) {
        mDay = day;
    }

    public void setMonth(int month) {
        mMonth = month;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public void setTime(float time) {
        mTime = time;
    }

    public void setOutdoorTableObject(OutdoorTableObject outdoor) throws SQLException {
        mOutdoorTableObject = outdoor;
        HelperFactory.get().getOutdoorDAO().create(mOutdoorTableObject);
    }


    /////////Override
    @Override
    public String toString() {
        String indoors = "";
        for (IndoorTableObject ind: mIndoorTableObjects){
            indoors+=ind.toString();
        }
        return " "+mYear+"."+mMonth+"."+mDay+": "+mTime+"\n"+mOutdoorTableObject.toString()+indoors;
    }
}
