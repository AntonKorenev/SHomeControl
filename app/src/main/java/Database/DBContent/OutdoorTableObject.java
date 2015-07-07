package Database.DBContent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class for storing outdoor climate parameters
 *
 * @author Anton Korenev
 * @version 1.0
 */
@DatabaseTable(tableName = "outdoor")
public class OutdoorTableObject {
    //Column names
    public static final String TEMPERATURE_COLUMN_NAME = "temperature";
    public static final String HUMIDITY_COLUMN_NAME = "humidity";
    public static final String PRESSURE_COLUMN_NAME = "pressure";

    /** Id in database */
    @DatabaseField(generatedId = true)
    private int mId;

    /** Momentary temperature outdoor(celsius) */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = TEMPERATURE_COLUMN_NAME)
    protected int mTemperature;

    /** Momentary humidity outdoor(percent) */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = HUMIDITY_COLUMN_NAME)
    protected int mHumidity;

    /** Momentary pressure(Pa) */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = PRESSURE_COLUMN_NAME)
    int mPressure;

    /** Link to date object */
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private DateTableObject mDate;

    /**
     * Default constructor
     */
    public OutdoorTableObject() {
        mTemperature = 0;
        mHumidity = 0;
        mPressure = 0;
    }

    /**
     * Default constructor with parameters
     * @param temperature momentary temperature value
     * @param humidity momentary humidity value
     * @param pressure momentary pressure value
     */
    public OutdoorTableObject(int temperature, int humidity, int pressure) {
        mTemperature = temperature;
        mHumidity = humidity;
        mPressure = pressure;
    }

    //Getters

    public int getTemperature() {
        return mTemperature;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public int getPressure() {
        return mPressure;
    }

    public DateTableObject getDate() {
        return mDate;
    }

    //Setters
    public void setTemperature(int temperature) {
        mTemperature = temperature;
    }

    public void setHumidity(int humidity) {
        mHumidity = humidity;
    }

    public void setPressure(int pressure) {
        mPressure = pressure;
    }

    public void setDate(DateTableObject date) {
        mDate = date;
    }

    //Override methods
    @Override
    public String toString() {
        return  "Outdoor parameters:\n"+
                "Temperature=" + mTemperature +
                ", Humidity=" + mHumidity +
                ", Pressure=" + mPressure +'\n';
    }
}
