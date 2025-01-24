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
    public SurfaceHolder surHolder;
    public GameLoop gameLoop;
    public Helicopter helicopter;

    public Task1(Context context) {
        super(context);
        surHolder = getHolder();
        surHolder.addCallback(this);
        helicopter = new Helicopter();
        gameLoop = new GameLoop(this);
    }

    public void render() {
        Canvas can = surHolder.lockCanvas();
        if (can != null) {
            can.drawColor(Color.BLACK);
            can.drawBitmap(helicopter.getSpriteSimple(), helicopter.getX(), helicopter.getY(), null);
            surHolder.unlockCanvasAndPost(can);
        }
    }

    public void update(double delta) {
        helicopter.move(delta);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startGameLoop();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
    }
}
