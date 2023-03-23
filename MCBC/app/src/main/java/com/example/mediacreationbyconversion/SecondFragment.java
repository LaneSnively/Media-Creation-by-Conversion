package com.example.mediacreationbyconversion;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mediacreationbyconversion.databinding.FragmentFirstBinding;
import com.example.mediacreationbyconversion.databinding.FragmentSecondBinding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Stack;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private SharedViewModel viewModel;
    private String text = "";

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
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tokeyboardinput.setOnClickListener(view1 -> {
            text = Objects.requireNonNull(binding.inputtext.getText()).toString();
            storeText(text);
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

        binding.selectfile.setOnClickListener(v -> readFile());

        binding.savefile.setOnClickListener(v -> createFile());
    }

    private static final int READ_REQUEST_CODE = 42;

    public void readFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain"); // Only show text files in the file picker
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    private static final int CREATE_FILE = 43;

    private void createFile() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain"); // Only show text files in the file picker
        intent.putExtra(Intent.EXTRA_TITLE, "brush.txt");
        startActivityForResult(intent, CREATE_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        Uri uri = null;
        if(resultCode == Activity.RESULT_OK)
            uri = resultData.getData();
        if (requestCode == READ_REQUEST_CODE) {
            try {
                binding.inputtext.setText(readTextFromUri(uri));
                text = Objects.requireNonNull(binding.inputtext.getText()).toString();
                storeText(text);
                binding.inputtext.invalidate();
            } catch (Exception e){}
        }
        else if (requestCode == CREATE_FILE) {
            try {
                text = Objects.requireNonNull(binding.inputtext.getText()).toString();
                storeText(text);
                alterDocument(uri);
            } catch (Exception e){}
        }
    }

    private String readTextFromUri(Uri uri) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = requireActivity().getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private void alterDocument(Uri uri) {
        try {
            ParcelFileDescriptor txt = getActivity().getContentResolver().
                    openFileDescriptor(uri, "w");
            FileOutputStream fileOutputStream =
                    new FileOutputStream(txt.getFileDescriptor());
            fileOutputStream.getChannel().truncate(0);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
            txt.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}