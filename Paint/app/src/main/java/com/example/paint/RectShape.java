package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectShape extends Shape {

    private int xEnd;
    private int yEnd;

    public RectShape(int x, int y, String color) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        canvas.drawRect(x,y,xEnd,yEnd,paint);
    }
}
