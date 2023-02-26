package com.example.mediacreationbyconversion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mediacreationbyconversion.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        binding.drawingView.requestFocus();
        binding.drawingView.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                switch(keyCode){
                    case KeyEvent.KEYCODE_A:
                        binding.drawingView.paint.setColor(Color.RED);
                        break;
                    case KeyEvent.KEYCODE_B:
                        binding.drawingView.paint.setColor(Color.GREEN);
                        break;
                    case KeyEvent.KEYCODE_C:
                        binding.drawingView.paint.setColor(Color.BLUE);
                        break;
                    case KeyEvent.KEYCODE_SPACE:
                        binding.drawingView.paint.setColor(Color.YELLOW);
                        break;
                    default:
                        binding.drawingView.canvas
                                .drawCircle((float) (Math.random()*binding.drawingView.canvas.getWidth()),
                                        (float) (Math.random()*binding.drawingView.canvas.getHeight()),
                                        (float) (Math.random()*100),
                                        binding.drawingView.paint);
                        binding.drawingView.previousBitmap = binding.drawingView.bitmap
                                .copy(Bitmap.Config.ARGB_8888, true);
                        break;
                }
                binding.drawingView.invalidate();
                return true;
            }
        });

        binding.drawingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawingView.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(binding.drawingView, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}