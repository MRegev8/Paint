package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class PaintActivity extends AppCompatActivity {
    private static final String TAG = "PaintActivity";
    private FrameLayout frame;
    private PaintView paintView;
    private int r,g,b,pixels;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        frame = findViewById(R.id.frm);
        paintView = new PaintView(this);
        frame.addView(paintView);
        ImageView imageView=findViewById(R.id.colorpicker);
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN||event.getAction() == MotionEvent.ACTION_MOVE){
                    bitmap=imageView.getDrawingCache();
                    pixels=bitmap.getPixel((int)event.getX(),(int)event.getY());
                    r= Color.red(pixels);
                    b= Color.blue(pixels);
                    g= Color.green(pixels);
                    String hex="#"+Integer.toHexString(pixels);
                    paintView.setColor(hex);
                }

                return true;
            }
        });
    }

    public void addLine(View view) {
        paintView.addLine();
    }
    public void addRect(View view) {
        paintView.addRect();
    }
    public void addPath(View view) {
        paintView.addPath();
    }
    public void addCircle(View view) {
        paintView.addCircle();
    }

    public void changeColor(View view)
    {
        String color = view.getTag().toString();
        paintView.setColor(color);
    }

    public void clear(View view) {
        paintView.undo();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.style) {
            paintView.changestyle();
            Toast.makeText(this, paintView.GetStyle(), Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.BIG) {
            paintView.smallisweak();
            Toast.makeText(this, "YOU HAVE SUCCESSFULLY ELIMINATED THE MIDDLE CLASS", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.thicc) {
            paintView.changeWidth();
            Toast.makeText(this, "YOU ARE NOW " +paintView.GetWidth(), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}