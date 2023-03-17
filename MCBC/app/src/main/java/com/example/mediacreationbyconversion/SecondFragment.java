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

import com.example.mediacreationbyconversion.databinding.FragmentFirstBinding;
import com.example.mediacreationbyconversion.databinding.FragmentSecondBinding;

import java.util.Objects;
import java.util.Stack;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private SharedViewModel viewModel;
    private String text = "";
//    private Paint paint = new Paint();
//    private Bitmap bitmap = null;
//    private Canvas canvas = null;
//    private Stack<Bitmap> history = new Stack<>();
//    private int brushSize = 0;
//    private int canvasX = 0;
//    private int canvasY = 0;
//    private int canvasColor = 0;

    private void storeText(String data) {
        viewModel.setText(data);
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
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        viewModel.getText().observe(getViewLifecycleOwner(), data -> text = data);
//        viewModel.getPaint().observe(getViewLifecycleOwner(), data -> paint = data);
//        viewModel.getBitmap().observe(getViewLifecycleOwner(), data -> bitmap = data);
//        viewModel.getCanvas().observe(getViewLifecycleOwner(), data -> canvas = data);
//        viewModel.getHistory().observe(getViewLifecycleOwner(), data -> history = data);
//        viewModel.getBrushSize().observe(getViewLifecycleOwner(), data -> brushSize = data);
//        viewModel.getCanvasX().observe(getViewLifecycleOwner(), data -> canvasX = data);
//        viewModel.getCanvasY().observe(getViewLifecycleOwner(), data -> canvasY = data);
//        viewModel.getCanvasColor().observe(getViewLifecycleOwner(), data -> canvasColor = data);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tokeyboardinput.setOnClickListener(view1 -> {
            storeText(text);
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

        binding.submit.setOnClickListener(v -> {
            text = Objects.requireNonNull(binding.inputtext.getText()).toString();
            storeText(text);
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}