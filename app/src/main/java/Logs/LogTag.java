package Logs;

import java.io.Serializable;

/**
 * Enum of possible tags(categories) for logger
 *
 * @author Anton Korenev
 * @version 1.0
 */
public enum LogTag implements Serializable{
    ALL(0),
    APP(1),
    ARDUINO(2),
    DATABASE(3),
    DROPBOX(4),
    OWM(5),
    PROGNOSIS(6);

    /** Integer equivalent of enum parameter */
    public final int mValue;

    /**
     * Default singlete constructor
     * @param value Integer equivalent of enum paramete
     */
    private LogTag(int value) {
        mValue = value;
    }

    /**
     * The method for converting enum variables to their string names
     * @return String array of enum values names
     */
    public static String[] getArrayOfTags(){
        String[] tags = new String[LogTag.values().length];
        for (int i = 0; i < LogTag.values().length; i++) {
            tags[i] = LogTag.values()[i].toString();
        }
        return tags;
    }

    /**
     * The method for converting enum variables to their string names.
     * to the end of each one was added an indent (is used for creating spinners)
     * @return String array of enum values names
     */
    public static String[] getArrayOfTagsIndent(){
        String[] tags = new String[LogTag.values().length];
        for (int i = 0; i < LogTag.values().length; i++) {
            tags[i] = LogTag.values()[i].toString()+"  ";
        }
        return tags;
    }
}
