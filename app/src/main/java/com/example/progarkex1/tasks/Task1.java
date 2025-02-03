package com.example.progarkex1.tasks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.progarkex1.GameLoop;
import com.example.progarkex1.classes.Helicopter;

public class Task1 extends SurfaceView implements SurfaceHolder.Callback {
    private static Task1 instance;
    public SurfaceHolder surHolder;
    public Helicopter helicopter;

    public Task1(Context context) {
        super(context);
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Just one state!!");
        }

        surHolder = getHolder();
        surHolder.addCallback(this);
        helicopter = new Helicopter();
    }

    public static synchronized Task1 getInstance() {
        return instance;
    }

    public void render() {
        Canvas can = surHolder.lockCanvas();
        if (can != null) {
            can.drawColor(Color.BLACK);
            can.drawBitmap(helicopter.getSprite(), helicopter.getX(), helicopter.getY(), null);
            surHolder.unlockCanvasAndPost(can);
        }
    }

    public void update() {
        helicopter.move();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        GameLoop.getInstance().startGameLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
    }
}
