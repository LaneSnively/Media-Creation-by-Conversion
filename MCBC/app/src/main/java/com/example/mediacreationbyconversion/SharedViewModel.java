package com.example.mediacreationbyconversion;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Stack;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> text = new MutableLiveData<>();
    public void setText(String data) { this.text.setValue(data); }
    public LiveData<String> getText() { return text; }

    private MutableLiveData<Paint> paint = new MutableLiveData<>();
    public void setPaint(Paint data) { this.paint.setValue(data); }
    public LiveData<Paint> getPaint() { return paint; }

    private MutableLiveData<Bitmap> bitmap = new MutableLiveData<>();
    public void setBitmap(Bitmap data) { this.bitmap.setValue(data); }
    public LiveData<Bitmap> getBitmap() { return bitmap; }

    private MutableLiveData<Canvas> canvas = new MutableLiveData<>();
    public void setCanvas(Canvas data) { this.canvas.setValue(data); }
    public LiveData<Canvas> getCanvas() { return canvas; }

    private MutableLiveData<Stack<Bitmap>> history = new MutableLiveData<>();
    public void setHistory(Stack<Bitmap> data) { this.history.setValue(data); }
    public LiveData<Stack<Bitmap>> getHistory() { return history; }

    private MutableLiveData<Integer> brushSize = new MutableLiveData<>();
    public void setBrushSize(Integer data) { this.brushSize.setValue(data); }
    public LiveData<Integer> getBrushSize() { return brushSize; }

    private MutableLiveData<Integer> canvasX = new MutableLiveData<>();
    public void setCanvasX(Integer data) { this.canvasX.setValue(data); }
    public LiveData<Integer> getCanvasX() { return canvasX; }

    private MutableLiveData<Integer> canvasY = new MutableLiveData<>();
    public void setCanvasY(Integer data) { this.canvasY.setValue(data); }
    public LiveData<Integer> getCanvasY() { return canvasY; }

    private MutableLiveData<Integer> canvasColor = new MutableLiveData<>();
    public void setCanvasColor(Integer data) { this.canvasColor.setValue(data); }
    public LiveData<Integer> getCanvasColor() { return canvasColor; }
}
