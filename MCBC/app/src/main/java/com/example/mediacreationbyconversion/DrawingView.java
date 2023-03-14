package com.example.mediacreationbyconversion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DrawingView extends View {
    public Paint paint;
    public Bitmap previousBitmap;
    public Bitmap bitmap;
    public Canvas canvas;

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

    public int canvasColor = yellowLight;

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

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(canvasColor);
//        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(yellowLight);
        previousBitmap = Bitmap.createBitmap(bitmap);
        previousBitmap.eraseColor(Color.TRANSPARENT);
        previousBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawBitmap(previousBitmap, 0, 0, paint);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    public void save(){
        Bitmap b = previousBitmap;
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ChromaticTypewriter");
        if(!dir.exists()) dir.mkdirs();
        File file = new File(dir, "asdf.jpeg");
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
            Toast.makeText(getContext(), "Image saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}