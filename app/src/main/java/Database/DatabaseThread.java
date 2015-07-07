package Database;

import java.sql.SQLException;

import Database.DBContent.DateTableObject;
import Database.DBContent.IndoorTableObject;
import Database.DBContent.OutdoorTableObject;
import Database.Helpers.HelperFactory;
import Logs.LogDate;
import Logs.LogJournalFactory;
import Logs.LogMessage;
import Logs.LogTag;
import Logs.LogThread;

/**
 * @author Anton Korenev
 * @version 1.0
 */
public class DatabaseThread extends Thread {
    LogThread mLogThread;
    long mDelay;

    DatabaseThread(LogThread lt, long delay){
        mLogThread = lt;
        mDelay = delay;
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()){

            //TODO: взятие из ардуино
            IndoorTableObject indoor = new IndoorTableObject();
            IndoorTableObject indoor1 = new IndoorTableObject(2,2,2,3,4,6);
            addArduinoMessage();
            try {
                sleep(mDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addArduinoMessage(IndoorTableObject... indoors){
        //создается здесь из погодных сервисов TODO: доделать взятие из погодных сервисов
        OutdoorTableObject outdoor = new OutdoorTableObject();

        //создается здесь как и для логгера
        LogDate ld = new LogDate();
        DateTableObject date = new DateTableObject(ld);
        try {
            //добавляем в БД
            date.setOutdoorTableObject(outdoor);
            HelperFactory.get().getDateDAO().create(date);
            date.addIndoors(indoors);

            //Добавим в логер
            LogMessage msg2 = new LogMessage(LogTag.DATABASE,date.toString());
            mLogThread.addLog(msg2);
            LogJournalFactory.get().addMessage(msg2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
