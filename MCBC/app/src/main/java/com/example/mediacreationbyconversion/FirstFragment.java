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

    private int s=30; //size of canvas paint brush
    public int w=s; //canvas brush horizontal location
    public int h=s; //canvas brush vertical location

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
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
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
                        case KeyEvent.KEYCODE_SPACE:
                            //brush moving logic
                            if(w>binding.drawingView.canvas.getWidth()-2*s){
                                w = s; //brush hit right side, move back to left
                                if(h>binding.drawingView.canvas.getHeight()-2*s){
                                    h = s; //brush hit bottom, move back to top
                                } else {
                                    h += s; //move brush right
                                }
                            } else {
                                w += s; //move brush down
                            }
                            binding.drawingView.invalidate();
                            return true;
                        default:
                            break;
                    }


                    //random drawing
//                    binding.drawingView.canvas
//                        .drawCircle((float) (Math.random()*binding.drawingView.canvas.getWidth()),
//                                (float) (Math.random()*binding.drawingView.canvas.getHeight()),
//                                (float) (Math.random()*100),
//                                binding.drawingView.paint);



                    //drawing on the canvas at location (w,h)
                    binding.drawingView.canvas
                            .drawCircle((float) w, (float) h, (float) s,
                                    binding.drawingView.paint);

                    //brush moving logic
                    if(w>binding.drawingView.canvas.getWidth()-2*s){
                        w = s; //brush hit right side, move back to left
                        if(h>binding.drawingView.canvas.getHeight()-2*s){
                            h = s; //brush hit bottom, move back to top
                        } else {
                            h += s; //move brush right
                        }
                    } else {
                        w += s; //move brush down
                    }

                    //update bitmap
                    binding.drawingView.previousBitmap = binding.drawingView.bitmap
                            .copy(Bitmap.Config.ARGB_8888, true);

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
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}