package com.example.mediacreationbyconversion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
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

    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;
    private Stack<Bitmap> history;
    private int brushSize;
    private String brushShape;
    private float canvasX = 0;
    private float canvasY = 0;
    private int canvasColor;

    private void storePaint(Paint data) { viewModel.setPaint(data); }
    private void storeBitmap(Bitmap data) { viewModel.setBitmap(data); }
    private void storeCanvas(Canvas data) { viewModel.setCanvas(data); }
    private void storeHistory(Stack<Bitmap> data) { viewModel.setHistory(data); }
    private void storeBrushSize(int data) { viewModel.setBrushSize(data); }
    private void storeBrushShape(String data){ viewModel.setBrushShape(data); }
    private void storeCanvasX(float data) { viewModel.setCanvasX(data); }
    private void storeCanvasY(float data) { viewModel.setCanvasY(data); }
    private void storeCanvasColor(int data) { viewModel.setCanvasColor(data); }
    private boolean stored = false;

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
        viewModel.getPaint().observe(getViewLifecycleOwner(), data -> paint = data);
        viewModel.getBitmap().observe(getViewLifecycleOwner(), data -> bitmap = data);
        viewModel.getCanvas().observe(getViewLifecycleOwner(), data -> canvas = data);
        viewModel.getHistory().observe(getViewLifecycleOwner(), data -> history = data);
        viewModel.getBrushSize().observe(getViewLifecycleOwner(), data -> brushSize = data);
        viewModel.getBrushShape().observe(getViewLifecycleOwner(), data -> brushShape = data);
        viewModel.getCanvasX().observe(getViewLifecycleOwner(), data -> canvasX = data);
        viewModel.getCanvasY().observe(getViewLifecycleOwner(), data -> canvasY = data);
        viewModel.getCanvasColor().observe(getViewLifecycleOwner(), data -> canvasColor = data);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.totextinput.setOnClickListener(view15 -> {
            updateDrawingViewModel();
            NavHostFragment
                    .findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });

        binding.save.setOnClickListener(view16 -> binding.drawingView.save());

        binding.smallbrush.setOnClickListener(view17 -> binding.drawingView.brushSize = 10);

        binding.mediumbrush.setOnClickListener(view18 -> binding.drawingView.brushSize = 30);

        binding.largebrush.setOnClickListener(view14 -> binding.drawingView.brushSize = 55);

        binding.whitecanvas.setOnClickListener(view13 -> {
            binding.drawingView.canvasColor = binding.drawingView.white;
            binding.drawingView.resetCanvas();
        });

        binding.blackcanvas.setOnClickListener(view12 -> {
            binding.drawingView.canvasColor = binding.drawingView.black;
            binding.drawingView.resetCanvas();
        });

        binding.papyruscanvas.setOnClickListener(view1 -> {
            binding.drawingView.canvasColor = binding.drawingView.yellowLight;
            binding.drawingView.resetCanvas();
        });

        //make a keyboard button, not onclicklistener
        binding.drawingView.setOnClickListener(v -> {
            if(bitmap != null) restoreDrawing();
            binding.drawingView.invalidate();

            if(text.equals("")){
                binding.drawingView.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(binding.drawingView, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        binding.drawingView.setOnTouchListener((v, event) -> {
            binding.drawingView.requestFocus();
            binding.drawingView.canvasX = event.getX();
            binding.drawingView.canvasY = event.getY();
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

//    @Override
//    public void onResume() {
//        super.onResume();
//        if(bitmap != null) restoreDrawing();
//        binding.drawingView.invalidate();
//        Log.v("asdf", "onresume");
//
//    }

    public void convertText(String s, boolean addHistory){
        binding.drawingView.convertText(s, addHistory);
        binding.drawingView.invalidate();
    }

    public void restoreDrawing(){
        binding.drawingView.restoreDrawingView(paint,
                bitmap,
                canvas,
                history,
                brushSize,
                brushShape,
                canvasX,
                canvasY,
                canvasColor);
    }

    public void updateDrawingViewModel(){
        storePaint(binding.drawingView.paint);
        storeBitmap(binding.drawingView.bitmap);
        storeCanvas(binding.drawingView.canvas);
        storeHistory(binding.drawingView.history);
        storeBrushSize(binding.drawingView.brushSize);
        storeBrushShape(binding.drawingView.brushShape);
        storeCanvasX(binding.drawingView.canvasX);
        storeCanvasY(binding.drawingView.canvasY);
        storeCanvasColor(binding.drawingView.canvasColor);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}