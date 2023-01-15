package com.example.paint;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class PaintView extends View {

    TextView hello;
    private Paint paint;
    private Paint bgPaint;
    private String currentShape = "Rect";
    private String currentColor = "#000000";
    private int currentWidth = 12;
    private String currentStyle = "STROKE";
    private Stack<Shape> shapes;
    private double maxarea=0;
    private Shape max;

    public PaintView(Context context) {
        super(context);
        shapes = new Stack<>();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        bgPaint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(12);
        bgPaint.setColor(Color.rgb(255,255,255));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(bgPaint);
        for (int i = 0; i < shapes.size(); i++){
            shapes.get(i).draw(canvas, paint);
        }
    }

    Shape shape;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(currentShape.equals("Rect"))
            {
                shape = new RectShape((int)event.getX(),(int)event.getY(),currentColor, currentStyle,currentWidth);
            }
            if(currentShape.equals("Line"))
            {
                shape = new LineShape((int)event.getX(),(int)event.getY(),currentColor, "STROKE",currentWidth);
            }
            if(currentShape.equals("Circle"))
            {
                shape = new CircleShape((int)event.getX(),(int)event.getY(),currentColor,currentStyle,currentWidth);
            }
            if(currentShape.equals("Path"))
            {
                shape = new PathShape((int)event.getX(),(int)event.getY(),currentColor, "STROKE",currentWidth);
            }
            shapes.push(shape);
            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            shape.updatePoint((int)event.getX(),(int)event.getY());
            invalidate();
        }
        return true;
    }

    public void addLine() {
        currentShape = "Line";
    }

    public void addRect() {
        currentShape = "Rect";
    }

    public void addCircle() {
        currentShape = "Circle";
    }
    public void addPath() {
        currentShape = "Path";
    }

    public void setColor(String color)
    {
        currentColor = color;
    }

    public void changestyle(){
        if(currentStyle=="STROKE") {
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            currentStyle="FILL";
        }
        else{
            paint.setStyle(Paint.Style.STROKE);
            currentStyle="STROKE";
        }
    }
    public String GetStyle(){
        return currentStyle;
    }

    public String GetWidth(){
        if(currentWidth==12)return "THIN";
        else return "thick";
    }

    public void changeWidth(){
        if(currentWidth==12) {
            paint.setStrokeWidth(60);
            currentWidth=60;
        }
        else{
            paint.setStrokeWidth(12);
            currentWidth=12;
        }
    }


    public void undo()
    {
        shapes.pop();
        invalidate();
    }
    public void smallisweak()
    {
        for (int i = 0; i < shapes.size(); i++){
            if(shapes.get(i) instanceof areaShape) {
                areaShape shape= (areaShape) shapes.get(i);
                if(shape.getArea()>maxarea){
                    maxarea=shape.getArea();
                    max = shape;
                }
            }
        }
        for (int i = shapes.size(); i > 0; i--){
            shapes.pop();
            invalidate();
        }
        if(max!=null)
            shapes.push(max);
    }
}