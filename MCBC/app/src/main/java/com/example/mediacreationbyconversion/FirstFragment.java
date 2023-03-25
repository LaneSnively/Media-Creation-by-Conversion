package com.example.mediacreationbyconversion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mediacreationbyconversion.databinding.FragmentFirstBinding;

import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private SharedViewModel viewModel;
    private String text = "";
    private Paint paint;
    private Bitmap bitmap;
    private Canvas canvas;
    private List<Bitmap> history;
    private int brushSize;
    private String brushShape;
    private float canvasX = 0;
    private float canvasY = 0;
    private int canvasColor;

    private void storeText(String data) {
        viewModel.setText(data);
    }
    private void storePaint(Paint data) { viewModel.setPaint(data); }
    private void storeBitmap(Bitmap data) { viewModel.setBitmap(data); }
    private void storeCanvas(Canvas data) { viewModel.setCanvas(data); }
    private void storeHistory(List<Bitmap> data) { viewModel.setHistory(data); }
    private void storeBrushSize(int data) { viewModel.setBrushSize(data); }
    private void storeBrushShape(String data){ viewModel.setBrushShape(data); }
    private void storeCanvasX(float data) { viewModel.setCanvasX(data); }
    private void storeCanvasY(float data) { viewModel.setCanvasY(data); }
    private void storeCanvasColor(int data) { viewModel.setCanvasColor(data); }

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

        binding.save.setOnClickListener(view16 -> {
            binding.drawingView.save(binding.drawingView.bitmap);
            Toast.makeText(getContext(), "image saved", Toast.LENGTH_SHORT).show();
        });

        binding.saveEvery.setOnClickListener(v -> {
            int count = binding.drawingView.saveEvery();
            Toast.makeText(getContext(),
                    "Saved " + count + " images for a GIF",
                    Toast.LENGTH_SHORT).show();
        });

        binding.loadimage.setOnClickListener(v -> selectImage());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.brushSizePicker.setTextColor(binding.drawingView.white);
            binding.brushSizePicker.setTextSize(100);
            binding.brushSizePicker.setValue(30);
            binding.brushSizePicker.setMinValue(0);
            binding.brushSizePicker.setMaxValue(400);
            binding.brushSizePicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
                binding.drawingView.brushSize = newVal;
            });
        }

        binding.stroke.setOnClickListener(v -> {
            binding.drawingView.paint.setStyle(Paint.Style.STROKE);
            binding.drawingView.paint.setStrokeWidth(1);
            Toast.makeText(getContext(), "Stroke Bristle", Toast.LENGTH_SHORT).show();
        });

        binding.fill.setOnClickListener(v -> {
            binding.drawingView.paint.setStyle(Paint.Style.FILL);
            Toast.makeText(getContext(), "Fill Bristle", Toast.LENGTH_SHORT).show();
        });

        binding.square.setOnClickListener(view14 -> {
            binding.drawingView.brushShape = "square";
            updateDrawingViewModel();
            Toast.makeText(getContext(), "Square Bristle", Toast.LENGTH_SHORT).show();
        });

        binding.circle.setOnClickListener(view14 -> {
            binding.drawingView.brushShape = "circle";
            updateDrawingViewModel();
            Toast.makeText(getContext(), "Circle Bristle", Toast.LENGTH_SHORT).show();
        });

        binding.character.setOnClickListener(view14 -> {
            binding.drawingView.brushShape = "character";
            updateDrawingViewModel();
            Toast.makeText(getContext(), "Character Bristle", Toast.LENGTH_SHORT).show();
        });

        binding.keyboard.setOnClickListener(v -> {
            binding.drawingView.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(binding.drawingView, InputMethodManager.SHOW_IMPLICIT);
        });

        binding.restore.setOnClickListener(v -> {
            if(bitmap != null) restoreDrawing();
            binding.drawingView.invalidate();
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
                    updateDrawingViewModel();
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

    private static final int REQUEST_CODE = 1;

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap immutableB = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                Bitmap mutableB = immutableB.copy(Bitmap.Config.ARGB_8888, true);
                int desiredWidth = binding.drawingView.getWidth();
                int desiredHeight = binding.drawingView.getHeight();
                int width = mutableB.getWidth();
                int height = mutableB.getHeight();
                double scaleX = (double) desiredWidth / width;
                double scaleY = (double) desiredHeight / height;

                if(scaleX > scaleY){
                    desiredWidth = (int) Math.floor(width * scaleY);
                    desiredHeight = (int) Math.floor(height * scaleY);
                }
                else if(scaleX < scaleY){
                    desiredWidth = (int) Math.floor(width * scaleX);
                    desiredHeight = (int) Math.floor(height * scaleX);
                }

                Bitmap scaledImage = Bitmap.createScaledBitmap(mutableB, desiredWidth, desiredHeight, true);
                binding.drawingView.bitmap = scaledImage;
                binding.drawingView.canvas = new Canvas(scaledImage);
                binding.drawingView.invalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        updateDrawingViewModel();
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
        storeText(text);
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