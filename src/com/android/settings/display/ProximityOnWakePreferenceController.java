/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.android.settings.display;

import android.content.Context;
import android.provider.Settings;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.Preference;

import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.settings.preferences.SystemSettingSwitchPreference;
import com.android.settings.core.PreferenceControllerMixin;

public class ProximityOnWakePreferenceController extends AbstractPreferenceController implements
        Preference.OnPreferenceChangeListener, PreferenceControllerMixin {

    private static final String KEY_PROXIMITY_WAKE = "proximity_on_wake";

    public ProximityOnWakePreferenceController(Context context) {
        super(context);
    }

    @Override
    public String getPreferenceKey() {
        return KEY_PROXIMITY_WAKE;
    }

    @Override
    public boolean isAvailable() {
        return mContext.getResources().getBoolean(
                com.android.internal.R.bool.config_proximityCheckOnWake);
    }

    @Override
    public void updateState(Preference preference) {
        int value = Settings.System.getInt(
                mContext.getContentResolver(), Settings.System.PROXIMITY_ON_WAKE, 1);
        ((SwitchPreference) preference).setChecked(value != 0);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean value = (Boolean) newValue;
        Settings.System.putInt(
                mContext.getContentResolver(), Settings.System.PROXIMITY_ON_WAKE, value ? 1 : 0);
        return true;
    }
}
