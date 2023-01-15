package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Paint;

public class areaShape extends Shape {
    protected double area;
    public areaShape(int x, int y, String color, String style,int width) {
        super(x, y, color, style,width);
        this.area=0;
    }
    @Override
    public void updatePoint(int xe,int ye)
    {

    }

    public double getArea() {
        return this.area;
    }
}
