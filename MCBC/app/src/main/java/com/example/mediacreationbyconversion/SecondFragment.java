package com.example.mediacreationbyconversion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SecondFragment extends Fragment {
    int color0 = Color.parseColor("#000000"); // Perfect Black
    int color1 = Color.parseColor("#FFFFFF"); // Perfect White
    int color2 = Color.parseColor("#E6E6E6"); // Light Gray
    int color3 = Color.parseColor("#CCCCCC"); // Medium Light Gray
    int color4 = Color.parseColor("#B3B3B3"); // Medium Gray
    int color5 = Color.parseColor("#999999"); // Medium Dark Gray
    int color6 = Color.parseColor("#808080"); // Dark Gray
    int color7 = Color.parseColor("#666666"); // Charcoal
    int color8 = Color.parseColor("#4D4D4D"); // Very Dark Gray
    int color9 = Color.parseColor("#333333"); // Super Dark Gray

    int qLight = Color.parseColor("#FF9999"); // Light Red (lowercase)
    int qDark = Color.parseColor("#CC0000"); // Dark Red (uppercase)
    int wLight = Color.parseColor("#FFCC99"); // Light Orange (lowercase)
    int wDark = Color.parseColor("#FF6600"); // Dark Orange (uppercase)
    int eLight = Color.parseColor("#FFFF99"); // Light Yellow (lowercase)
    int eDark = Color.parseColor("#FFCC00"); // Dark Yellow (uppercase)
    int rLight = Color.parseColor("#CCFF99"); // Light Green (lowercase)
    int rDark = Color.parseColor("#009900"); // Dark Green (uppercase)
    int tLight = Color.parseColor("#99CCFF"); // Light Blue (lowercase)
    int tDark = Color.parseColor("#0066CC"); // Dark Blue (uppercase)
    int yLight = Color.parseColor("#9999FF"); // Light Navy (lowercase)
    int yDark = Color.parseColor("#3333CC"); // Dark Navy (uppercase)
    int uLight = Color.parseColor("#CC99FF"); // Light Purple (lowercase)
    int uDark = Color.parseColor("#9900CC"); // Dark Purple (uppercase)
    int iLight = Color.parseColor("#FF99FF"); // Light Violet (lowercase)
    int iDark = Color.parseColor("#CC33CC"); // Dark Violet (uppercase)
    int oLight = Color.parseColor("#FFCCCC"); // Lighter Red (lowercase)
    int oDark = Color.parseColor("#CC3333"); // Darker Red (uppercase)
    int pLight = Color.parseColor("#FFE0CC"); // Lighter Orange (lowercase)
    int pDark = Color.parseColor("#FF9966"); // Darker Orange (uppercase)

    int aLight = Color.parseColor("#FFFFCC"); // Lighter Yellow (lowercase)
    int aDark = Color.parseColor("#FFDB4D"); // Darker Yellow (uppercase)
    int sLight = Color.parseColor("#E0FFCC"); // Lighter Green (lowercase)
    int sDark = Color.parseColor("#33CC52"); // Darker Green (uppercase)
    int dLight = Color.parseColor("#CCE0FF"); // Lighter Blue (lowercase)
    int dDark = Color.parseColor("#3399DB"); // Darker Blue (uppercase)
    int fLight = Color.parseColor("#CCCCFF"); // Lighter Navy (lowercase)
    int fDark = Color.parseColor("#6666CC"); // Darker Navy (uppercase)
    int gLight = Color.parseColor("#E0CCFF"); // Lighter Purple (lowercase)
    int gDark = Color.parseColor("#9966CC"); // Darker Purple (uppercase)
    int hLight = Color.parseColor("#FFCCFF"); // Lighter Violet (lowercase)
    int hDark = Color.parseColor("#CC66CC"); // Darker Violet (uppercase)
    int jLight = Color.parseColor("#FFCCCC"); // Lightest Red (lowercase)
    int jDark = Color.parseColor("#CC6666"); // Darkest Red (uppercase)
    int kLight = Color.parseColor("#FFE8D6"); // Lightest Orange (lowercase)
    int kDark = Color.parseColor("#FFA366"); // Darkest Orange (uppercase)
    int lLight = Color.parseColor("#FFFFE0"); // Lightest Yellow (lowercase)
    int lDark = Color.parseColor("#FFE699"); // Darkest Yellow (uppercase)

    int zLight = Color.parseColor("#D6FFD6"); // Lightest Green (lowercase)
    int zDark = Color.parseColor("#66CC7A"); // Darkest Green (uppercase)
    int xLight = Color.parseColor("#D6E0FF"); // Lightest Blue (lowercase)
    int xDark = Color.parseColor("#6699E6"); // Darkest Blue (uppercase)
    int cLight = Color.parseColor("#E0D6FF"); // Lightest Purple (lowercase)
    int cDark = Color.parseColor("#9966CC"); // Darkest Purple (uppercase)
    int vLight = Color.parseColor("#FFD6FF"); // Lightest Violet (lowercase)
    int vDark = Color.parseColor("#CC99CC"); // Darkest Violet (uppercase)
    int bLight = Color.parseColor("#FFA3A3"); // Medium Red (lowercase)
    int bDark = Color.parseColor("#CC3333"); // Medium Red (uppercase)
    int nLight = Color.parseColor("#FFCCA3"); // Medium Orange (lowercase)
    int nDark = Color.parseColor("#FF9933"); // Medium Orange (uppercase)
    int mLight = Color.parseColor("#FFFFA3"); // Medium Yellow (lowercase)
    int mDark = Color.parseColor("#FFD433"); // Medium Yellow (uppercase)

    int comma = Color.parseColor("#FFEE82"); // Soft Yellow (comma)
    int period = Color.parseColor("#D36292"); // Soft Pink (period)

    public Map<Integer, Character> reversedColorMap = new HashMap<Integer, Character>() {{
        put(color0, '0');
        put(color1, '1');
        put(color2, '2');
        put(color3, '3');
        put(color4, '4');
        put(color5, '5');
        put(color6, '6');
        put(color7, '7');
        put(color8, '8');
        put(color9, '9');

        put(aLight, 'a');
        put(bLight, 'b');
        put(cLight, 'c');
        put(dLight, 'd');
        put(eLight, 'e');
        put(fLight, 'f');
        put(gLight, 'g');
        put(hLight, 'h');
        put(iLight, 'i');
        put(jLight, 'j');
        put(kLight, 'k');
        put(lLight, 'l');
        put(mLight, 'm');
        put(nLight, 'n');
        put(oLight, 'o');
        put(pLight, 'p');
        put(qLight, 'q');
        put(rLight, 'r');
        put(sLight, 's');
        put(tLight, 't');
        put(uLight, 'u');
        put(vLight, 'v');
        put(wLight, 'w');
        put(xLight, 'x');
        put(yLight, 'y');
        put(zLight, 'z');

        put(aDark, 'A');
        put(bDark, 'B');
        put(cDark, 'C');
        put(dDark, 'D');
        put(eDark, 'E');
        put(fDark, 'F');
        put(gDark, 'G');
        put(hDark, 'H');
        put(iDark, 'I');
        put(jDark, 'J');
        put(kDark, 'K');
        put(lDark, 'L');
        put(mDark, 'M');
        put(nDark, 'N');
        put(oDark, 'O');
        put(pDark, 'P');
        put(qDark, 'Q');
        put(rDark, 'R');
        put(sDark, 'S');
        put(tDark, 'T');
        put(uDark, 'U');
        put(vDark, 'V');
        put(wDark, 'W');
        put(xDark, 'X');
        put(yDark, 'Y');
        put(zDark, 'Z');

        put(comma, ',');
        put(period, '.');
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

        binding.tokeyboardinput.setOnClickListener(v -> {
            if (binding.inputtext.getText() != null)
                text = Objects.requireNonNull(binding.inputtext.getText()).toString();
            if (!text.equals(""))
                storeText(text);
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

        binding.convertToText.setOnClickListener(v -> {
            Toast.makeText(getContext(), "began converting canvas pixels to text brush", Toast.LENGTH_LONG).show();
            if(bitmap != null) text = convertBitmapToString(bitmap);
            binding.inputtext.setText(text);
        });

        binding.cleartext.setOnClickListener(v -> {
            text = "";
            storeText(text);
            binding.inputtext.setText(null);
        });

        binding.selectfile.setOnClickListener(v -> readFile());

        binding.savefile.setOnClickListener(v -> {
            save(text);
            Toast.makeText(getContext(), "saved brush", Toast.LENGTH_SHORT).show();
        });

        view.postDelayed(() -> {
            if(binding != null){
                if(bitmap != null) binding.canvas.setImageBitmap(bitmap);
                if(!text.equals("")) {
                    if(text.length() > 5000)
                        Toast.makeText(getContext(),
                                "loading large text brushes can take a while",
                                Toast.LENGTH_LONG).show();
                    binding.inputtext.setText(text);
                }
            }
        }, 10);
    }

    public void save(String t) {
        File dir = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
                "ChromaticTypewriter");
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, "TextBrush.txt");
        boolean newFile = false;
        long num = 1;
        while (!newFile) {
            if (file.exists()) {
                file = new File(dir, "TextBrush" + num + ".txt");
                num++;
            } else newFile = true;
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(t.getBytes(StandardCharsets.UTF_8));
            fos.close();
            MediaScannerConnection.scanFile(getContext(), new String[]{file.toString()},
                    null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final int READ_REQUEST_CODE = 42;

    public void readFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain"); // Only show text files in the file picker
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        Uri uri = null;
        if (resultCode == Activity.RESULT_OK && resultData != null && resultData.getData() != null) {
            try {
                Toast.makeText(getContext(),
                        "loading large text brushes can take a while",
                        Toast.LENGTH_LONG).show();
                uri = resultData.getData();
                binding.inputtext.setText(readTextFromUri(uri));
                text = binding.inputtext.getText().toString();
                storeText(text);
                binding.inputtext.invalidate();
            } catch (Exception e) {}
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

    public String convertBitmapToString(Bitmap b) {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < b.getHeight(); y++) {
            for (int x = 0; x < b.getWidth(); x++) {
                int pixelColor = b.getPixel(x, y);
                int closestColor = findClosestColor(pixelColor);
                char character = reversedColorMap.get(closestColor);
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
        List<Integer> colorsList = new ArrayList<>(reversedColorMap.keySet());
        boolean isPixelColorGray = isColorGray(pixelColor);
        for (int i = 0; i < colorsList.size(); i++) {
            int currentColor = colorsList.get(i);
            if (!isPixelColorGray && isColorGray(currentColor)) continue;
            int currentDistance = colorDistance(pixelColor, currentColor);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                closestColor = currentColor;
            }
        }
        return closestColor;
    }

    private boolean isColorGray(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        int grayThreshold = 40;
        return Math.abs(g - b) <= grayThreshold;
    }

    private int colorDistance(int color1, int color2) {
        int rDiff = Color.red(color1) - Color.red(color2);
        int gDiff = Color.green(color1) - Color.green(color2);
        int bDiff = Color.blue(color1) - Color.blue(color2);
        return (rDiff * rDiff) + (gDiff * gDiff) + (bDiff * bDiff);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}