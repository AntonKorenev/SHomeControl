package Logs;

/**
 * @author Anton Korenev
 * @version 1.0
 */
public class LogJournalFactory {
    private static LogJournal sLogJournal;

    public static LogJournal get() {
        if(sLogJournal == null) sLogJournal = new LogJournal();
        return sLogJournal;
    }

    public static void set(LogJournal lj){
        sLogJournal = lj;
    }

    public static void release(){
        sLogJournal.clear();
        sLogJournal = null;
    }
}
