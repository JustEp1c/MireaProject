package com.mirea.sumachev.mireaproject;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SensorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorsFragment extends Fragment implements SensorEventListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView textViewGscope;
    private TextView GscopeX;
    private TextView GscopeY;
    private TextView GscopeZ;
    private TextView textViewMF;
    private TextView MFieldX;
    private TextView MFieldY;
    private TextView MFieldZ;
    private  TextView textViewLight;
    private TextView lightIndic;
    private SensorManager sensorManager;

    public SensorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorsFragment newInstance(String param1, String param2) {
        SensorsFragment fragment = new SensorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensors, container, false);
        textViewGscope = view.findViewById(R.id.textViewGscope);
        GscopeX = view.findViewById(R.id.GscopeX);
        GscopeY = view.findViewById(R.id.GscopeY);
        GscopeZ = view.findViewById(R.id.GscopeZ);
        textViewMF = view.findViewById(R.id.textViewMF);
        MFieldX = view.findViewById(R.id.MFieldX);
        MFieldY = view.findViewById(R.id.MFieldY);
        MFieldZ = view.findViewById(R.id.MFieldZ);
        textViewLight = view.findViewById(R.id.textViewLight);
        lightIndic = view.findViewById(R.id.lightIndic);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Sensor magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_UI);
        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float valueX = sensorEvent.values[0];
            float valueY = sensorEvent.values[1];
            float valueZ = sensorEvent.values[2];
            GscopeX.setText("X: " + valueX);
            GscopeY.setText("Y: " + valueY);
            GscopeZ.setText("Z: " + valueZ);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float valueX = sensorEvent.values[0];
            float valueY = sensorEvent.values[1];
            float valueZ = sensorEvent.values[2];
            MFieldX.setText("X: " + valueX);
            MFieldY.setText("Y: " + valueY);
            MFieldZ.setText("Z: " + valueZ);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightValue = sensorEvent.values[0];
            lightIndic.setText("value: " + lightValue);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}