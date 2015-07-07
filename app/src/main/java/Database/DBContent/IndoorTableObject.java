package Database.DBContent;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class for storing climate parameters and actions
 *
 * @author Anton Korenev
 * @version 1.0
 */
@DatabaseTable(tableName = "indoor")
public class IndoorTableObject {
    //Column names
    public static final String TEMPERATURE_COLUMN_NAME = "temperature";
    public static final String HUMIDITY_COLUMN_NAME = "humidity";
    public static final String LUMINOSITY_COLUMN_NAME = "luminosity";
    public static final String FERTILIZERS_COLUMN_NAME = "fertilizers";
    public static final String VENTILATION_COLUMN_NAME = "ventilation";
    public static final String WATERING_COLUMN_NAME = "watering";

    /** Id in database */
    @DatabaseField(generatedId = true)
    private int mId;

    /** Momentary temperature in room(celsius) */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = TEMPERATURE_COLUMN_NAME)
    protected int mTemperature;

    /** Momentary humidity in room(percent) */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = HUMIDITY_COLUMN_NAME)
    protected int mHumidity;

    /** Momentary luminosity in room(percent) */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = LUMINOSITY_COLUMN_NAME)
    protected int mLuminosity;

    /** Momentary amount of fertilizers(from 0 to $max, in ml) */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = FERTILIZERS_COLUMN_NAME)
    protected int mFertilizers;

    /** Momentary time of ventilation(in minutes) */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = VENTILATION_COLUMN_NAME)
    protected int mVentilation;

    /** Momentary amount of watering(from 0 to $max,in ml) */
    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER, columnName = WATERING_COLUMN_NAME)
    protected int mWatering;

    /** Link to date object */
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private DateTableObject mDate;

    //Constructors
    /**
     * Default constructor, all parameters were set to zero
     */
    public IndoorTableObject(){
        mTemperature = 0;
        mHumidity = 0;
        mLuminosity = 0;
        mFertilizers = 0;
        mVentilation = 0;
        mWatering = 0;
    }

    /**
     * Constructor, all nonmicroclimate parameters were set to zero
     * @param temperature momentary temperature value
     * @param humidity momentary humidity value
     * @param luminosity momentary luminosity value
     */
    public IndoorTableObject(int temperature, int humidity, int luminosity) {
        mTemperature = temperature;
        mHumidity = humidity;
        mLuminosity = luminosity;
        mFertilizers = 0;
        mVentilation = 0;
        mWatering = 0;
    }

    /**
     * Constructor, which sets all parameters to obtained values
     * @param temperature momentary temperature value
     * @param humidity momentary humidity value
     * @param luminosity momentary luminosity value
     * @param fertilizers momentary fertilizers amount
     * @param ventilation momentary ventilation time
     * @param watering momentary watering amount
     */
    public IndoorTableObject(int temperature, int humidity, int luminosity, int fertilizers, int ventilation,
                             int watering) {
        mTemperature = temperature;
        mHumidity = humidity;
        mLuminosity = luminosity;
        mFertilizers = fertilizers;
        mVentilation = ventilation;
        mWatering = watering;
    }


    /////////////////Getters
    /**
     * Getter for temperature
     * @return room temperatur
     */
    public int getTemperature() {
        return mTemperature;
    }

    /**
     * Getter for humidity
     * @return room humidity
     */
    public int getHumidity() {
        return mHumidity;
    }

    /**
     * Getter for  luminosity
     * @return room luminosity
     */
    public int getLuminosity() {
        return mLuminosity;
    }

    /**
     * Getter for fertilizers
     * @return amount of fertilizers at the certain moment (in ml)
     */
    public int getFertilizers() {
        return mFertilizers;
    }

    /**
     * Getter for ventilation
     * @return time of ventilation at the certain moment (in minutes)
     */
    public int getVentilation() {
        return mVentilation;
    }

    /**
     * Getter for watering
     * @return amount of watering at the certain moment (in ml)
     */
    public int getWatering() {
        return mWatering;
    }

    /**
     * TODO:desc
     * @return
     */
    public DateTableObject getDate() {
        return mDate;
    }

    //////////////Setters
    /**
     * Setter for temperature
     * @param temperature new temperature value
     */
    public void setTemperature(int temperature) {
        mTemperature = temperature;
    }

    /**
     * Setter for humidity
     * @param humidity new humidity value
     */
    public void setHumidity(int humidity) {
        mHumidity = humidity;
    }

    /**
     * Setter for luminosity
     * @param luminosity new luminosity value
     */
    public void setLuminosity(int luminosity) {
        mLuminosity = luminosity;
    }

    /**
     * Setter for fertilizers
     * @param fertilizers new fertilizers value
     */
    public void setFertilizers(int fertilizers) {
        mFertilizers = fertilizers;
    }

    /**
     * Setter for ventilation
     * @param ventilation new ventilation value
     */
    public void setVentilation(int ventilation) {
        mVentilation = ventilation;
    }

    /**
     * Setter for watering
     * @param watering new watering value
     */
    public void setWatering(int watering) {
        mWatering = watering;
    }

    /**
     * TODO:desc
     * @param date
     */
    public void setDate(DateTableObject date) {
        mDate = date;
    }

    //Override methods
    @Override
    public String toString() {
        return  "Indoor parameters:\n"+
                "Temperature=" + mTemperature +
                ", Humidity=" + mHumidity +
                ", Luminosity=" + mLuminosity +
                ", Fertilizers=" + mFertilizers +
                ", Ventilation=" + mVentilation +
                ", Watering=" + mWatering + "\n";
    }


}
