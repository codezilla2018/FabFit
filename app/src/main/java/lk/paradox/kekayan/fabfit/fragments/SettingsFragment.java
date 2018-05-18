package lk.paradox.kekayan.fabfit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import lk.paradox.kekayan.fabfit.R;


public class SettingsFragment extends Fragment {
    final static int DEFAULT_WEIGHT = 52;
    final static int DEFAULT_GOAL = 10000;
    final static float DEFAULT_STEP_SIZE = Locale.getDefault() == Locale.US ? 2.5f : 75f;
    final static String DEFAULT_STEP_UNIT = Locale.getDefault() == Locale.US ? "ft" : "cm";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }


}
