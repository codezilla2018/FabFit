package lk.paradox.kekayan.fabfit.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import lk.paradox.kekayan.fabfit.R;

import static android.content.Context.MODE_PRIVATE;


public class SettingsFragment extends Fragment {
    public final static int DEFAULT_WEIGHT = 52;
    public final static int DEFAULT_HEIGHT = 173;
    public final static String DEFAULT_STEP_UNIT = "cm";
    public final static double DEFAULT_STEP_SIZE = METRIC_AVG_FACTOR * DEFAULT_HEIGHT;
    private static double METRIC_AVG_FACTOR = 1.167185415740329;
    public static int DEFAULT_GOAL = 10000;
    //
    private Button save;
    private EditText dgoal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        save = v.findViewById(R.id.savegoalbtn);
        dgoal = v.findViewById(R.id.goaltxt);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int goals = Integer.valueOf(String.valueOf(dgoal.getText()));
                SharedPreferences.Editor editor = Objects.requireNonNull(getActivity()).
                        getSharedPreferences("FabFit", MODE_PRIVATE).edit();
                editor.putInt("dailygoal", goals);
                editor.apply();
            }
        });
        return v;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences prefs = Objects.requireNonNull(getActivity()).getSharedPreferences
                ("FabFit", MODE_PRIVATE);
        int goals = prefs.getInt("dailygoal", DEFAULT_GOAL);
        dgoal.setText(String.valueOf(goals));
    }
}
