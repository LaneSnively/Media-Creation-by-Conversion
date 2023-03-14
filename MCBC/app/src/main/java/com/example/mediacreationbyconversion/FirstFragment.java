package com.example.mediacreationbyconversion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mediacreationbyconversion.databinding.FragmentFirstBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;

    private int s=30; //size of canvas paint brush
    private int w=0; //canvas brush horizontal location
    private int h=0; //canvas brush vertical location
    private boolean editing = false;
    private boolean entered = false;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.totextinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawingView.save();
            }
        });

        binding.smallbrush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = 10;
            }
        });

        binding.mediumbrush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = 30;
            }
        });

        binding.largebrush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = 50;
            }
        });

        binding.whitecanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawingView.canvasColor = binding.drawingView.white;
                binding.drawingView.canvas.drawColor(binding.drawingView.canvasColor);
            }
        });

        binding.blackcanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawingView.canvasColor = binding.drawingView.black;
                binding.drawingView.canvas.drawColor(binding.drawingView.canvasColor);
            }
        });

        binding.papyruscanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawingView.canvasColor = binding.drawingView.yellowLight;
                binding.drawingView.canvas.drawColor(binding.drawingView.canvasColor);
            }
        });

        binding.drawingView.requestFocus();
        binding.drawingView.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    switch(keyCode){
                        case KeyEvent.KEYCODE_Q:
                            binding.drawingView.paint.setColor(binding.drawingView.grayDark);
                            break;
                        case KeyEvent.KEYCODE_W:
                            binding.drawingView.paint.setColor(binding.drawingView.pinkDark);
                            break;
                        case KeyEvent.KEYCODE_E:
                            binding.drawingView.paint.setColor(binding.drawingView.purpleDark);
                            break;
                        case KeyEvent.KEYCODE_R:
                            binding.drawingView.paint.setColor(binding.drawingView.redDark);
                            break;
                        case KeyEvent.KEYCODE_T:
                            binding.drawingView.paint.setColor(binding.drawingView.orangeDark);
                            break;
                        case KeyEvent.KEYCODE_Y:
                            binding.drawingView.paint.setColor(binding.drawingView.brownDark);
                            break;
                        case KeyEvent.KEYCODE_U:
                            binding.drawingView.paint.setColor(binding.drawingView.yellowDark);
                            break;
                        case KeyEvent.KEYCODE_I:
                            binding.drawingView.paint.setColor(binding.drawingView.greenDark);
                            break;
                        case KeyEvent.KEYCODE_O:
                            binding.drawingView.paint.setColor(binding.drawingView.tealDark);
                            break;
                        case KeyEvent.KEYCODE_P:
                            binding.drawingView.paint.setColor(binding.drawingView.blueDark);
                            break;
                        case KeyEvent.KEYCODE_A:
                            binding.drawingView.paint.setColor(binding.drawingView.pink);
                            break;
                        case KeyEvent.KEYCODE_S:
                            binding.drawingView.paint.setColor(binding.drawingView.purple);
                            break;
                        case KeyEvent.KEYCODE_D:
                            binding.drawingView.paint.setColor(binding.drawingView.red);
                            break;
                        case KeyEvent.KEYCODE_F:
                            binding.drawingView.paint.setColor(binding.drawingView.orange);
                            break;
                        case KeyEvent.KEYCODE_G:
                            binding.drawingView.paint.setColor(binding.drawingView.brown);
                            break;
                        case KeyEvent.KEYCODE_H:
                            binding.drawingView.paint.setColor(binding.drawingView.yellow);
                            break;
                        case KeyEvent.KEYCODE_J:
                            binding.drawingView.paint.setColor(binding.drawingView.green);
                            break;
                        case KeyEvent.KEYCODE_K:
                            binding.drawingView.paint.setColor(binding.drawingView.teal);
                            break;
                        case KeyEvent.KEYCODE_L:
                            binding.drawingView.paint.setColor(binding.drawingView.blue);
                            break;
                        case KeyEvent.KEYCODE_Z:
                            binding.drawingView.paint.setColor(binding.drawingView.purpleLight);
                            break;
                        case KeyEvent.KEYCODE_X:
                            binding.drawingView.paint.setColor(binding.drawingView.redLight);
                            break;
                        case KeyEvent.KEYCODE_C:
                            binding.drawingView.paint.setColor(binding.drawingView.orangeLight);
                            break;
                        case KeyEvent.KEYCODE_V:
                            binding.drawingView.paint.setColor(binding.drawingView.brownLight);
                            break;
                        case KeyEvent.KEYCODE_B:
                            binding.drawingView.paint.setColor(binding.drawingView.greenLight);
                            break;
                        case KeyEvent.KEYCODE_N:
                            binding.drawingView.paint.setColor(binding.drawingView.tealLight);
                            break;
                        case KeyEvent.KEYCODE_M:
                            binding.drawingView.paint.setColor(binding.drawingView.blueLight);
                            break;
                        case KeyEvent.KEYCODE_ENTER:
                            if(h<binding.drawingView.canvas.getHeight()-s){
                                h += s; //move brush down
                            } else {
                                h = 0; //brush hit bottom, move back to top
                            }
                            if(!entered) {
                                w -= s;
                                entered = true;
                            }
                            binding.drawingView.invalidate();
                            return true;
                        case KeyEvent.KEYCODE_SPACE:
                            //rectangle brush moving logic
                            if(w>=binding.drawingView.canvas.getWidth()-s){
                                w = 0; //brush hit right side, move back to left
                                if(h>=binding.drawingView.canvas.getHeight()-s){
                                    h = 0; //brush hit bottom, move back to top
                                } else {
                                    h += s; //move brush down
                                }
                            } else {
                                w += s; //move brush right
                            }
                            binding.drawingView.invalidate();
                            return true;
                        case KeyEvent.KEYCODE_DEL:
                            binding.drawingView.backspace();
                            binding.drawingView.invalidate();
                            return true;
                        case KeyEvent.KEYCODE_NAVIGATE_OUT:
                            editing = false;
                            return true;
                        default:
                            return true;
                    }

                    //drawing on the canvas at location (w,h)
                    binding.drawingView.canvas
                            .drawRect((float) w, (float) h, (float) w+s, (float) h+s,
                                    binding.drawingView.paint);

                    //rectangle brush moving logic
                    if(w>=binding.drawingView.canvas.getWidth()-s){
                        w = 0; //brush hit right side, move back to left
                        if(h>=binding.drawingView.canvas.getHeight()-s){
                            h = 0; //brush hit bottom, move back to top
                        } else {
                            h += s; //move brush down
                        }
                    } else {
                        w += s; //move brush right
                    }

                    entered = false;

                    binding.drawingView.addHistory();
                    binding.drawingView.invalidate();
                    return true;
                }
                return false;
            }
        });

        binding.drawingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawingView.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(binding.drawingView, InputMethodManager.SHOW_IMPLICIT);
                editing = true;
            }
        });

        binding.drawingView.setOnTouchListener((v, event) -> {
            if(editing){
                binding.drawingView.requestFocus();
                w = (int) event.getX();
                h = (int) event.getY();
            }
            return false;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}