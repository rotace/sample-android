package com.example.yasunori.tutorialsensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {

    private static String TAG = "myApp";
    private SensorManager mSensorManager;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView mTextView5;
    private TextView mTextView6;
    private TextView mTextView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTextView3 = (TextView) findViewById(R.id.textView3);
        mTextView4 = (TextView) findViewById(R.id.textView4);
        mTextView5 = (TextView) findViewById(R.id.textView5);
        mTextView6 = (TextView) findViewById(R.id.textView6);
        mTextView7 = (TextView) findViewById(R.id.textView7);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
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
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
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