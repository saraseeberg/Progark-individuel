package com.example.progarkex1.tasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.progarkex1.GameLoop;
import com.example.progarkex1.classes.Helicopter;

import java.util.Locale;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private static GamePanel instance;
    public SurfaceHolder surHolder;
    public Helicopter helicopter;
    private final Paint textPaint;
    private int screenWidth;
    private int screenHeight;
    private float targetX;
    private float targetY;

    public GamePanel(Context context) {
        super(context);
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Just one state!!");
        }

        surHolder = getHolder();
        surHolder.addCallback(this);
        helicopter = new Helicopter();

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);

        targetX = helicopter.getX();
        targetY = helicopter.getY();
    }

    public static synchronized GamePanel getInstance() {
        return instance;
    }

    public void render() {
        Canvas can = surHolder.lockCanvas();
        if (can != null) {
            can.drawColor(Color.BLACK);
            can.drawBitmap(helicopter.getSprite(), helicopter.getX(), helicopter.getY(), null);
            String positionText = String.format(Locale.forLanguageTag("nb-NO"),
                    "X: %.1f, Y: %.1f", helicopter.getX(), helicopter.getY());
            can.drawText(positionText, 20, 100, textPaint);
            surHolder.unlockCanvasAndPost(can);
        }
    }

    public void update() {
        float dx = targetX - helicopter.getX();
        float dy = targetY - helicopter.getY();

        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float speed = 10;

        if (distance > speed) {
            helicopter.setPosition(
                    helicopter.getX() + (dx / distance) * speed,
                    helicopter.getY() + (dy / distance) * speed,
                    screenWidth,
                    screenHeight
            );
        } else {
            helicopter.setPosition(targetX, targetY, screenWidth, screenHeight);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
            targetX = event.getX();
            targetY = event.getY();
            return true;
        }
        return false;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        GameLoop.getInstance().startGameLoop();
        screenWidth = getWidth();
        screenHeight = getHeight();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
    }
}
