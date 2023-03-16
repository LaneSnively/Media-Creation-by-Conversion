package com.example.mediacreationbyconversion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DrawingViewModel extends ViewModel {
    private MutableLiveData<DrawingView> drawing = new MutableLiveData<>();

    public void setDrawing(DrawingView drawing) {
        this.drawing.setValue(drawing);
    }

    public LiveData<DrawingView> getDrawing() {
        return drawing;
    }
}
