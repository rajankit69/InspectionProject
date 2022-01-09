package com.theartofdev.edmodo.cropper;

import androidx.appcompat.app.AppCompatActivity;



import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



import com.jcminarro.philology.Philology;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * Created by ADMIN on 5/9/2018.
 */

public class BaseActivity extends AppCompatActivity {

    public LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(Philology.INSTANCE.wrap(newBase)));
    }













    }









