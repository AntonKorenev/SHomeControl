package Logs;

import java.io.Serializable;

/**
 * Logger message class
 * Is used for creating and sharing log message data
 *
 * @author Anton Korenev
 * @version 1.0
 */
public class LogMessage implements Comparable<LogMessage>, Serializable{
    /** Tag for logcat */
    private static final String sTag = LogMessage.class.getSimpleName();

    /** The message category */
    public LogTag mLogTag;

    /** The message text */
    public String mText;

    /** the date, message was created */
    private LogDate mDate;

    /**
     * Default constructor
     *
     * @param logTag message cathegory
     * @param msg message content
     */
    public LogMessage(LogTag logTag, String msg){
        mLogTag = logTag;
        mDate = new LogDate();

        //formating received log text to default pattern
        mText = logTag.toString() +":\n"
                +mDate.toString()+"\n\n"+
                "\""+msg+"\"";;

    }

    /**
     * Getter for creation date
     * @return the date, message was created
     */
    public LogDate getDate(){
        return mDate;
    }

    //compare parameter for comparator
    @Override
    public int compareTo(LogMessage another) {
        float date1 = mDate.hash();
        float date2 = another.getDate().hash();
        if(date1 > date2){
            return 1;
        } else if(date1 == date2){
            return 0;
        } else {
            return -1;
        }
    }
}
