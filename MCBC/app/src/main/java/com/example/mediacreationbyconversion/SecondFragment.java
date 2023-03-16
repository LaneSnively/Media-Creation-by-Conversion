package com.example.mediacreationbyconversion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mediacreationbyconversion.databinding.FragmentSecondBinding;

import java.util.Objects;

public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;
    private String text = "";
    private SharedViewModel viewModel;
    private DrawingView drawing;

    private void storeText(String data) {
        viewModel.setText(data);
    }

//    private void storeDrawing(DrawingView data) {
//        viewModel.setDrawing(data);
//    }

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
//        viewModel.getDrawing().observe(getViewLifecycleOwner(), data -> drawing = data);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tokeyboardinput.setOnClickListener(view1 -> {
            storeText(text);
//            storeDrawing(drawing);
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

        binding.submit.setOnClickListener(v -> {
            text = Objects.requireNonNull(binding.inputtext.getText()).toString();
            storeText(text);
//            storeDrawing(drawing);
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