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
import java.util.Locale;
import java.util.Map;

public class DrawingView extends View {
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

    int qLight = Color.parseColor("#F08080"); // Light Coral
    int qDark = Color.parseColor("#8B0000"); // Dark Red
    int wLight = Color.parseColor("#87CEFA"); // Light Sky Blue
    int wDark = Color.parseColor("#00008B"); // Dark Blue
    int eLight = Color.parseColor("#98FB98"); // Pale Green
    int eDark = Color.parseColor("#006400"); // Dark Green
    int rLight = Color.parseColor("#FFB6C1"); // Light Pink
    int rDark = Color.parseColor("#8B008B"); // Dark Magenta
    int tLight = Color.parseColor("#AFEEEE"); // Pale Turquoise
    int tDark = Color.parseColor("#008B8B"); // Dark Cyan
    int yLight = Color.parseColor("#FFFACD"); // Lemon Chiffon
    int yDark = Color.parseColor("#6B8E23"); // Olive Drab
    int uLight = Color.parseColor("#E6E6FA"); // Lavender
    int uDark = Color.parseColor("#4B0082"); // Indigo
    int iLight = Color.parseColor("#DB7093"); // Pale Violet Red
    int iDark = Color.parseColor("#B22222"); // Firebrick
    int oLight = Color.parseColor("#EEE8AA"); // Pale Goldenrod
    int oDark = Color.parseColor("#B8860B"); // Dark Goldenrod
    int pLight = Color.parseColor("#D8BFD8"); // Thistle
    int pDark = Color.parseColor("#BA55D3"); // Medium Orchid

    int aLight = Color.parseColor("#FFA07A"); // Light Salmon
    int aDark = Color.parseColor("#E9967A"); // Dark Salmon
    int sLight = Color.parseColor("#ADD8E6"); // Light Blue
    int sDark = Color.parseColor("#191970"); // Midnight Blue
    int dLight = Color.parseColor("#98FB98"); // Pale Green
    int dDark = Color.parseColor("#006400"); // Dark Green
    int fLight = Color.parseColor("#F5DEB3"); // Wheat
    int fDark = Color.parseColor("#A0522D"); // Sienna
    int gLight = Color.parseColor("#AFEEEE"); // Pale Turquoise
    int gDark = Color.parseColor("#008B8B"); // Dark Cyan
    int hLight = Color.parseColor("#DB7093"); // Pale Violet Red
    int hDark = Color.parseColor("#B22222"); // Firebrick
    int jLight = Color.parseColor("#E6E6FA"); // Lavender
    int jDark = Color.parseColor("#4B0082"); // Indigo
    int kLight = Color.parseColor("#FFFACD"); // Lemon Chiffon
    int kDark = Color.parseColor("#6B8E23"); // Olive Drab
    int lLight = Color.parseColor("#D8BFD8"); // Thistle
    int lDark = Color.parseColor("#BA55D3"); // Medium Orchid

    int zLight = Color.parseColor("#EEE8AA"); // Pale Goldenrod
    int zDark = Color.parseColor("#B8860B"); // Dark Goldenrod
    int xLight = Color.parseColor("#FFB6C1"); // Light Pink
    int xDark = Color.parseColor("#8B008B"); // Dark Magenta
    int cLight = Color.parseColor("#ADD8E6"); // Light Blue
    int cDark = Color.parseColor("#191970"); // Midnight Blue
    int vLight = Color.parseColor("#F08080"); // Light Coral
    int vDark = Color.parseColor("#8B0000"); // Dark Red
    int bLight = Color.parseColor("#F5DEB3"); // Wheat
    int bDark = Color.parseColor("#A0522D"); // Sienna
    int nLight = Color.parseColor("#98FB98"); // Pale Green
    int nDark = Color.parseColor("#006400"); // Dark Green
    int mLight = Color.parseColor("#E6E6FA"); // Lavender
    int mDark = Color.parseColor("#4B0082"); // Indigo

    int comma = Color.parseColor("#FFFACD"); // Lemon Chiffon
    int period = Color.parseColor("#DB7093"); // Pale Violet Red

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
        put(KeyEvent.KEYCODE_PERIOD, '.');
        put(KeyEvent.KEYCODE_COMMA, ',');
    }};

    public Map<Character, Integer> colorMap = new HashMap<Character, Integer>(){{
        put('0', color0);
        put('1', color1);
        put('2', color2);
        put('3', color3);
        put('4', color4);
        put('5', color5);
        put('6', color6);
        put('7', color7);
        put('8', color8);
        put('9', color9);

        put('a', aLight);
        put('b', bLight);
        put('c', cLight);
        put('d', dLight);
        put('e', eLight);
        put('f', fLight);
        put('g', gLight);
        put('h', hLight);
        put('i', iLight);
        put('j', jLight);
        put('k', kLight);
        put('l', lLight);
        put('m', mLight);
        put('n', nLight);
        put('o', oLight);
        put('p', pLight);
        put('q', qLight);
        put('r', rLight);
        put('s', sLight);
        put('t', tLight);
        put('u', uLight);
        put('v', vLight);
        put('w', wLight);
        put('x', xLight);
        put('y', yLight);
        put('z', zLight);

        put('A', aDark);
        put('B', bDark);
        put('C', cDark);
        put('D', dDark);
        put('E', eDark);
        put('F', fDark);
        put('G', gDark);
        put('H', hDark);
        put('I', iDark);
        put('J', jDark);
        put('K', kDark);
        put('L', lDark);
        put('M', mDark);
        put('N', nDark);
        put('O', oDark);
        put('P', pDark);
        put('Q', qDark);
        put('R', rDark);
        put('S', sDark);
        put('T', tDark);
        put('U', uDark);
        put('V', vDark);
        put('W', wDark);
        put('X', xDark);
        put('Y', yDark);
        put('Z', zDark);

        put(',', comma);
        put('.', period);
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
    public int canvasColor = color0;
    public boolean shift = false;

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
        paint.setColor(color0);
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

    public void clearDrawingView() {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(paint.getColor());
        history.clear();
        history.add(bitmap.copy(Bitmap.Config.ARGB_8888, true));
        canvasX = 0;
        canvasY = 0;
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        offsetX = (float) (viewWidth - bitmapWidth) / 2;
        offsetY = (float) (viewHeight - bitmapHeight) / 2;
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
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
        int historySize = 333;
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
        canvas.drawCircle(canvasX - offsetX, canvasY - offsetY, (float) brushSize / 2, paint);
    }

    public void drawText(String s) {
        paint.setTextSize((float) brushSize);
        canvas.drawText(s, canvasX - offsetX, canvasY - offsetY, paint);
    }

    public int convertText(String s, boolean addHistory) {
        int lineLength = 0;
        Character c;
        boolean isPixelatedImage = s.length() > 5000;
        int imageBrushSize = brushSize;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);

            if(colorMap.containsKey(c)) {
                if(isPixelatedImage) {
                    brushSize = 1;
                    int w;
                    for(w = 0; w < imageBrushSize; w++){
                        if(i + w < s.length()){
                            c = s.charAt(i + w);
                            if(c.equals('\n')) break;
                        } else {
                            return 1;
                        }
                    }
                    if (w > 0) i+= w-1;
                    if(colorMap.containsKey(c)){
                        if(shift) paint.setColor(colorMap.get(c.toString().toUpperCase().toCharArray()[0]));
                        else paint.setColor(colorMap.get(c));
                    }
                } else{
                    if(shift) paint.setColor(colorMap.get(c.toString().toUpperCase().toCharArray()[0]));
                    else paint.setColor(colorMap.get(c));
                }
            }

            if(c.equals('\n')){
                if(isPixelatedImage){
                    int skippedLines = 0;
                    for(int skipOffset = 0; skipOffset + i < s.length(); skipOffset++){
                        c = s.charAt(skipOffset + i);
                        if(c.equals('\n')) skippedLines++;
                        if(skippedLines >= imageBrushSize) {
                            i += skipOffset;
                            break;
                        }
                    }
                }
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
        brushSize = imageBrushSize;
        return 1;
    }
}