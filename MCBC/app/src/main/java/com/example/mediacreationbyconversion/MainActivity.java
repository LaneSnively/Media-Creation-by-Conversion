package com.example.mediacreationbyconversion;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.example.mediacreationbyconversion.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.keycolorlayout.setOnClickListener(v -> {

        });
        View view = binding.getRoot();
        setContentView(view);
    }
}