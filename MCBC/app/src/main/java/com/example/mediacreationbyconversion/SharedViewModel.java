package com.example.mediacreationbyconversion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> text = new MutableLiveData<>();

    public void setText(String data) {
        this.text.setValue(data);
    }

    public LiveData<String> getText() {
        return text;
    }

    private MutableLiveData<DrawingView> drawing = new MutableLiveData<>();

    public void setDrawing(DrawingView drawing) {
        this.drawing.setValue(drawing);
    }

    public LiveData<DrawingView> getDrawing() {
        return drawing;
    }
}
