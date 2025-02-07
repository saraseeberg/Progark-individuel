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
import com.example.progarkex1.ecs.Entity;
import com.example.progarkex1.ecs.MovementSystem;
import com.example.progarkex1.ecs.PositionComponent;
import com.example.progarkex1.ecs.TargetComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private static GamePanel instance;
    public SurfaceHolder surHolder;
    private final Paint textPaint;

    private List<Entity> entities;
    private MovementSystem movementSystem;
    private Helicopter helicopter;

    public GamePanel(Context context) {
        super(context);
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException("Just one state!!");
        }

        surHolder = getHolder();
        surHolder.addCallback(this);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);

        entities = new ArrayList<>();
        helicopter = new Helicopter();
        entities.add(helicopter);

        movementSystem = new MovementSystem(entities, getWidth(), getHeight());
    }

    public static synchronized GamePanel getInstance() {
        return instance;
    }

    public void render() {
        Canvas can = surHolder.lockCanvas();
        if (can != null) {
            can.drawColor(Color.BLACK);

            PositionComponent position = helicopter.getPosition();
            can.drawBitmap(helicopter.getSprite(), position.x, position.y, null);

            String positionText = String.format(Locale.forLanguageTag("nb-NO"),
                    "X: %.1f, Y: %.1f", position.x, position.y);
            can.drawText(positionText, 20, 100, textPaint);

            surHolder.unlockCanvasAndPost(can);
        }
    }

    public void update() {
        movementSystem.update();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
            TargetComponent target = helicopter.getTarget();
            target.x = event.getX();
            target.y = event.getY();
            return true;
        }
        return false;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        GameLoop.getInstance().startGameLoop();
        movementSystem = new MovementSystem(entities, getWidth(), getHeight());
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
    }
}
