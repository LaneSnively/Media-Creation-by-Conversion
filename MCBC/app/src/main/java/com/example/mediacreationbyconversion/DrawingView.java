package com.example.mediacreationbyconversion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawingView extends View {
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

    public Map<Integer, Character> keyMap = new HashMap<Integer, Character>() {{
        put(KeyEvent.KEYCODE_0, '0');
        put(KeyEvent.KEYCODE_1, '1');
        put(KeyEvent.KEYCODE_2, '2');
        put(KeyEvent.KEYCODE_3, '3');
        put(KeyEvent.KEYCODE_4, '4');
        put(KeyEvent.KEYCODE_5, '5');
        put(KeyEvent.KEYCODE_6, '6');
        put(KeyEvent.KEYCODE_7, '7');
        put(KeyEvent.KEYCODE_8, '8');
        put(KeyEvent.KEYCODE_9, '9');

        put(KeyEvent.KEYCODE_A, 'a');
        put(KeyEvent.KEYCODE_B, 'b');
        put(KeyEvent.KEYCODE_C, 'c');
        put(KeyEvent.KEYCODE_D, 'd');
        put(KeyEvent.KEYCODE_E, 'e');
        put(KeyEvent.KEYCODE_F, 'f');
        put(KeyEvent.KEYCODE_G, 'g');
        put(KeyEvent.KEYCODE_H, 'h');
        put(KeyEvent.KEYCODE_I, 'i');
        put(KeyEvent.KEYCODE_J, 'j');
        put(KeyEvent.KEYCODE_K, 'k');
        put(KeyEvent.KEYCODE_L, 'l');
        put(KeyEvent.KEYCODE_M, 'm');
        put(KeyEvent.KEYCODE_N, 'n');
        put(KeyEvent.KEYCODE_O, 'o');
        put(KeyEvent.KEYCODE_P, 'p');
        put(KeyEvent.KEYCODE_Q, 'q');
        put(KeyEvent.KEYCODE_R, 'r');
        put(KeyEvent.KEYCODE_S, 's');
        put(KeyEvent.KEYCODE_T, 't');
        put(KeyEvent.KEYCODE_U, 'u');
        put(KeyEvent.KEYCODE_V, 'v');
        put(KeyEvent.KEYCODE_W, 'w');
        put(KeyEvent.KEYCODE_X, 'x');
        put(KeyEvent.KEYCODE_Y, 'y');
        put(KeyEvent.KEYCODE_Z, 'z');

        put(KeyEvent.KEYCODE_ENTER, '\n');
        put(KeyEvent.KEYCODE_SPACE, ' ');
    }};

    public Map<Character, Integer> colorMap = new HashMap<Character, Integer>(){{
        put('1', white);
        put('2', whiteDark);
        put('3', whiteDarker);
        put('4', grayLighter);
        put('5', grayLight);
        put('6', gray);
        put('7', grayDark);
        put('8', grayDarker);
        put('9', blackLight);
        put('0', black);

        put('a', pink);
        put('b', brown);
        put('c', blue);
        put('d', red);
        put('e', purpleDark);
        put('f', orange);
        put('g', brownLight);
        put('h', yellow);
        put('i', greenDark);
        put('j', teal);
        put('k', tealDark);
        put('l', blueDark);
        put('m', yellowDark);
        put('n', greenLight);
        put('o', tealDark);
        put('p', blueDark);
        put('q', grayDark);
        put('r', redDark);
        put('s', purple);
        put('t', orangeDark);
        put('u', yellowDark);
        put('v', greenLight);
        put('w', pinkDark);
        put('x', redLight);
        put('y', brownDark);
        put('z', purpleLight);
    }};

    public Paint paint;
    public Bitmap bitmap;
    public Canvas canvas;
    public List<Bitmap> history = new ArrayList<Bitmap>();
    public int brushSize = 30; //size of canvas paint brush
    public String brushShape = "square";
    public float canvasX = 0; //canvas brush horizontal location
    public float canvasY = 0; //canvas brush vertical location
    public float offsetX = 0;
    public float offsetY = 0;
    public int canvasColor = black;

    public DrawingView(Context context) {
        super(context);
        init();
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs,
                       int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(black);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(canvasColor);
        history.add(bitmap.copy(Bitmap.Config.ARGB_8888, true));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        offsetX = (float) (viewWidth - bitmapWidth) / 2;
        offsetY = (float) (viewHeight - bitmapHeight) / 2;
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }

    public void restoreDrawingView(Paint paint,
                                   Bitmap bitmap,
                                   Canvas canvas,
                                   List<Bitmap> history,
                                   int brushSize,
                                   String brushShape,
                                   float canvasX,
                                   float canvasY,
                                   float offsetX,
                                   float offsetY,
                                   int canvasColor) {
        this.bitmap = bitmap;
        this.paint = paint;
        this.canvas = canvas;
        this.history = history;
        this.brushSize = brushSize;
        this.brushShape = brushShape;
        this.canvasX = canvasX;
        this.canvasY = canvasY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.canvasColor = canvasColor;
    }

    public void backspace() {
        if (history.isEmpty()) {
            history.add(bitmap.copy(Bitmap.Config.ARGB_8888, true));
        } else {
            bitmap = history.remove(history.size() - 1);
            canvas = new Canvas(bitmap);
            //doesn't work when deleting enter history
            if (canvasX <= 0) {
                canvasX = canvas.getWidth() - brushSize; //brush hit right side, move back to left
                if (canvasY <= 0) {
                    canvasY = canvas.getHeight() - brushSize; //brush hit bottom, move back to top
                } else canvasY -= brushSize; //move brush down
            } else canvasX -= brushSize; //move brush right
        }
    }

    public void addHistory() {
        history.add(bitmap.copy(Bitmap.Config.ARGB_8888, true));
        int historySize = 400;
        if (history.size() > historySize)
            history.remove((int) (Math.random() * history.size()));
    }

    public void save(Bitmap b) {
        File dir = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "ChromaticTypewriter");
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, "ChromaticTypewriter.jpeg");
        boolean newFile = false;
        long num = 1;
        while (!newFile) {
            if (file.exists()) {
                file = new File(dir, "ChromaticTypewriter" + num + ".jpeg");
                num++;
            } else newFile = true;
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            MediaScannerConnection.scanFile(getContext(), new String[]{file.toString()},
                    null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int saveEvery() {
        int count = 0;
        int hs = history.size();
        int interval = (hs < 50) ? 1 : (int) hs / 50;
        File dir = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "ChromaticTypewriter");
        boolean newDir = false;
        long num = 1;
        while (!newDir) {
            if (dir.exists()) {
                dir = new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                        "ChromaticTypewriter" + num);
                num++;
            } else {
                newDir = true;
                dir.mkdirs();
            }
        }
        File file = new File(dir, "ChromaticTypewriter.jpeg");
        for (int i = 0; i < (Math.min(hs, 50)); i++) {
            Bitmap b = history.get(i * interval);
            boolean newFile = false;
            num = 1;
            while (!newFile) {
                if (file.exists()) {
                    file = new File(dir, "ChromaticTypewriter" + num + ".jpeg");
                    num++;
                } else newFile = true;
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
                count++;
                MediaScannerConnection.scanFile(getContext(), new String[]{file.toString()},
                        null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public void drawSqure() {
        canvas.drawRect(canvasX - offsetX, canvasY - offsetY, canvasX - offsetX + brushSize, canvasY - offsetY + brushSize, paint);
    }

    public void drawCircle() {
        canvas.drawCircle(canvasX - offsetX, canvasY - offsetY, brushSize / 2, paint);
    }

    public void drawText(String s) {
        paint.setTextSize((float) brushSize);
        canvas.drawText(s, canvasX - offsetX, canvasY - offsetY, paint);
    }

    public void convertText(String s, boolean addHistory) {
        int lineLength = 0;
        Character c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);

            if(colorMap.containsKey(c)) paint.setColor(colorMap.get(c));

            if(c.equals('\n')){
                if (addHistory) {
                    if (canvasY < canvas.getHeight() - brushSize) {
                        canvasY += brushSize; //move brush down
                    } else canvasY = 0; //brush hit bottom, move back to top
                } else canvasY += brushSize; //move brush down
                if (!addHistory) {
                    for (int k = 0; k < lineLength; k++)
                        canvasX -= brushSize;
                    if (canvasX < 0) canvasX = canvas.getWidth() - brushSize;
                }
            }

            if(c.equals(' ')){
                if (addHistory) {
                    if (canvasX >= canvas.getWidth() - brushSize) {
                        canvasX = 0; //brush hit right side, move back to left
                        if (canvasY >= canvas.getHeight() - brushSize) {
                            canvasY = 0; //brush hit bottom, move back to top
                        } else canvasY += brushSize; //move brush down
                    } else canvasX += brushSize; //move brush right
                } else canvasX += brushSize; //move brush right
            }

            if (!c.equals('\n')) lineLength++;
            else lineLength = 0;

            if (!c.equals(' ') && !c.equals('\n')) {
                switch (brushShape) {
                    case "square":
                        drawSqure();
                        break;
                    case "circle":
                        drawCircle();
                        break;
                    case "character":
                        drawText(c.toString());
                        break;
                    default:
                        break;
                }

                //rectangle brush moving logic
                if (addHistory) {
                    if (canvasX >= canvas.getWidth() - brushSize) {
                        canvasX = 0; //brush hit right side, move back to left
                        if (canvasY >= canvas.getHeight() - brushSize) {
                            canvasY = 0; //brush hit bottom, move back to top
                        } else canvasY += brushSize; //move brush down
                    } else canvasX += brushSize; //move brush right
                } else canvasX += brushSize; //move brush right
            }
            if (addHistory) addHistory();
        }
        if (!addHistory) addHistory();
    }
}