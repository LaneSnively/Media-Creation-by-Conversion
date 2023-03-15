package com.example.mediacreationbyconversion;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mediacreationbyconversion.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    private String text = "";
    private SharedViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String data) {
                text = data;
            }
        });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.totextinput.setOnClickListener(view15 -> NavHostFragment
                .findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment));

        binding.save.setOnClickListener(view16 -> binding.drawingView.save());

        binding.smallbrush.setOnClickListener(view17 -> binding.drawingView.brushSize = 10);

        binding.mediumbrush.setOnClickListener(view18 -> binding.drawingView.brushSize = 30);

        binding.largebrush.setOnClickListener(view14 -> binding.drawingView.brushSize = 50);

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
                switch(keyCode){
                    case KeyEvent.KEYCODE_DEL:
                        binding.drawingView.backspace();
                        binding.drawingView.invalidate();
                        return true;
                    default:
                        break;
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
        binding.drawingView.invalidate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}