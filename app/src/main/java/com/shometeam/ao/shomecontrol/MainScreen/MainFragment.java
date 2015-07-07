package com.shometeam.ao.shomecontrol.MainScreen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shometeam.ao.shomecontrol.R;

/**
 * Created by ao on 7/2/15.
 */
public class MainFragment extends Fragment {
    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
