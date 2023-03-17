package com.example.mediacreationbyconversion;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DrawingViewState implements Parcelable {
    public Paint paint;
    public Bitmap bitmap;
    public Stack<Bitmap> history;
    public int brushSize;
    public int canvasX;
    public int canvasY;
    public int canvasColor;

    public DrawingViewState() {
    }

    protected DrawingViewState(Parcel in) {
        brushSize = in.readInt();
        canvasX = in.readInt();
        canvasY = in.readInt();
        canvasColor = in.readInt();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        paint = (Paint) in.readSerializable();
        List<Bitmap> historyList = new ArrayList<>();
        in.readList(historyList, Bitmap.class.getClassLoader());
        history = new Stack<>();
        history.addAll(historyList);
    }

    public static final Creator<DrawingViewState> CREATOR = new Creator<DrawingViewState>() {
        @Override
        public DrawingViewState createFromParcel(Parcel in) {
            return new DrawingViewState(in);
        }

        @Override
        public DrawingViewState[] newArray(int size) {
            return new DrawingViewState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(brushSize);
        dest.writeInt(canvasX);
        dest.writeInt(canvasY);
        dest.writeInt(canvasColor);
        dest.writeParcelable(bitmap, flags);
        dest.writeSerializable((Serializable) paint);
        dest.writeList(new ArrayList<>(history));
    }
}