package com.cyberfox21.apptobindservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.cyberfox21.apptobindservice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding = null;
    private Intent intent;
    private Boolean bound = false;
    private ServiceConnection sConn;

    private static final String LOG_TAG = "CHECKER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        intent = new Intent("com.cyberfox21.serviceapp.MyService");
        intent.setPackage("com.cyberfox21.serviceapp");

        sConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                bound = true;
                Log.d(LOG_TAG, "onServiceConnected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bound = false;
                Log.d(LOG_TAG, "onServiceDisconnected");
            }
        };

        setContentView(binding.getRoot());
        setupViews();
    }

    private void setupViews(){
        binding.btnBind.setOnClickListener(v -> bind());
        binding.btnUnBind.setOnClickListener(v -> unbind());
        binding.btnStart.setOnClickListener(v -> start());
        binding.btnStop.setOnClickListener(v -> stop());
    }

    private void bind(){
        bindService(intent, sConn, BIND_AUTO_CREATE);
    }

    private void unbind(){
        if(!bound) return;
        else unbindService(sConn);
        bound = false;
    }

    private void start(){
        startService(intent);
    }

    private void stop(){
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        unbindService(null);
        super.onDestroy();
    }
}