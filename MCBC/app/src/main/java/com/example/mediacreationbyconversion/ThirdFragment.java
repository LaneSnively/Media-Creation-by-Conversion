package com.example.mediacreationbyconversion;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mediacreationbyconversion.databinding.FragmentThirdBinding;

import java.util.List;

public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;
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
    public float offsetX = 0;
    public float offsetY = 0;
    private int canvasColor;

    private void storePaint(Paint data) {
        viewModel.setPaint(data);
    }

    private void storeBitmap(Bitmap data) {
        viewModel.setBitmap(data);
    }

    private void storeCanvas(Canvas data) {
        viewModel.setCanvas(data);
    }

    private void storeHistory(List<Bitmap> data) {
        viewModel.setHistory(data);
    }

    private void storeBrushSize(int data) {
        viewModel.setBrushSize(data);
    }

    private void storeBrushShape(String data) {
        viewModel.setBrushShape(data);
    }

    private void storeCanvasX(float data) {
        viewModel.setCanvasX(data);
    }

    private void storeCanvasY(float data) {
        viewModel.setCanvasY(data);
    }

    private void storeOffsetX(float data) {
        viewModel.setOffsetX(data);
    }

    private void storeOffsetY(float data) {
        viewModel.setOffsetY(data);
    }

    private void storeCanvasColor(int data) {
        viewModel.setCanvasColor(data);
    }

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
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        viewModel.getText().observe(getViewLifecycleOwner(), data -> text = data);
        viewModel.getPaint().observe(getViewLifecycleOwner(), data -> paint = data);
        viewModel.getBitmap().observe(getViewLifecycleOwner(), data -> bitmap = data);
        viewModel.getCanvas().observe(getViewLifecycleOwner(), data -> canvas = data);
        viewModel.getHistory().observe(getViewLifecycleOwner(), data -> history = data);
        viewModel.getBrushSize().observe(getViewLifecycleOwner(), data -> brushSize = data);
        viewModel.getBrushShape().observe(getViewLifecycleOwner(), data -> brushShape = data);
        viewModel.getCanvasX().observe(getViewLifecycleOwner(), data -> canvasX = data);
        viewModel.getCanvasY().observe(getViewLifecycleOwner(), data -> canvasY = data);
        viewModel.getOffsetX().observe(getViewLifecycleOwner(), data -> offsetX = data);
        viewModel.getOffsetY().observe(getViewLifecycleOwner(), data -> offsetY = data);
        viewModel.getCanvasColor().observe(getViewLifecycleOwner(), data -> canvasColor = data);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tokeyboardinput.setOnClickListener(v -> {
            updateDrawingViewModel();
            NavHostFragment
                    .findNavController(ThirdFragment.this)
                    .navigate(R.id.action_ThirdFragment_to_FirstFragment);
        });

        binding.drawingView.setOnTouchListener((v, event) -> {
            binding.drawingView.requestFocus();
            binding.drawingView.canvasX = event.getX();
            binding.drawingView.canvasY = event.getY();
            convertText(text, false);
            return false;
        });

        view.postDelayed(() -> {
            if(binding != null) {
                if (bitmap != null) {
                    restoreDrawing();
                    binding.drawingView.invalidate();
                }
            }
        }, 10);
    }

    public void convertText(String s, boolean addHistory) {
        binding.drawingView.convertText(s, addHistory);
        binding.drawingView.invalidate();
    }

    public void restoreDrawing() {
        Bitmap b = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        int desiredWidth = binding.drawingView.getWidth();
        int desiredHeight = binding.drawingView.getHeight();
        int width = b.getWidth();
        int height = b.getHeight();
        double scaleX = (double) desiredWidth / width;
        double scaleY = (double) desiredHeight / height;

        if (scaleX > scaleY) {
            desiredWidth = (int) Math.floor(width * scaleY);
            desiredHeight = (int) Math.floor(height * scaleY);
        } else if (scaleX < scaleY) {
            desiredWidth = (int) Math.floor(width * scaleX);
            desiredHeight = (int) Math.floor(height * scaleX);
        }

        Bitmap scaledImage = Bitmap.createScaledBitmap(b, desiredWidth, desiredHeight, true);
        bitmap = scaledImage;
        canvas = new Canvas(scaledImage);

        binding.drawingView.restoreDrawingView(paint,
                bitmap,
                canvas,
                history,
                brushSize,
                brushShape,
                canvasX,
                canvasY,
                offsetX,
                offsetY,
                canvasColor);
    }

    public void updateDrawingViewModel() {
        storePaint(binding.drawingView.paint);
        storeBitmap(binding.drawingView.bitmap);
        storeCanvas(binding.drawingView.canvas);
        storeHistory(binding.drawingView.history);
        storeBrushSize(binding.drawingView.brushSize);
        storeBrushShape(binding.drawingView.brushShape);
        storeCanvasX(binding.drawingView.canvasX);
        storeCanvasY(binding.drawingView.canvasY);
        storeOffsetX(binding.drawingView.offsetX);
        storeOffsetY(binding.drawingView.offsetY);
        storeCanvasColor(binding.drawingView.canvasColor);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}