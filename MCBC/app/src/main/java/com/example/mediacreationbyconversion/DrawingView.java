package com.example.mediacreationbyconversion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DrawingView extends View {
    int white = Color.parseColor("#FFFFFF");
    int black = Color.parseColor("#000000");

    int grayLight = Color.parseColor("#A9A9A9");
    int gray = Color.parseColor("#808080");
    int grayDark = Color.parseColor("#696969");

    int pinkLight = Color.parseColor("#FFC0CB");
    int pink = Color.parseColor("#FF69B4");
    int pinkDark = Color.parseColor("#FF1493");

    int purpleLight = Color.parseColor("#EE82EE");
    int purple = Color.parseColor("#9932CC");
    int purpleDark = Color.parseColor("#800080");

    int redLight = Color.parseColor("#FFA07A");
    int red = Color.parseColor("#FF0000");
    int redDark = Color.parseColor("#800000");

    int orangeLight = Color.parseColor("#FF7F50");
    int orange = Color.parseColor("#FFA500");
    int orangeDark = Color.parseColor("#FF4500");

    int brownLight = Color.parseColor("#FFD180");
    int brown = Color.parseColor("#FF9100");
    int brownDark = Color.parseColor("#DD2C00");

    int yellowLight = Color.parseColor("#FFFFCC");
    int yellow = Color.parseColor("#FFFF00");
    int yellowDark = Color.parseColor("#CCCC00");

    int greenLight = Color.parseColor("#98FB98");
    int green = Color.parseColor("#00FF00");
    int greenDark = Color.parseColor("#556B2F");

    int tealLight = Color.parseColor("#7FFFD4");
    int teal = Color.parseColor("#00CED1");
    int tealDark = Color.parseColor("#008080");

    int blueLight = Color.parseColor("#87CEFA");
    int blue = Color.parseColor("#0000FF");
    int blueDark = Color.parseColor("#00008B");

    public Map<Integer, Character> keymap = new HashMap<Integer, Character>() {{
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
        put(KeyEvent.KEYCODE_TAB, '\t');
        put(KeyEvent.KEYCODE_SPACE, ' ');
    }};

    //state of the drawing
    public Paint paint;
    public Bitmap bitmap;
    public Canvas canvas;
    public Stack<Bitmap> history = new Stack<>();
    public int brushSize=30; //size of canvas paint brush
    public int canvasX=0; //canvas brush horizontal location
    public int canvasY=0; //canvas brush vertical location
    public int canvasColor = yellowLight;

    public Paint getPaint(){return paint;}
    public void setPaint(Paint paint){this.paint = paint;}
    public Bitmap getBitmap(){return bitmap;}
    public void setBitmap(Bitmap bitmap){this.bitmap = bitmap;}
    public Canvas getCanvas(){return canvas;}
    public void setCanvas(Canvas canvas){this.canvas = canvas;}
    public Stack<Bitmap> getHistory(){return history;}
    public void setHistory(Stack<Bitmap> history){this.history = history;}
    public int getBrushSize(){return brushSize;}
    public void setBrushSize(int brushSize){this.brushSize = brushSize;}
    public int getCanvasX(){return canvasX;}
    public void setCanvasX(int canvasX){this.canvasX = canvasX;}
    public int getCanvasY(){return canvasY;}
    public void setCanvasY(int canvasY){this.canvasY = canvasY;}
    public int getCanvasColor(){return canvasColor;}
    public void setCanvasColor(int canvasColor){this.canvasColor = canvasColor;}

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

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(canvasColor);
        history.push(bitmap.copy(Bitmap.Config.ARGB_8888, true));
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    private void init(){
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(black);
//        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
    }

    public void backspace(){
        if(history.isEmpty()){
            history.push(bitmap.copy(Bitmap.Config.ARGB_8888, true));
        } else {
            bitmap = history.pop();
            canvas = new Canvas(bitmap);
        }
    }

    public void resetCanvas(){
        history.clear();
        history.push(bitmap.copy(Bitmap.Config.ARGB_8888, true));
    }

    public void addHistory(){
        history.push(bitmap.copy(Bitmap.Config.ARGB_8888, true));
    }

    public void save(){
        Bitmap b = bitmap;
        File dir = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "ChromaticTypewriter");
        if(!dir.exists()) dir.mkdirs();
        File file = new File(dir, "ChromaticTypewriter.jpeg");
        boolean newFile = false;
        long num = 1;
        while(!newFile){
            if(file.exists()){
                file = new File(dir, "ChromaticTypewriter" + num + ".jpeg");
                num++;
            }
            else newFile = true;
        }
        try{
            FileOutputStream fos = new FileOutputStream(file);
            b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            MediaScannerConnection.scanFile(getContext(), new String[] {file.toString()},
                    null, null);
            Toast.makeText(getContext(), "image saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertText(String s, boolean addHistory){
        Character c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case 'q':
                    paint.setColor(grayDark);
                    break;
                case 'w':
                    paint.setColor(pinkDark);
                    break;
                case 'e':
                    paint.setColor(purpleDark);
                    break;
                case 'r':
                    paint.setColor(redDark);
                    break;
                case 't':
                    paint.setColor(orangeDark);
                    break;
                case 'y':
                    paint.setColor(brownDark);
                    break;
                case 'u':
                    paint.setColor(yellowDark);
                    break;
                case 'i':
                    paint.setColor(greenDark);
                    break;
                case 'o':
                    paint.setColor(tealDark);
                    break;
                case 'p':
                    paint.setColor(blueDark);
                    break;
                case 'a':
                    paint.setColor(pink);
                    break;
                case 's':
                    paint.setColor(purple);
                    break;
                case 'd':
                    paint.setColor(red);
                    break;
                case 'f':
                    paint.setColor(orange);
                    break;
                case 'g':
                    paint.setColor(brown);
                    break;
                case 'h':
                    paint.setColor(yellow);
                    break;
                case 'j':
                    paint.setColor(green);
                    break;
                case 'k':
                    paint.setColor(teal);
                    break;
                case 'l':
                    paint.setColor(blue);
                    break;
                case 'z':
                    paint.setColor(purpleLight);
                    break;
                case 'x':
                    paint.setColor(redLight);
                    break;
                case 'c':
                    paint.setColor(orangeLight);
                    break;
                case 'v':
                    paint.setColor(brownLight);
                    break;
                case 'b':
                    paint.setColor(greenLight);
                    break;
                case 'n':
                    paint.setColor(tealLight);
                    break;
                case 'm':
                    paint.setColor(blueLight);
                    break;
                case '\n':
                    if(canvasY<canvas.getHeight()-brushSize){
                        canvasY += brushSize; //move brush down
                    } else {
                        canvasY = 0; //brush hit bottom, move back to top
                    }
                    break;
//                case '\t':
//                    break;
                case ' ':
                    if(canvasX>=canvas.getWidth()-brushSize){
                        canvasX = 0; //brush hit right side, move back to left
                        if(canvasY>=canvas.getHeight()-brushSize){
                            canvasY = 0; //brush hit bottom, move back to top
                        } else {
                            canvasY += brushSize; //move brush down
                        }
                    } else {
                        canvasX += brushSize; //move brush right
                    }
                    break;
                default:
                    break;
            }

            if(!c.equals(' ') && !c.equals('\n')){
                //drawing on the canvas at location (w,h)
                canvas.drawRect((float) canvasX,
                        (float) canvasY,
                        (float) canvasX + brushSize,
                        (float) canvasY + brushSize,
                        paint);

                //rectangle brush moving logic
                if (canvasX >= canvas.getWidth() - brushSize) {
                    canvasX = 0; //brush hit right side, move back to left
                    if (canvasY >= canvas.getHeight() - brushSize) {
                        canvasY = 0; //brush hit bottom, move back to top
                    } else {
                        canvasY += brushSize; //move brush down
                    }
                } else {
                    canvasX += brushSize; //move brush right
                }
            }
            if(addHistory) addHistory();
        }
    }

//    public Parcelable saveState() {
//        DrawingViewState state = new DrawingViewState();
//        state.brushSize = brushSize;
//        state.canvasX = canvasX;
//        state.canvasY = canvasY;
//        state.canvasColor = canvasColor;
//        state.bitmap = bitmap;
//        state.paint = paint;
//        state.history = history;
//        return state;
//    }
//
//    public void restoreState(Parcelable state) {
//        if (state instanceof DrawingViewState) {
//            DrawingViewState drawingViewState = (DrawingViewState) state;
//            brushSize = drawingViewState.brushSize;
//            canvasX = drawingViewState.canvasX;
//            canvasY = drawingViewState.canvasY;
//            canvasColor = drawingViewState.canvasColor;
//            bitmap = drawingViewState.bitmap;
//            paint = drawingViewState.paint;
//            history = drawingViewState.history;
//            canvas = new Canvas(bitmap);
//            invalidate();
//        }
//    }
}