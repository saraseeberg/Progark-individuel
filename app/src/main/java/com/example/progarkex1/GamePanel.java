package com.example.progarkex1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private Paint greenPaint = new Paint();
    private SurfaceHolder sirHolder;
    //private float x, y;
    private ArrayList<PointF> squarePos = new ArrayList<>();

    public GamePanel(Context context) {
        super(context);
        sirHolder = getHolder();
        sirHolder.addCallback(this);
        greenPaint.setColor(Color.GREEN);
    }

    private void render(){
        Canvas can = sirHolder.lockCanvas();
        for(PointF pos : squarePos)
            can.drawRect(pos.x, pos.y, pos.x + 50, pos.y + 50, greenPaint);
        sirHolder.unlockCanvasAndPost(can);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            squarePos.add(new PointF(event.getX(), event.getY()));
            render();
        }
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        render();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
