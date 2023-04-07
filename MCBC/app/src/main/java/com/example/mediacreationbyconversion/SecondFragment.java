package com.example.mediacreationbyconversion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mediacreationbyconversion.databinding.FragmentSecondBinding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SecondFragment extends Fragment {
    int white = Color.parseColor("#FFFFFF");
    int whiteDark = Color.parseColor("#F2F2F2");
    int whiteDarker = Color.parseColor("#D9D9D9");

    int grayLighter = Color.parseColor("#BFBFBF");
    int grayLight = Color.parseColor("#A6A6A6");
    int gray = Color.parseColor("#8C8C8C");
    int grayDark = Color.parseColor("#737373");
    int grayDarker = Color.parseColor("#595959");

    int blackLight = Color.parseColor("#404040");
    int black = Color.parseColor("#000000");

    int pink = Color.parseColor("#FF69B4");
    int pinkDark = Color.parseColor("#FF1493");

    int purpleLight = Color.parseColor("#EE82EE");
    int purple = Color.parseColor("#9932CC");
    int purpleDark = Color.parseColor("#800080");

    int redLight = Color.parseColor("#FFA07A");
    int red = Color.parseColor("#FF0000");
    int redDark = Color.parseColor("#800000");

    int orange = Color.parseColor("#FFA500");
    int orangeDark = Color.parseColor("#FF4500");

    int brownLight = Color.parseColor("#FFD180");
    int brown = Color.parseColor("#FF9100");
    int brownDark = Color.parseColor("#DD2C00");

    int yellow = Color.parseColor("#FFFF00");
    int yellowDark = Color.parseColor("#CCCC00");

    int greenLight = Color.parseColor("#98FB98");
    int greenDark = Color.parseColor("#556B2F");

    int teal = Color.parseColor("#00CED1");
    int tealDark = Color.parseColor("#008080");

    int blue = Color.parseColor("#0000FF");
    int blueDark = Color.parseColor("#00008B");

    public Map<Integer, Character> colorMap = new HashMap<Integer, Character>(){{
        put(white, '1');
        put(whiteDark, '2');
        put(whiteDarker, '3');
        put(grayLighter, '4');
        put(grayLight, '5');
        put(gray, '6');
        put(grayDark, '7');
        put(grayDarker, '8');
        put(blackLight, '9');
        put(black, '0');

        put(pink, 'a');
        put(brown, 'b');
        put(blue, 'c');
        put(red, 'd');
        put(purpleDark, 'e');
        put(orange, 'f');
        put(brownLight, 'g');
        put(yellow, 'h');
        put(greenDark, 'i');
        put(teal, 'j');
        put(tealDark, 'k');
        put(blueDark, 'l');
        put(yellowDark, 'm');
        put(greenLight, 'n');
        put(tealDark, 'o');
        put(blueDark, 'p');
        put(grayDark, 'q');
        put(redDark, 'r');
        put(purple, 's');
        put(orangeDark, 't');
        put(yellowDark, 'u');
        put(greenLight, 'v');
        put(pinkDark, 'w');
        put(redLight, 'x');
        put(brownDark, 'y');
        put(purpleLight, 'z');
    }};

    private FragmentSecondBinding binding;
    private SharedViewModel viewModel;
    private String text = "";
    private Bitmap bitmap;

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
        viewModel.getBitmap().observe(getViewLifecycleOwner(), data -> bitmap = data);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.keyboard.setOnClickListener(v -> {
            binding.inputtext.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(binding.inputtext, InputMethodManager.SHOW_IMPLICIT);
        });

        binding.tokeyboardinput.setOnClickListener(view1 -> {
            if (binding.inputtext.getText() != null)
                text = Objects.requireNonNull(binding.inputtext.getText()).toString();
            if (!text.equals(""))
                storeText(text);
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

        binding.convertToText.setOnClickListener(v -> {
            Toast.makeText(getContext(), "begin converting canvas to text", Toast.LENGTH_SHORT).show();
            if(bitmap != null) text = convertBitmapToString(bitmap);
            binding.inputtext.setText(text);
            storeText(Objects.requireNonNull(binding.inputtext.getText()).toString());
        });

        binding.selectfile.setOnClickListener(v -> readFile());

        binding.savefile.setOnClickListener(v -> {
            createFile();
            Toast.makeText(getContext(), "saved brush", Toast.LENGTH_SHORT).show();
        });

        view.postDelayed(() -> {
            if(bitmap != null && binding != null) binding.canvas.setImageBitmap(bitmap);
        }, 150);
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
        super.onActivityResult(requestCode, resultCode, resultData);
        Uri uri = null;
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK && resultData != null && resultData.getData() != null) {
            try {
                uri = resultData.getData();
                binding.inputtext.setText(readTextFromUri(uri));
                storeText(Objects.requireNonNull(binding.inputtext.getText()).toString());
                binding.inputtext.invalidate();
            } catch (Exception e) {
            }
        } else if (requestCode == CREATE_FILE) {
            try {
                uri = resultData.getData();
                storeText(Objects.requireNonNull(binding.inputtext.getText()).toString());
                alterDocument(uri);
            } catch (Exception e) {
            }
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
            fileOutputStream.write(Objects.requireNonNull(binding.inputtext.getText()).toString().getBytes());
            fileOutputStream.close();
            txt.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertBitmapToString(Bitmap b) {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                int pixelColor = b.getPixel(x, y);
                int closestColor = findClosestColor(pixelColor);
                char character = colorMap.get(closestColor);
                result.append(character);
            }
            result.append('\n');
        }
        result.append('\n');
        return result.toString();
    }

    private int findClosestColor(int pixelColor) {
        int minDistance = Integer.MAX_VALUE;
        int closestColor = 0;
        for (Map.Entry<Integer, Character> entry : colorMap.entrySet()) {
            int distance = colorDistance(pixelColor, entry.getKey());
            if (distance < minDistance) {
                minDistance = distance;
                closestColor = entry.getKey();
            }
        }
        return closestColor;
    }

    private int colorDistance(int color1, int color2) {
        int rDiff = Color.red(color1) - Color.red(color2);
        int gDiff = Color.green(color1) - Color.green(color2);
        int bDiff = Color.blue(color1) - Color.blue(color2);
        return rDiff * rDiff + gDiff * gDiff + bDiff * bDiff;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}