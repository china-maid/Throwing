package com.mic.throwing.sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import com.mic.throwing.R;

public class Window extends Sprite {
    int   middleX;
    Point screenSize;
    
    public enum Position {
        left, center, right
    }
    
    public Window(Context context, Point size) {
        super(getWindowImage(context), size.x / 4, size.y / 4);
    }
    
    private static Bitmap getWindowImage(Context context) {
        Bitmap imageTemp = BitmapFactory.decodeResource(context.getResources(), R.drawable.window);
        Bitmap image = Bitmap.createScaledBitmap(imageTemp, 200, 170, true);
        return image;
    }
}
