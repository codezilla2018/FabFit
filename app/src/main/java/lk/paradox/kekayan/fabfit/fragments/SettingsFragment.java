package lk.paradox.kekayan.fabfit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import lk.paradox.kekayan.fabfit.R;


public class SettingsFragment extends Fragment {
    public final static int DEFAULT_WEIGHT = 52;
    public final static int DEFAULT_HEIGHT = 173;
    private static double METRIC_AVG_FACTOR = 1.167185415740329;
    public final static int DEFAULT_GOAL = 10000;
    public final static double DEFAULT_STEP_SIZE =METRIC_AVG_FACTOR*DEFAULT_HEIGHT;
    public final static String DEFAULT_STEP_UNIT = "cm";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }


}
