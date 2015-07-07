package Logs;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import com.shometeam.ao.shomecontrol.MainScreen.MainLogRecyclerAdapter;
import com.shometeam.ao.shomecontrol.R;
import java.lang.ref.WeakReference;

/**
 * Class for solving the problem with memory leak in handler
 *
 * @author Anton Korenev
 * @version 1.0
 */
public class WeakLogHandler extends Handler {
    /** Tag for logcat */
    private static final String sTag = WeakLogHandler.class.getSimpleName();

    /** Weak reference to UI activity */
    private final WeakReference<Activity> mActivity;

    /** Current logger tag of activity */
    private LogTag mCurrentLogTag;

    /**
     * Default class constructor
     * @param activity link to ui activity
     * @param logTag value of current UI logger tag
     */
    public WeakLogHandler(Activity activity, LogTag logTag) {
        mActivity = new WeakReference<Activity>(activity);
        mCurrentLogTag = logTag;
    }

    //Getters
    /**
     * Getter for logger tag of activity
     * @return Getter for logger tag of activity
     */
    public LogTag getCurrentTag() {
        return mCurrentLogTag;
    }

    //Setters
    /**
     * Setter for logger tag of activity
     * @param currentLogTag new logger tag
     */
    public void setCurrentTag(LogTag currentLogTag) {
        mCurrentLogTag = currentLogTag;
    }

    //Override methods
    @Override
    public void handleMessage(Message msg) {
        Activity activity = mActivity.get();
        if(activity!=null){
            //getting main recycler adapter to add log message
            RecyclerView logRecyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view_main);
            MainLogRecyclerAdapter adapter = (MainLogRecyclerAdapter)logRecyclerView.getAdapter();

            //Adding in cases of equal tags or tag All
            if((msg.what == mCurrentLogTag.mValue) || (mCurrentLogTag == LogTag.ALL)){
                adapter.addLog((LogMessage)msg.obj);
            }
        }
    }
}
