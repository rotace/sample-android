package com.example.samplenavidrawer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SensorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SensorFragment extends Fragment implements SensorEventListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SensorFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SensorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SensorFragment newInstance(String param1, String param2) {
        SensorFragment fragment = new SensorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private static String TAG = "Sensor";
    private SensorManager mSensorManager;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView mTextView5;
    private TextView mTextView6;
    private TextView mTextView7;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensor, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView2 = (TextView) view.findViewById(R.id.textView2);
        mTextView3 = (TextView) view.findViewById(R.id.textView3);
        mTextView4 = (TextView) view.findViewById(R.id.textView4);
        mTextView5 = (TextView) view.findViewById(R.id.textView5);
        mTextView6 = (TextView) view.findViewById(R.id.textView6);
        mTextView7 = (TextView) view.findViewById(R.id.textView7);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        if (sensors.size() > 0) {
            Log.i(TAG, "sensorNo.:" + sensors.size());
            for (Sensor s : sensors) {
                mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_UI);
                String typeName = "";
                switch (s.getType()) {
                    case Sensor.TYPE_ACCELEROMETER:
                        typeName = "TYPE_ACCELEROMETER";
                        break;
                    case Sensor.TYPE_PROXIMITY:
                        typeName = "TYPE_PROXIMITY";
                        break;
                    case Sensor.TYPE_MAGNETIC_FIELD:
                        typeName = "TYPE_MAGNETIC_FIELD";
                        break;
                    case Sensor.TYPE_LIGHT:
                        typeName = "TYPE_LIGHT";
                        break;
                    case Sensor.TYPE_GRAVITY:
                        typeName = "TYPE_GRAVITY";
                        break;
                    case Sensor.TYPE_AMBIENT_TEMPERATURE:
                        typeName = "TYPE_AMBIENT_TEMPERATURE";
                        break;
                    case Sensor.TYPE_GYROSCOPE:
                        typeName = "TYPE_GYROSCOPE";
                        break;
                }
                Log.i(TAG, s.getName() + " : " + s.getType() + " : " + typeName);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float value = event.values[0];

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                mTextView2.setText("accel");
                mTextView3.setText(
                        " x:" + String.format("%.2f", event.values[0]) +
                                " y:" + String.format("%.2f", event.values[1]) +
                                " z:" + String.format("%.2f", event.values[2]));
                break;
            case Sensor.TYPE_PROXIMITY:
                mTextView4.setText("proximity");
                mTextView5.setText("" + value);
                break;
            case Sensor.TYPE_LIGHT:
                mTextView6.setText("light");
                mTextView7.setText("" + value);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                break;

//            default:
//                new AlertDialog.Builder(this)
//                        .setTitle("log")
//                        .setMessage(event.sensor.getName()+":"+event.sensor.getType())
//                        .setPositiveButton("OK",null)
//                        .show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
