package com.shometeam.ao.shomecontrol;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.shometeam.ao.shomecontrol.MainScreen.MainLogRecyclerAdapter;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import Database.DBContent.DateTableObject;
import Database.DBContent.IndoorTableObject;
import Database.DBContent.OutdoorTableObject;
import Database.Helpers.HelperFactory;
import Logs.LogJournal;
import Logs.LogJournalFactory;
import Logs.LogTag;
import Logs.LogThread;
import Logs.WeakLogHandler;

public class MainActivity extends AppCompatActivity {
    /** Tag for logcat */
    private static final String sTag = "AAAAA12";

    WeakLogHandler mMainHandler;
    LogThread mLogThread;
    RecyclerView mLogRecyclerView;

    @Override
    protected void onDestroy() {
        quitDataBase();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            LogJournalFactory.set((LogJournal) savedInstanceState.getSerializable("KEY_LOG_UTIL") );

            LogTag restoredTag = (LogTag) savedInstanceState.getSerializable("KEY_LOG_TAG");
            mMainHandler = new WeakLogHandler(this, restoredTag);

            List<Message> restoredLogQueue =
                    (List<Message>) savedInstanceState.getSerializable("KEY_LOG_QUEUE");
            mLogThread = new LogThread(mMainHandler);
            mLogThread.restoreLogQueue(restoredLogQueue);
        }
        else{
            mMainHandler = new WeakLogHandler(this, LogTag.ALL);
            mLogThread = new LogThread(mMainHandler);
        }


        startGUI();
        mLogThread.start();
        mLogThread.getLooper();

        startDataBase();

        Thread t = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    HelperFactory.get().getDateDAO().clear();
                    int iter = 0;
                    for(Object obj: HelperFactory.get().getDateDAO().getAllDataClasses()){
                        Log.d(sTag," "+iter);
                        iter++;
                        DateTableObject dt = (DateTableObject) obj;
                        Log.d(sTag, dt.toString());

                        OutdoorTableObject receivedOutdoor = dt.getOutdoorTableObject();
                        //HelperFactory.get().getOutdoorDAO().refresh(receivedOutdoor);
                        Log.d(sTag, receivedOutdoor.toString());
                        Log.d(sTag, "size=" + dt.getIndoorTableObjects().size());
                        for(IndoorTableObject ind: dt.getIndoorTableObjects()){
                            Log.d(sTag, ind.toString());
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();




        /*Thread t = new Thread(){
            @Override
            public void run() {
                while (!isInterrupted()){
                    try {
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    LogMessage msg1 = new LogMessage(LogTag.DROPBOX,"AAAA");
                    mLogThread.addLog(msg1);
                    mLogJournal.addMessage(msg1);

                    LogMessage msg2 = new LogMessage(LogTag.PROGNOSIS,"AAAA");
                    mLogThread.addLog(msg2);
                    mLogJournal.addMessage(msg2);

                    LogMessage msg3 = new LogMessage(LogTag.APP,"AAAA");
                    mLogThread.addLog(msg3);
                    mLogJournal.addMessage(msg3);

                    LogMessage msg4 = new LogMessage(LogTag.DATABASE,"AAAA");
                    mLogThread.addLog(msg4);
                    mLogJournal.addMessage(msg4);

                    LogMessage msg5 = new LogMessage(LogTag.OWM,"AAAA");
                    mLogThread.addLog(msg5);
                    mLogJournal.addMessage(msg5);
                }
            }
        };
        t.start();

        Thread t2 = new Thread(){
            @Override
            public void run() {
                while (!isInterrupted()){
                    try {
                        sleep(9000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LogMessage msg = new LogMessage(LogTag.ARDUINO,"BBBB");
                    mLogThread.addLog(msg);
                    mLogJournal.addMessage(msg);
                }
            }
        };
        t2.start();*/
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("KEY_LOG_UTIL", LogJournalFactory.get());
        outState.putSerializable("KEY_LOG_TAG", mMainHandler.getCurrentTag());
        outState.putSerializable("KEY_LOG_QUEUE",(Serializable)mLogThread.getLogQueue());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    ////starters

    private void startGUI(){
        // Инициализируем Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_text_dark));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.color_text_dark));
        setSupportActionBar(toolbar);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, LogTag.getArrayOfTagsIndent());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner_logs);
        spinner.setAdapter(adapter);


        mLogRecyclerView = (RecyclerView)findViewById(R.id.recycler_view_main);
        final MainLogRecyclerAdapter logRecyclerAdapter = new MainLogRecyclerAdapter(
                LogJournalFactory
                        .get()
                        .getMessagesByTag(mMainHandler.getCurrentTag())
        );

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mLogRecyclerView.setAdapter(logRecyclerAdapter);
        mLogRecyclerView.setLayoutManager(layoutManager);
        mLogRecyclerView.setItemAnimator(itemAnimator);

        // выделяем элемент
        spinner.setSelection(mMainHandler.getCurrentTag().mValue);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                mMainHandler.setCurrentTag(LogTag.values()[position]);
                logRecyclerAdapter.clear();
                logRecyclerAdapter.addLogList(LogJournalFactory
                        .get()
                        .getMessagesByTag(LogTag.values()[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void startDataBase(){
        HelperFactory.set(getApplicationContext());
    }


    ///////enders
    private void quitDataBase(){
        HelperFactory.release();
    }
}










