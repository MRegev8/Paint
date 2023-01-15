package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CircleShape extends areaShape {

    private int xEnd;
    private int yEnd;
    private int xe;
    private int ye;
    private int Radius;
    private int width;
    public CircleShape(int x, int y, String color, String style,int width) {
        super(x, y, color, style,width);
        xEnd = x;
        yEnd = y;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        Radius = (int) Math.sqrt(Math.pow((xEnd - xe),2) + Math.pow((yEnd-ye),2));
        this.xe=xe;
        this.ye=ye;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        //canvas.drawCircle((x+xe)/2,(y+ye)/2,Radius/2,paint);
        canvas.drawOval(x,y,xe,ye,paint);
    }
    @Override
    public double getArea()
    {
        return Math.abs((x-xe)/2*(y-ye)/2*Math.PI);

    }
}
