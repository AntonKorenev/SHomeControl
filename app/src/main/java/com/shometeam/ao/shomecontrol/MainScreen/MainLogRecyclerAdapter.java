package com.shometeam.ao.shomecontrol.MainScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shometeam.ao.shomecontrol.R;

import java.util.List;

import Logs.LogMessage;

/**
 * Created by ao on 6/30/15.
 */
public class MainLogRecyclerAdapter extends RecyclerView.Adapter<MainLogRecyclerAdapter.ViewHolder> {

    private List<LogMessage> mLogs;

    public MainLogRecyclerAdapter(List<LogMessage> logs) {
        mLogs = logs;
    }

    @Override
    public MainLogRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_log_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mLogText.setText(mLogs.get(i).mText);
        switch (mLogs.get(i).mLogTag){
            case ALL:
                viewHolder.mLogButton.setImageResource(R.mipmap.ic_launcher);
                break;
            case APP:
                viewHolder.mLogButton.setImageResource(R.mipmap.mobile_icon);
                break;
            case ARDUINO:
                viewHolder.mLogButton.setImageResource(R.mipmap.arduino_icon);
                break;
            case DATABASE:
                viewHolder.mLogButton.setImageResource(R.mipmap.database_icon);
                break;
            case DROPBOX:
                viewHolder.mLogButton.setImageResource(R.mipmap.dropbox_icon);
                break;
            case OWM:
                viewHolder.mLogButton.setImageResource(R.mipmap.weather_icon);
                break;
            case PROGNOSIS:
                viewHolder.mLogButton.setImageResource(R.mipmap.ai_icon);
                break;
            default:
                viewHolder.mLogButton.setImageResource(R.mipmap.ic_launcher);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return mLogs.size();
    }

    public void addLog(LogMessage log){
        mLogs.add(0,log);
        notifyDataSetChanged();
    }

    public void addLogList(List<LogMessage> logs){
        mLogs.addAll(logs);
        notifyDataSetChanged();
    }

    public void clear(){
        mLogs.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mLogText;
        private ImageButton mLogButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mLogText = (TextView) itemView.findViewById(R.id.text_view_log);
            mLogButton = (ImageButton) itemView.findViewById(R.id.image_button_log);
        }
    }
}
