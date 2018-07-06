/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.app.ActivityManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

import com.android.settings.testutils.SettingsRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

@RunWith(SettingsRobolectricTestRunner.class)
public class SettingsActivityTest {

    @Mock
    private FragmentManager mFragmentManager;
    @Mock
    private ActivityManager.TaskDescription mTaskDescription;
    private SettingsActivity mActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mActivity = spy(new SettingsActivity());
    }

    @Test
    public void launchSettingFragment_nullExtraShowFragment_shouldNotCrash() {
        when(mActivity.getFragmentManager()).thenReturn(mFragmentManager);
        when(mFragmentManager.beginTransaction()).thenReturn(mock(FragmentTransaction.class));

        doReturn(RuntimeEnvironment.application.getClassLoader()).when(mActivity).getClassLoader();

        mActivity.launchSettingFragment(null, true, mock(Intent.class));
    }

    @Test
    public void testSetTaskDescription_IconChanged() {
        mActivity.setTaskDescription(mTaskDescription);

        verify(mTaskDescription).setIcon(anyInt());
    }
}
