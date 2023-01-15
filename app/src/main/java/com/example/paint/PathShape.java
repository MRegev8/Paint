package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import javax.xml.xpath.XPath;

public class PathShape extends Shape {

    private int xEnd;
    private int yEnd;
    private Path pathinio;

    public PathShape(int x, int y, String color, String style,int width) {
        super(x, y, color, style, width);
        pathinio=new Path();
        xEnd = x;
        yEnd = y;
        pathinio.moveTo(xEnd,yEnd);
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
        pathinio.lineTo(xe,ye);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        canvas.drawPath(pathinio,paint);
    }
}
