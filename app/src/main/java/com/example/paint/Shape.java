package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class Shape {
    protected int x;
    protected int y;
    protected String color;
    protected String style;
    protected int width;


    public Shape(int x, int y, String color, String style, int width) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.style = style;
        this.width=width;
    }

    public abstract void updatePoint(int xe,int ye);

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.parseColor(color));
        if(this.style=="STROKE") {
            paint.setStyle(Paint.Style.STROKE);
        }
        else paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(this.width);
    }
}