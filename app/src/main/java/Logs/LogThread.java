package Logs;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Logger thread class
 * Is used for handling messages from different threads and than changing UI thread after processing
 *
 * @author Anton Korenev
 * @version 1.0
 */
public class LogThread extends HandlerThread{
    /** Tag for logcat */
    private static final String sTag = LogThread.class.getSimpleName();

    /** Synchronized queue for storing handler messages */
    private List<Message> mLogQueue;

    /** Link to Activity handler */
    private Handler mHandler;

    /**
     * Default constructor
     * @param handler link to activity handler
     */
    public LogThread(Handler handler) {
        super(sTag);
        mHandler = handler;
        mLogQueue = Collections.synchronizedList(new LinkedList<Message>());
    }

    @Override
    protected void onLooperPrepared() {
        while(!isInterrupted()){
            if(mLogQueue.isEmpty()){
                synchronized (this){
                    try {
                        Log.d(sTag,"waiting");
                        wait();
                        Log.d(sTag, "stop waiting");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else{
                handleLog();
            }
        }
    }

    /**
     * The method for restoring log queue. Is used for restoring it from savedInstanceState
     * @param logQueue
     */
    public void restoreLogQueue(List<Message> logQueue){
        mLogQueue = logQueue;
    }

    /**
     * Getter for logs queue
     * @return
     */
    public List<Message> getLogQueue(){
        return mLogQueue;
    }

    /**
     * Synchronized method for adding new log message into the queue
     * @param msg
     */
    public void addLog(LogMessage msg){
        synchronized (this){
            Message message = mHandler.obtainMessage(msg.mLogTag.mValue, msg);
            mLogQueue.add(message);
            notify();
        }
    }

    /**
     * The method for handling message from the queue. Now it just redirects it to Activity handler
     */
    private void handleLog(){
            mHandler.sendMessage(mLogQueue.get(0));
            mLogQueue.remove(0);
    }
}