package com.example.yasunori.tutorialsplitcost;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

/**
 * Setting Preference Activity
 * Created by yasunori on 16/03/12.
 */
public class SettingPrefActivity extends Activity {

    static public final String PREF_KEY_FRACTION = "key_fraction";
    static public final String PREF_KEY_ROUNDUP = "key_roundup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // call PrefFragment
        getFragmentManager().beginTransaction().replace(
                android.R.id.content , new PrefFragment() ).commit();
    }

    // PrefFragment Class
    public static class PrefFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_pref);

            // set summary
            setSummaryFraction();
        }

        @Override
        public void onResume() {
            super.onResume();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.unregisterOnSharedPreferenceChangeListener(listener);
        }

        @Override
        public void onPause() {
            super.onPause();
            SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
            sp.unregisterOnSharedPreferenceChangeListener(listener);
        }

        private SharedPreferences.OnSharedPreferenceChangeListener listener
                = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(PREF_KEY_FRACTION)) {
                    setSummaryFraction();
                }
            }
        };

        // set fraction summary
        private void setSummaryFraction() {
            ListPreference prefFraction = (ListPreference)findPreference(PREF_KEY_FRACTION);
            prefFraction.setSummary(prefFraction.getEntry());
        }
    }
}
