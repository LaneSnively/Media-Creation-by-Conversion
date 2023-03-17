package com.example.mediacreationbyconversion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mediacreationbyconversion.databinding.FragmentFirstBinding;

import java.util.Stack;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private SharedViewModel viewModel;
    private String text = "";

//    private Paint paint = binding.drawingView.getPaint();
//    private Bitmap bitmap = binding.drawingView.getBitmap();
//    private Canvas canvas = binding.drawingView.getCanvas();
//    private Stack<Bitmap> history = binding.drawingView.getHistory();
//    private int brushSize = binding.drawingView.getBrushSize();
//    private int canvasX = binding.drawingView.getCanvasX();
//    private int canvasY = binding.drawingView.getCanvasY();
//    private int canvasColor = binding.drawingView.getCanvasColor();

//    private Paint paint;
//    private Bitmap bitmap;
//    private Canvas canvas;
//    private Stack<Bitmap> history;
//    private int brushSize;
//    private int canvasX = 0;
//    private int canvasY = 0;
//    private int canvasColor;

//    private void storePaint(Paint data) { viewModel.setPaint(data); }
//    private void storeBitmap(Bitmap data) { viewModel.setBitmap(data); }
//    private void storeCanvas(Canvas data) { viewModel.setCanvas(data); }
//    private void storeHistory(Stack<Bitmap> data) { viewModel.setHistory(data); }
//    private void storeBrushSize(int data) { viewModel.setBrushSize(data); }
//    private void storeCanvasX(int data) { viewModel.setCanvasX(data); }
//    private void storeCanvasY(int data) { viewModel.setCanvasY(data); }
//    private void storeCanvasColor(int data) { viewModel.setCanvasColor(data); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        viewModel.getText().observe(getViewLifecycleOwner(), data -> text = data);
//        viewModel.getPaint().observe(getViewLifecycleOwner(), data -> paint = data);
//        viewModel.getBitmap().observe(getViewLifecycleOwner(), data -> bitmap = data);
//        viewModel.getCanvas().observe(getViewLifecycleOwner(), data -> canvas = data);
//        viewModel.getHistory().observe(getViewLifecycleOwner(), data -> history = data);
//        viewModel.getBrushSize().observe(getViewLifecycleOwner(), data -> brushSize = data);
//        viewModel.getCanvasX().observe(getViewLifecycleOwner(), data -> canvasX = data);
//        viewModel.getCanvasY().observe(getViewLifecycleOwner(), data -> canvasY = data);
//        viewModel.getCanvasColor().observe(getViewLifecycleOwner(), data -> canvasColor = data);
//        if(bitmap != null) resetDrawing();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        updateDrawingViewModel();
//        resetDrawing();
//        if(bitmap != null) resetDrawing();

        binding.totextinput.setOnClickListener(view15 -> {
//            updateDrawingViewModel();
            NavHostFragment
                    .findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });

        binding.save.setOnClickListener(view16 -> binding.drawingView.save());

        binding.smallbrush.setOnClickListener(view17 -> binding.drawingView.setBrushSize(10));

        binding.mediumbrush.setOnClickListener(view18 -> binding.drawingView.setBrushSize(30));

        binding.largebrush.setOnClickListener(view14 -> binding.drawingView.setBrushSize(55));

        binding.whitecanvas.setOnClickListener(view13 -> {
            binding.drawingView.canvasColor = binding.drawingView.white;
            binding.drawingView.canvas.drawColor(binding.drawingView.canvasColor);
            binding.drawingView.resetCanvas();
        });

        binding.blackcanvas.setOnClickListener(view12 -> {
            binding.drawingView.canvasColor = binding.drawingView.black;
            binding.drawingView.canvas.drawColor(binding.drawingView.canvasColor);
            binding.drawingView.resetCanvas();
        });

        binding.papyruscanvas.setOnClickListener(view1 -> {
            binding.drawingView.canvasColor = binding.drawingView.yellowLight;
            binding.drawingView.canvas.drawColor(binding.drawingView.canvasColor);
            binding.drawingView.resetCanvas();
        });

        //make a keyboard button, not onclicklistener
        binding.drawingView.setOnClickListener(v -> {
            if(text.equals("")){
                binding.drawingView.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(binding.drawingView, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        binding.drawingView.setOnTouchListener((v, event) -> {
            binding.drawingView.requestFocus();
            binding.drawingView.canvasX = (int) event.getX();
            binding.drawingView.canvasY = (int) event.getY();
            convertText(text, false);
            return false;
        });

        binding.drawingView.requestFocus();
        binding.drawingView.setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == KeyEvent.ACTION_DOWN){
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    binding.drawingView.backspace();
                    binding.drawingView.invalidate();
                    return true;
                }
                if(binding.drawingView.keymap.containsKey(keyCode))
                    convertText(binding.drawingView.keymap
                            .get(keyCode).toString(), true);
                return true;
            }
            return false;
        });
    }

    public void convertText(String s, boolean addHistory){
        binding.drawingView.convertText(s, addHistory);
//        updateDrawingViewModel();
        binding.drawingView.invalidate();
    }

//    public void resetDrawing(){
//        binding.drawingView.setPaint(paint);
//        binding.drawingView.setBitmap(bitmap);
//        binding.drawingView.setCanvas(canvas);
//        binding.drawingView.setHistory(history);
//        binding.drawingView.setBrushSize(brushSize);
//        binding.drawingView.setCanvasX(canvasX);
//        binding.drawingView.setCanvasY(canvasY);
//        binding.drawingView.setCanvasColor(canvasColor);
//        if(history!=binding.drawingView.getHistory()) Log.v("asdf", "history null");
//        if(paint!=binding.drawingView.getPaint()) Log.v("asdf", "paint null");
//        if(bitmap!=binding.drawingView.getBitmap()) Log.v("asdf", "bitmap null");
//        if(canvas!=binding.drawingView.getCanvas()) Log.v("asdf", "canvas null");
//        if(brushSize!=binding.drawingView.getBrushSize()) Log.v("asdf", "brushSize null");
//        if(canvasColor!=binding.drawingView.getCanvasColor()) Log.v("asdf", "canvasColor null");
//    }

//    public void updateDrawingViewModel(){
//        storePaint(binding.drawingView.getPaint());
//        storeBitmap(binding.drawingView.getBitmap());
//        storeCanvas(binding.drawingView.getCanvas());
//        storeHistory(binding.drawingView.getHistory());
//        storeBrushSize(binding.drawingView.getBrushSize());
//        storeCanvasX(binding.drawingView.getCanvasX());
//        storeCanvasY(binding.drawingView.getCanvasY());
//        storeCanvasColor(binding.drawingView.getCanvasColor());
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}