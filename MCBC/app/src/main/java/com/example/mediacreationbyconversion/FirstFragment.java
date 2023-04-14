package com.example.mediacreationbyconversion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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
    public float offsetX = 0;
    public float offsetY = 0;
    private int canvasColor;

    private void storeText(String data) {
        viewModel.setText(data);
    }

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
        viewModel.getOffsetX().observe(getViewLifecycleOwner(), data -> offsetX = data);
        viewModel.getOffsetY().observe(getViewLifecycleOwner(), data -> offsetY = data);
        viewModel.getCanvasColor().observe(getViewLifecycleOwner(), data -> canvasColor = data);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.totextinput.setOnClickListener(v -> {
            updateDrawingViewModel();
            NavHostFragment
                    .findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });

        binding.tofullscreen.setOnClickListener(v -> {
            updateDrawingViewModel();
            NavHostFragment
                    .findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_ThirdFragment);
        });

        binding.save.setOnClickListener(v -> {
            binding.drawingView.save(binding.drawingView.bitmap);
            Toast.makeText(getContext(), "image saved", Toast.LENGTH_SHORT).show();
        });

        binding.saveEvery.setOnClickListener(v -> {
            int count = binding.drawingView.saveEvery();
            Toast.makeText(getContext(),
                    "saved " + count + " images for a GIF",
                    Toast.LENGTH_SHORT).show();
        });

        binding.loadimage.setOnClickListener(v -> selectImage());

        binding.clearcanvas.setOnClickListener(v -> {
            binding.drawingView.clearDrawingView();
            binding.drawingView.invalidate();
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.brushSizePicker.setTextColor(Color.parseColor("#FFFFFF"));
            binding.brushSizePicker.setTextSize(80);
            binding.brushSizePicker.setMinValue(1);
            binding.brushSizePicker.setMaxValue(500);
            binding.brushSizePicker.setValue(binding.drawingView.brushSize);
            binding.brushSizePicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
                binding.drawingView.brushSize = newVal;
            });
        }

        binding.stroke.setOnClickListener(v -> {
            binding.drawingView.paint.setStyle(Paint.Style.STROKE);
            binding.drawingView.paint.setStrokeWidth(1);

            binding.stroke.setBackgroundColor(Color.parseColor("#00008B")); // blueDark
            binding.stroke.setTextColor(Color.parseColor("#7FFFD4")); // tealLight

            binding.fill.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
            binding.fill.setTextColor(Color.parseColor("#00008B")); // blueDark
        });

        binding.fill.setOnClickListener(v -> {
            binding.drawingView.paint.setStyle(Paint.Style.FILL);

            binding.fill.setBackgroundColor(Color.parseColor("#00008B")); // blueDark
            binding.fill.setTextColor(Color.parseColor("#7FFFD4")); // tealLight

            binding.stroke.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
            binding.stroke.setTextColor(Color.parseColor("#00008B")); // blueDark
        });

        binding.square.setOnClickListener(v -> {
            binding.drawingView.brushShape = "square";

            binding.square.setBackgroundColor(Color.parseColor("#00008B")); // blueDark
            binding.square.setTextColor(Color.parseColor("#7FFFD4")); // tealLight

            binding.circle.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
            binding.circle.setTextColor(Color.parseColor("#00008B")); // blueDark

            binding.character.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
            binding.character.setTextColor(Color.parseColor("#00008B")); // blueDark
        });

        binding.circle.setOnClickListener(v -> {
            binding.drawingView.brushShape = "circle";

            binding.circle.setBackgroundColor(Color.parseColor("#00008B")); // blueDark
            binding.circle.setTextColor(Color.parseColor("#7FFFD4")); // tealLight

            binding.square.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
            binding.square.setTextColor(Color.parseColor("#00008B")); // blueDark

            binding.character.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
            binding.character.setTextColor(Color.parseColor("#00008B")); // blueDark
        });

        binding.character.setOnClickListener(v -> {
            binding.drawingView.brushShape = "character";

            binding.character.setBackgroundColor(Color.parseColor("#00008B")); // blueDark
            binding.character.setTextColor(Color.parseColor("#7FFFD4")); // tealLight

            binding.circle.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
            binding.circle.setTextColor(Color.parseColor("#00008B")); // blueDark

            binding.square.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
            binding.square.setTextColor(Color.parseColor("#00008B")); // blueDark
        });

        binding.keyboard.setOnClickListener(v -> {
            binding.drawingView.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(binding.drawingView, InputMethodManager.SHOW_IMPLICIT);
        });

        binding.pastebrush.setOnClickListener(v -> {
            binding.drawingView.canvasX = 0;
            binding.drawingView.canvasY = 0;
            if(text.length() > 5000)
                Toast.makeText(getContext(),
                        "pasting large text brushes can take a while",
                        Toast.LENGTH_LONG).show();
            convertText(text, false);
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
            if(event.isShiftPressed()) binding.drawingView.shift = true;
            else binding.drawingView.shift = false;
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    binding.drawingView.backspace();
                    binding.drawingView.invalidate();
                    return true;
                }
                if (binding.drawingView.keyMap.containsKey(keyCode)) {
                    text = binding.drawingView.keyMap.get(keyCode).toString();
                    binding.textbrushview.setText("text brush:\n" + text);
                    binding.textbrushview.invalidate();
                    convertText(text, true);
                }
                return true;
            }
            return false;
        });

        view.postDelayed(() -> {
            if(binding != null) {
                if (bitmap != null) {
                    restoreDrawing();
                    binding.brushSizePicker.setValue(brushSize);
                    binding.drawingView.invalidate();

                    if(binding.drawingView.brushShape.equals("circle")){
                        binding.circle.setBackgroundColor(Color.parseColor("#00008B")); // blueDark
                        binding.circle.setTextColor(Color.parseColor("#7FFFD4")); // tealLight

                        binding.square.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
                        binding.square.setTextColor(Color.parseColor("#00008B")); // blueDark

                        binding.character.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
                        binding.character.setTextColor(Color.parseColor("#00008B")); // blueDark
                    } else if(binding.drawingView.brushShape.equals("square")){
                        binding.square.setBackgroundColor(Color.parseColor("#00008B")); // blueDark
                        binding.square.setTextColor(Color.parseColor("#7FFFD4")); // tealLight

                        binding.circle.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
                        binding.circle.setTextColor(Color.parseColor("#00008B")); // blueDark

                        binding.character.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
                        binding.character.setTextColor(Color.parseColor("#00008B")); // blueDark
                    } else if(binding.drawingView.brushShape.equals("character")){
                        binding.character.setBackgroundColor(Color.parseColor("#00008B")); // blueDark
                        binding.character.setTextColor(Color.parseColor("#7FFFD4")); // tealLight

                        binding.circle.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
                        binding.circle.setTextColor(Color.parseColor("#00008B")); // blueDark

                        binding.square.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
                        binding.square.setTextColor(Color.parseColor("#00008B")); // blueDark
                    }
                    if(binding.drawingView.paint.getStyle().equals(Paint.Style.FILL)){
                        binding.fill.setBackgroundColor(Color.parseColor("#00008B")); // blueDark
                        binding.fill.setTextColor(Color.parseColor("#7FFFD4")); // tealLight

                        binding.stroke.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
                        binding.stroke.setTextColor(Color.parseColor("#00008B")); // blueDark
                    } else if(binding.drawingView.paint.getStyle().equals(Paint.Style.STROKE)){
                        binding.stroke.setBackgroundColor(Color.parseColor("#00008B")); // blueDark
                        binding.stroke.setTextColor(Color.parseColor("#7FFFD4")); // tealLight

                        binding.fill.setBackgroundColor(Color.parseColor("#7FFFD4")); // tealLight
                        binding.fill.setTextColor(Color.parseColor("#00008B")); // blueDark
                    }
                }
                if(text.length() > 5000){
                    Toast.makeText(getContext(),
                            "loading large text brushes can take a while",
                            Toast.LENGTH_LONG).show();
                    binding.brushSizePicker.setValue(1);
                    binding.drawingView.brushSize = 1;
                }
                if(!text.equals("")){
                    binding.textbrushview.setText("text brush:\n" + text);
                    binding.textbrushview.invalidate();
                }
            }
        }, 10);
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

                if (scaleX > scaleY) {
                    desiredWidth = (int) Math.floor(width * scaleY);
                    desiredHeight = (int) Math.floor(height * scaleY);
                } else if (scaleX < scaleY) {
                    desiredWidth = (int) Math.floor(width * scaleX);
                    desiredHeight = (int) Math.floor(height * scaleX);
                }

                Bitmap scaledImage = Bitmap.createScaledBitmap(mutableB, desiredWidth, desiredHeight, true);
                binding.drawingView.bitmap = scaledImage;
                binding.drawingView.canvas = new Canvas(scaledImage);
                int startX = (binding.drawingView.getWidth() - binding.drawingView.canvas.getWidth()) / 2;
                int startY = (binding.drawingView.getHeight() - binding.drawingView.canvas.getHeight()) / 2;
                binding.drawingView.canvasX = startX;
                binding.drawingView.canvasY = startY;
                binding.drawingView.invalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        storeText(text);
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